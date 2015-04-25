package chess.server.helpers;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import chess.model.data.PlayerCombo;
import chess.model.data.PlayerList;

public class LoginHelper {
	@SuppressWarnings("unchecked")
	public static RequestDispatcher LoginProcedure(HttpServletRequest request, ServletContext theContext) {
		String userName = (String) request.getParameter(ServerStrings.USERNAME);
		String password = (String) request.getParameter(ServerStrings.PASSWORD);
		RequestDispatcher rd;
		
		ArrayList<PlayerCombo> allPlayers = (ArrayList<PlayerCombo>) theContext.getAttribute(ServerStrings.ALL_PLAYERS);
		if(allPlayers == null) {
			allPlayers = new ArrayList<PlayerCombo>();
			theContext.setAttribute(ServerStrings.ALL_PLAYERS, allPlayers);
		}
		
		if(PlayerList.getPlayerList().validPlayer(userName, password) != true) {
			rd = request.getRequestDispatcher(ServerStrings.MESSAGE_PAGE);
			request.setAttribute(ServerStrings.MESSAGE_HEADING_PARAM, ServerStrings.INVALID_LOGIN_HEADING);
			request.setAttribute(ServerStrings.MESSAGE_BODY_PARAM, ServerStrings.INVALID_LOGIN_BODY);
		}
		else {
			AssignPlayer(allPlayers, userName);
			
			request.getSession().setAttribute(ServerStrings.FIRST_LOOK, ServerStrings.TRUE);
			request.getSession().setAttribute(ServerStrings.USERNAME, userName);
			rd = request.getRequestDispatcher(ServerStrings.CHESSBOARD_SERVLET);
		}
		return rd; 
	}
	
	public static void AssignPlayer(ArrayList<PlayerCombo> allPlayers, String userName) {
		boolean playerAssigned = false;
		if(allPlayers.size() != 0) {
			for(PlayerCombo pc : allPlayers) {
				if(!pc.hasPlayer(userName) && pc.needMore()) {
					pc.setPlayer(userName);
					playerAssigned = true;
				}
			}
		}
		
		if(playerAssigned != true){
			allPlayers.add(new PlayerCombo(userName, null));
		}
	}
}

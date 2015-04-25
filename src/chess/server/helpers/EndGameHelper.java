package chess.server.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import chess.model.board.Chessboard;
import chess.model.data.PlayerCombo;
import chess.model.data.entities.Moves;

public class EndGameHelper {
	static EntityManager em = Persistence.createEntityManagerFactory(ServerStrings.CHESS_ENTITIES).createEntityManager();
	
	@SuppressWarnings("unchecked")
	public static RequestDispatcher PreviousMatchMoves(HttpServletRequest request, ServletContext theContext) {
		Query q_lastGame = em.createNamedQuery(ServerStrings.GET_LAST_GAME);
		q_lastGame.setParameter(1, (String) request.getSession().getAttribute(ServerStrings.USERNAME));
		
		List<Integer> gameIds = q_lastGame.getResultList();
		
		Query q_allMoves = em.createNamedQuery(ServerStrings.GET_ALL_MOVES);
		q_allMoves.setParameter(1, request.getSession().getAttribute(ServerStrings.USERNAME));
		q_allMoves.setParameter(2, gameIds.get(gameIds.size() - 1));
		
		List<String> allMoves = q_allMoves.getResultList();
		request.setAttribute(ServerStrings.MOVES, allMoves);
		
		return request.getRequestDispatcher(ServerStrings.ENDGAME_PAGE);
	}
	
	@SuppressWarnings("unchecked")
	public static RequestDispatcher CurrentMatchMoves (HttpServletRequest request, ServletContext theContext) {
		Query q_lastGame = em.createNamedQuery(ServerStrings.GET_LAST_GAME);
		q_lastGame.setParameter(1, (String) request.getSession().getAttribute(ServerStrings.USERNAME));
		
		List<Integer> gameIds = q_lastGame.getResultList();
		
		int lastGameId = -1;
		lastGameId = gameIds.get(gameIds.size() - 1);
		
		if(lastGameId == -1) {
			lastGameId = 1;
		}
		else
			lastGameId++;
		
		HashMap<PlayerCombo, Chessboard> allGames = (HashMap<PlayerCombo, Chessboard>) theContext.getAttribute(ServerStrings.ALL_GAMES);
		ArrayList<PlayerCombo> allPlayers = (ArrayList<PlayerCombo>) theContext.getAttribute(ServerStrings.ALL_PLAYERS);
		
		for(PlayerCombo pc : allPlayers) {
			if(pc.hasPlayer((String) request.getSession().getAttribute(ServerStrings.USERNAME)) == true) {
				ArrayList<String> allMoves = pc.getAllMoves().getAllMoves(); //I know, this looks very silly
				request.setAttribute(ServerStrings.MOVES, allMoves);				
				
				try {
				for(int i = 0; i < allMoves.size(); i++) {
					em.getTransaction().begin();
					em.persist(new Moves((i + 1), lastGameId, allMoves.get(i), pc.getPlayer1(), pc.getPlayer2()));
					em.getTransaction().commit();
				}
				} catch (Exception e) {}
					
				allGames.remove(pc);
				allPlayers.remove(pc);
			}
		}
		request.getSession().removeAttribute(ServerStrings.USERNAME);
		
		return request.getRequestDispatcher(ServerStrings.ENDGAME_PAGE);
	}
}



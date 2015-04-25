package chess.server.servlets;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import chess.server.helpers.*;

@WebServlet("/EndGameServlet")
public class EndGameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EntityManager em = Persistence.createEntityManagerFactory("ChessEntities").createEntityManager();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ServletContext theContext = getServletContext();
		RequestDispatcher rd = EndGameHelper.PreviousMatchMoves(request, theContext);
		
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ServletContext theContext = getServletContext();
		RequestDispatcher rd = EndGameHelper.CurrentMatchMoves(request, theContext);
		
		rd.forward(request, response);
	}
}

package chess.server.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chess.server.helpers.*;

@WebServlet("/ChessServlet")
public class ChessServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ServletContext theContext = getServletContext();
		RequestDispatcher rd = ChessHelper.BoardRefresh(request, theContext);
		
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ServletContext theContext = getServletContext();
		RequestDispatcher rd = ChessHelper.BoardProcedure(request, theContext);
		
		rd.forward(request, response);
	}
}

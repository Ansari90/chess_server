package chess.server.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import chess.model.board.Chessboard;
import chess.model.board.Square;
import chess.model.data.PlayerCombo;
import chess.model.pieces.Piece;

public class ChessHelper {
	//HTML element creation strings - Used in SetDisplay() :: Alter with caution!
	//Future Upgrade: Do all this in javascript code on the page
	final static int squareSide = 64;
	final static String next = ",'";
	final static String whiteSquare = "White";
	final static String blackSquare = "Black";
	final static String blank = "Blank";
	final static String square = "Square";
	final static String button = "<button type=\"button\" style=\"background-image:url('./Images/";
	final static String button2 = ".png');\" onclick=\"enterSelection(";
	final static String source = "')\"><img src=\"./Images/";
	final static String middle = ".png\" style=\"border:0px\" alt=\"";
	final static String dimensions = "\" height=\"" + squareSide + "\" width=\"" + squareSide +"\"></button>";
	
	@SuppressWarnings("unchecked")
	public static RequestDispatcher BoardRefresh(HttpServletRequest request, ServletContext theContext) {
		String userName = (String) request.getSession().getAttribute(ServerStrings.USERNAME);
		HashMap<PlayerCombo, Chessboard> allGames = (HashMap<PlayerCombo, Chessboard>) theContext.getAttribute(ServerStrings.ALL_GAMES);
		Chessboard theBoard = null;
		
		for(PlayerCombo pc : (ArrayList<PlayerCombo>) theContext.getAttribute(ServerStrings.ALL_PLAYERS)) {
			if(pc.hasPlayer(userName))
				theBoard = allGames.get(pc);
		}
		
		//one of the players has already selected the end game option
		if(theBoard == null) {
			RequestDispatcher rd = request.getRequestDispatcher(ServerStrings.ENDGAME_SERVLET);
			return rd;
		}
		
		return (SetDisplay(request, theBoard));
	}
	
	@SuppressWarnings("unchecked")
	public static RequestDispatcher BoardProcedure(HttpServletRequest request, ServletContext theContext) {
		String userName = (String) request.getSession().getAttribute(ServerStrings.USERNAME);

		HashMap<PlayerCombo, Chessboard> allGames = (HashMap<PlayerCombo, Chessboard>) theContext.getAttribute(ServerStrings.ALL_GAMES);
		if(allGames == null) {	//first game
			allGames = new HashMap<PlayerCombo, Chessboard>();
			theContext.setAttribute(ServerStrings.ALL_GAMES, allGames);
		}
		
		PlayerCombo theCombo = null;
		Chessboard theBoard = null;
		
		for(PlayerCombo pc : (ArrayList<PlayerCombo>) theContext.getAttribute(ServerStrings.ALL_PLAYERS)) {
			if(pc.hasPlayer(userName)) { 
				theBoard = allGames.get(pc);
				theCombo = pc;
				
				if(theBoard == null) {	//new game (i.e. new board)
					theBoard = new Chessboard();
					allGames.put(theCombo, theBoard);
				}
				break;
			}
		}
		request.getSession().setAttribute(ServerStrings.GAME_MESSAGE, theCombo.getPlayer1() + " Vs. " + theCombo.getPlayer2());

		if(request.getSession().getAttribute(ServerStrings.FIRST_LOOK).equals(ServerStrings.TRUE))
			request.getSession().setAttribute(ServerStrings.FIRST_LOOK, ServerStrings.FALSE);
		else
			MakeMove(request, theCombo, theBoard);
		
		
		return SetDisplay(request, theBoard);
	}
	
	public static void MakeMove(HttpServletRequest request, PlayerCombo theCombo, Chessboard theBoard) {
		String whosNext = theCombo.whosTurn();
		String userName = (String) request.getSession().getAttribute(ServerStrings.USERNAME);
		String message = null;
		
		if((whosNext != null) && whosNext.equals(userName)) {
			int fromRank = Integer.parseInt(request.getParameter(ServerStrings.FROM_RANK));
			int toRank = Integer.parseInt(request.getParameter(ServerStrings.TO_RANK));
			char fromFile = request.getParameter(ServerStrings.FROM_FILE).charAt(0);
			char toFile = request.getParameter(ServerStrings.TO_FILE).charAt(0);
			
			String result = "";
			if(ColorCheck(theCombo, theBoard, userName, fromRank, fromFile) == true) {
				result = theBoard.makeMove(fromRank, fromFile, toRank, toFile);
				
				if(result.equals(Chessboard.INVALID) || result.equals(Chessboard.EMPTY_SQUARE))
					message = ServerStrings.INVALID_MOVE;
				else {
					theCombo.getAllMoves().add("" + fromRank + fromFile + " - " + toRank + toFile);
					theCombo.moveMade();
					message = result;
				}
			}
			else
				message = ServerStrings.NOT_YOUR_PIECE;
		}
		else
			message = ServerStrings.NOT_YOUR_TURN;
		
		request.setAttribute(ServerStrings.MOVE_MESSAGE, message);
	}
	
	public static boolean ColorCheck(PlayerCombo theCombo, Chessboard theBoard, String userName, int fromRank, char fromFile) {
		boolean properColor = false;
		Piece thePiece = theBoard.getSquare(fromRank, fromFile).hasPiece();
		if(thePiece != null) {
			if(thePiece.getColor().equals(Piece.WHITE) && theCombo.getPlayer1().equals(userName))
				properColor = true;
			if(thePiece.getColor().equals(Piece.BLACK) && (theCombo.getPlayer2() != null))
			{
				if(theCombo.getPlayer2().equals(userName))
					properColor = true;
			}
		}
		
		return properColor;
	}
	
	public static RequestDispatcher SetDisplay(HttpServletRequest request, Chessboard theBoard) {
		Piece thePiece;
		Square theSquare;
		
		String piece = "";
		String tempString;
		
		boolean white = false;	
		
		for(int i = 1; i <= 64; i++) {
			theSquare = theBoard.getSquare(i);
			thePiece = theSquare.hasPiece();
			
			String color = "";
			if(white == false)
				color = blackSquare;
			else
				color = whiteSquare;
			
			if((i%8) != 0) {
				white = !white;
			}
			//This code will combine the static strings in ChessHelper to create an image within a button object
			tempString = button + blank + color + square + button2 + theSquare.getRank() + next + theSquare.getFile();
			if(thePiece == null)
				piece = tempString + source + blank + color + square + middle + blank + " " + square + dimensions;
			else {
				String combo = thePiece.getColor() + thePiece.getLetter();
				piece = tempString + source + color + combo + middle + combo + dimensions;
			}
			
			request.getSession().setAttribute(ServerStrings.SQUARE + String.valueOf(i), piece);
		}
				
		RequestDispatcher rd = request.getRequestDispatcher(ServerStrings.CHESSBOARD_PAGE);
		return rd;
	}
}

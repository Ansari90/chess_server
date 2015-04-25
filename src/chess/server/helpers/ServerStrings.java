package chess.server.helpers;

//The only purpose of this class is store data for servlets and helper classes to use.
//I don't think that storing these in the database will be worthwhile.
public class ServerStrings {
	//Login Data Parameters
	public static String USERNAME = "userName";
	public static String PASSWORD = "password";
	
	//Game Parameters & Messages
	public static String TRUE = "true";
	public static String FALSE = "false";
	public static String FIRST_LOOK = "First Look";
	public static String GAME_MESSAGE = "gameMessage";
	
	public static String MOVE_MESSAGE = "moveMessage";
	public static String NOT_YOUR_TURN = "Not Your Turn! PATIENCE!";
	public static String NOT_YOUR_PIECE = "Not Your Piece!";
	public static String INVALID_MOVE = "Cannot Make That Move!";
	
	public static String FROM_FILE = "fromFile";
	public static String FROM_RANK = "fromRank";
	public static String TO_FILE = "toFile";
	public static String TO_RANK = "toRank";
	
	public static String SQUARE = "s";
	
	public static String ALL_PLAYERS = "allPlayers";
	public static String ALL_GAMES = "allGames";
	
	//Error Messages
	public static String MESSAGE_HEADING_PARAM = "messageHeading";
	public static String INVALID_LOGIN_HEADING = "Invalid User Name/Password";
	
	public static String MESSAGE_BODY_PARAM = "messageBody";
	public static String INVALID_LOGIN_BODY = "We could not find you in our system; please try again!";
	
	//Query String & Database Names
	public static String CHESS_ENTITIES = "ChessEntities";
	public static String GET_LAST_GAME = "getLastGame";
	public static String GET_ALL_MOVES = "getAllMoves";
	public static String MOVES = "moves";
	
	//Servlet & Page Addresses - Will have to manually write them in all .jsps, can't use these strings
	public static String LOGIN_SERVLET = "/LoginServlet";
	public static String LOGIN_PAGE = "/LoginPage.jsp";
	
	public static String CHESSBOARD_SERVLET = "/ChessServlet";
	public static String CHESSBOARD_PAGE = "/ChessBoard.jsp";
	
	public static String ENDGAME_SERVLET = "/EndGameServlet";
	public static String ENDGAME_PAGE = "/GameEndPage.jsp";
	
	public static String MESSAGE_PAGE = "/MessagePage.jsp";
}

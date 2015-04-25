package chess.model.board;

import chess.model.pieces.*;

import java.util.ArrayList;

public class Chessboard {
	/*
	 * Chessboard Information: Standard boards have 64 squares, so indices are numbered 0-63.
	 * Ranks are numbered 1-8, files are lettered a-h.
	 * 
	 * Chess is believed to have originated in India! (Source: http://en.wikipedia.org/wiki/History_of_chess)
	 */
	public static final char A = 'a';
	public static final char B = 'b';
	public static final char C = 'c';
	public static final char D = 'd';
	public static final char E = 'e';
	public static final char F = 'f';
	public static final char G = 'g';
	public static final char H = 'h';
	
	public static final int ONE = 1;
	public static final int TWO = 2;
	public static final int THREE = 3;
	public static final int FOUR = 4;
	public static final int FIVE = 5;
	public static final int SIX = 6;
	public static final int SEVEN = 7;
	public static final int EIGHT = 8;
	
	//Status strings, returned as messages; consider switching to enumerations
	public static final String EMPTY_SQUARE = "Empty Square";
	public static final String MOVE_MADE = "Move Made";
	public static final String INVALID = "Invalid Move";
	public static final String CHECK = "Check!";
	
	ArrayList<Square> cboard; 				//remember, index starts at 0
	private Square whiteKS, blackKS; 		//KS - King Square;
	
	public Chessboard() {
		cboard = new ArrayList<Square>();
		
		cboard.add(new Square(ONE, A, new Rook(Piece.WHITE)));
		cboard.add(new Square(ONE, B, new Knight(Piece.WHITE)));
		cboard.add(new Square(ONE, C, new Bishop(Piece.WHITE)));
		cboard.add(new Square(ONE, D, new King(Piece.WHITE)));	//index = 3, White's King's Square
		cboard.add(new Square(ONE, E, new Queen(Piece.WHITE)));
		cboard.add(new Square(ONE, F, new Bishop(Piece.WHITE)));
		cboard.add(new Square(ONE, G, new Knight(Piece.WHITE)));
		cboard.add(new Square(ONE, H, new Rook(Piece.WHITE)));
		
		cboard.add(new Square(TWO, A, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, B, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, C, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, D, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, E, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, F, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, G, new Pawn(Piece.WHITE)));
		cboard.add(new Square(TWO, H, new Pawn(Piece.WHITE)));
		
		cboard.add(new Square(THREE, A, null));
		cboard.add(new Square(THREE, B, null));
		cboard.add(new Square(THREE, C, null));
		cboard.add(new Square(THREE, D, null));
		cboard.add(new Square(THREE, E, null));
		cboard.add(new Square(THREE, F, null));
		cboard.add(new Square(THREE, G, null));
		cboard.add(new Square(THREE, H, null));
		
		cboard.add(new Square(FOUR, A, null));
		cboard.add(new Square(FOUR, B, null));
		cboard.add(new Square(FOUR, C, null));
		cboard.add(new Square(FOUR, D, null));
		cboard.add(new Square(FOUR, E, null));
		cboard.add(new Square(FOUR, F, null));
		cboard.add(new Square(FOUR, G, null));
		cboard.add(new Square(FOUR, H, null));
		
		cboard.add(new Square(FIVE, A, null));
		cboard.add(new Square(FIVE, B, null));
		cboard.add(new Square(FIVE, C, null));
		cboard.add(new Square(FIVE, D, null));
		cboard.add(new Square(FIVE, E, null));
		cboard.add(new Square(FIVE, F, null));
		cboard.add(new Square(FIVE, G, null));
		cboard.add(new Square(FIVE, H, null));
		
		cboard.add(new Square(SIX, A, null));
		cboard.add(new Square(SIX, B, null));
		cboard.add(new Square(SIX, C, null));
		cboard.add(new Square(SIX, D, null));
		cboard.add(new Square(SIX, E, null));
		cboard.add(new Square(SIX, F, null));
		cboard.add(new Square(SIX, G, null));
		cboard.add(new Square(SIX, H, null));
		
		cboard.add(new Square(SEVEN, A, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, B, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, C, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, D, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, E, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, F, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, G, new Pawn(Piece.BLACK)));
		cboard.add(new Square(SEVEN, H, new Pawn(Piece.BLACK)));
		
		cboard.add(new Square(EIGHT, A, new Rook(Piece.BLACK)));
		cboard.add(new Square(EIGHT, B, new Knight(Piece.BLACK)));
		cboard.add(new Square(EIGHT, C, new Bishop(Piece.BLACK)));
		cboard.add(new Square(EIGHT, D, new Queen(Piece.BLACK)));
		cboard.add(new Square(EIGHT, E, new King(Piece.BLACK))); //index = 60, Black's King's Square
		cboard.add(new Square(EIGHT, F, new Bishop(Piece.BLACK)));
		cboard.add(new Square(EIGHT, G, new Knight(Piece.BLACK)));
		cboard.add(new Square(EIGHT, H, new Rook(Piece.BLACK)));
		
		whiteKS = cboard.get(3);
		blackKS = cboard.get(60);
	}
	
	//This function could have been replaced with try/catch blocks
	//However, I find thisto be a more elegant method
	public boolean inBoardBounds(int rank, char file) {
		boolean inBounds = false;
		
		if((rank <= EIGHT && rank >= ONE) && (file <= H && file >= A))
			inBounds = true;
		
		return inBounds;
	}
	
	public Square getSquare(int rank, char file) {
		int number = (8 * (rank - 1)) + (file - A) + 1;
		return getSquare(number);
	}
	
	//Used in ChessServlet, not superfluous
	public Square getSquare(int number) {
		return cboard.get(number - 1);
	}
	
	public boolean kingCheck(String color) {
		boolean check = false;
		Piece thePiece = null;
		
		for(Square s : cboard) {
			thePiece = s.hasPiece();
			if((thePiece == null) || thePiece.getColor().equals(color)) {}
			else {
				if(color.equals(Piece.BLACK) == true) {
					if(thePiece.validMove(s, blackKS, this))
						check = true;
				}
				else {
					if(thePiece.validMove(s, whiteKS, this))
						check = true;
				}
				
			}
		}
		
		return check;
	}
	
	public void pawnMove(Pawn thePawn) {
		if(thePawn.firstMove())
			thePawn.firstMoveMade();
	}
	
	public String makeMove(int fromRank, char fromFile, int toRank, char toFile) {
		String moveResult = INVALID;
		
		if(inBoardBounds(fromRank, fromFile) && inBoardBounds(toRank, toFile)) {
			Square fromSquare = getSquare(fromRank, fromFile), toSquare = getSquare(toRank, toFile);
			Piece thePiece = fromSquare.hasPiece(), tempPiece; 
			
			if (thePiece == null) {
				moveResult = EMPTY_SQUARE;
			}
			else {
				if(thePiece.validMove(fromSquare, toSquare, this) == true) {	//Move Validation First, then
					tempPiece = toSquare.hasPiece();							//Piece Move Code
					
					fromSquare.setPiece(null);
					toSquare.setPiece(thePiece);
					
					if(thePiece.getLetter() == Piece.King) {
						if(thePiece.getColor().equals(Piece.WHITE))
							whiteKS = toSquare;
						else
							blackKS = toSquare;
					}															//Piece has been moved at this point
					//Verify that the bug actually originates from this location...comment this section out and try to
					//get a king check position
					if(thePiece.getColor().equals(Piece.WHITE)) {	//White Move Verification
						if(kingCheck(Piece.WHITE) == true) {
							toSquare.setPiece(tempPiece);
							fromSquare.setPiece(thePiece);
							
							if(thePiece.getLetter() == Piece.King) {
								whiteKS = fromSquare;
							}
						}
						else {
								if(thePiece.getLetter() == Piece.Pawn)
									pawnMove((Pawn) thePiece);
								
								moveResult = MOVE_MADE;
								if(kingCheck(Piece.BLACK) == true)
									moveResult += " : " + Piece.BLACK + " in " + CHECK;
						}
					}
					else {											//Black Move Verification
						if(kingCheck(Piece.BLACK) == true) {
							toSquare.setPiece(tempPiece);
							fromSquare.setPiece(thePiece);
							
							if(thePiece.getLetter() == Piece.King) {
								blackKS = fromSquare;
							}
						}
						else {
								if(thePiece.getLetter() == Piece.Pawn)
									pawnMove((Pawn) thePiece);
								
								moveResult = MOVE_MADE;
								if(kingCheck(Piece.WHITE) == true)
									moveResult += " : " + Piece.WHITE + " in " + CHECK;
						}		
					}
				}
			}
		}
		
		return moveResult;
	}
}

package chess.model.pieces;

import chess.model.board.*;

public interface Piece {
	public static final String BLACK = "Black";
	public static final String WHITE = "White";
	
	public static final char King = 'K';
	public static final char Queen = 'Q';
	public static final char Bishop = 'B';
	public static final char Knight = 'H';
	public static final char Rook = 'R';
	public static final char Pawn = 'P';

	public boolean validMove(Square currentSquare, Square toSquare, Chessboard theBoard);
	public String getColor();
	public char getLetter();
}

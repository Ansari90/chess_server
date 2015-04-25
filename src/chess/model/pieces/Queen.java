package chess.model.pieces;

import chess.model.board.*;

public class Queen implements Piece {
	String color;
	public Queen(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return Queen;
	}
	
	@Override
	/*
	 * Queen Movement: A queen can move any legal move that a Bishop or Rook can make from the square it is on.
	 * Only considered harder to write than a knight because you have to write Rook and Bishop logic before you can make a Queen.
	 */
	public boolean validMove(Square currentSquare, Square toSquare, Chessboard theBoard) {
		Bishop serviceBishop = new Bishop(color);
		Rook serviceRook = new Rook(color);
		
		if(serviceBishop.validMove(currentSquare, toSquare, theBoard) == true || serviceRook.validMove(currentSquare, toSquare, theBoard) == true)
			return true;
		else
			return false;
	}

}

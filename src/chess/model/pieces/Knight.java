package chess.model.pieces;

import chess.model.board.*;

public class Knight implements Piece {
	String color;
	public Knight(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return Knight;
	}

	@Override
	public boolean validMove(Square currentSquare, Square toSquare, Chessboard theBoard) {
		boolean valid = false;
		
		int currentRank = currentSquare.getRank();
		char currentFile = currentSquare.getFile();
		
		int toRank = toSquare.getRank();
		char toFile = toSquare.getFile();
		
		int rankDifference = currentRank - toRank;
		int fileDifference = currentFile - toFile;
		
		/*
		 * Knight Movement:Knights can have a maximum of 8 possible moves, and move in an 'L' formation,
		 * with the 'L' consisting of 4 squares (including the square with the knight on it).
		 * They can either move 2 files left or right and one rank up or down, OR 
		 * 2 ranks up or down and 1 file left or right. Probably the easiest piece to program, if you think about it.
		 */
		Piece thePiece = toSquare.hasPiece();
		if(thePiece == null || thePiece.getColor().equals(color) == false) {
			if((fileDifference == 2 || fileDifference == -2) && (rankDifference == 1 || rankDifference == -1))
				valid = true;
		
			if((fileDifference == 1 || fileDifference == -1) && (rankDifference == 2 || rankDifference == -2))
				valid = true;
		}
		return valid;
	}

}

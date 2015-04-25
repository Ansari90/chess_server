package chess.model.pieces;

import chess.model.board.*;

public class King implements Piece {
	String color;
	public King(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return King;
	}

	@Override
	public boolean validMove(Square currentSquare, Square toSquare, Chessboard theBoard) {
		boolean valid = false;
		
		int currentRank = currentSquare.getRank();
		char currentFile = currentSquare.getFile();
		
		int toRank = toSquare.getRank();
		char toFile = toSquare.getFile();
		
		/*
		 * King Movement: Limited to a maximum of 8 adjacent squares; very easy piece to program. Think of it as being in the
		 * center of 3x3 square grid.
		 */
		
		Piece thePiece = toSquare.hasPiece();
		if(thePiece == null || thePiece.getColor().equals(color) == false) {
			if(toFile == currentFile) {
				if(toRank == (currentRank + 1) || toRank == (currentRank - 1))
					valid = true;
			}
			
			if(toFile == (currentFile - 1)) {
				if((toRank == currentRank) || (toRank == currentRank - 1) || (toRank == currentRank + 1) )
					valid = true;
			}
			
			if(toFile == (currentFile + 1)) {
				if((toRank == currentRank) || (toRank == currentRank - 1) || (toRank == currentRank + 1) )
					valid = true;
			}
		}
		
		return valid;
	}

}

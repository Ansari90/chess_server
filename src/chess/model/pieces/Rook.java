package chess.model.pieces;

import chess.model.board.Chessboard;
import chess.model.board.Square;

public class Rook implements Piece {
	String color;
	public Rook(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return Rook;
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
		
		Piece thePiece = toSquare.hasPiece();
		
		/*
		 * Rook Movement: Rooks can make only move to squares on the same rank or file as themselves.
		 * Hence, the rank or file difference of any valid rook move MUST be equal to 0 (i.e. same rank or file).
		 */
		if((rankDifference == 0 || fileDifference == 0) && (thePiece == null || thePiece.getColor().equals(color) == false)) {
			valid = true;
			
			//only one parameter (either rank or file) changes for rook moves)
			int rankChange = (rankDifference != 0 ? (rankDifference < 0 ? 1 : -1) : 0);
			int fileChange = (fileDifference != 0 ? (fileDifference < 0 ? 1 : -1) : 0);
			
			Square tempSquare;
			do {
				currentRank += rankChange;
				currentFile += fileChange;
				
				tempSquare = theBoard.getSquare(currentRank, (char)currentFile);
				if((tempSquare.hasPiece() != null) && (tempSquare != toSquare)) {
					valid = false;
				}
			} while((valid == true) && (tempSquare != toSquare));
		}
		return valid;
	}
	
}

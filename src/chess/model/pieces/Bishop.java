package chess.model.pieces;

import chess.model.board.Chessboard;
import chess.model.board.Square;

public class Bishop implements Piece {
	String color;
	public Bishop(String color) {
		this.color = color;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return Bishop;
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
		 * Bishop Movement: Bishops move like the graph of the linear functions [f(x) = x] and [f(x) = -x];
		 * Therefore, any valid Bishop move MUST have the same rank and file difference (in terms of absolute value).
		 */
		Piece thePiece = toSquare.hasPiece();
		Square tempSquare;
		if((Math.abs(rankDifference) == Math.abs(fileDifference)) && (thePiece == null || thePiece.getColor().equals(color) == false)) {
			valid = true;
			
			int rankChange = (rankDifference < 0 ? 1 : -1);
			int fileChange = (fileDifference < 0 ? 1 : -1);
			
			do{
				currentRank += rankChange;
				currentFile += fileChange;
				
				tempSquare = theBoard.getSquare(currentRank, (char)currentFile);
				if((tempSquare.hasPiece() != null) && (tempSquare != toSquare))
					valid = false;
			}while((valid == true) && (tempSquare != toSquare));
		}
		return valid;
	}
}

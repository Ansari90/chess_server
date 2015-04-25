package chess.model.pieces;

import chess.model.board.Chessboard;
import chess.model.board.Square;

public class Pawn implements Piece {
	boolean firstMoveDone;
	String color;
	public Pawn(String color) {
		this.color = color;
		firstMoveDone = false;
	}

	public void firstMoveMade() { firstMoveDone = true; }
	
	@Override
	public String getColor() {
		return color;
	}

	@Override
	public char getLetter() {
		return Pawn;
	}
	
	public boolean firstMove() {
		return !firstMoveDone;
	}

	@Override
	public boolean validMove(Square currentSquare, Square toSquare, Chessboard theBoard) {
		boolean valid = false;
		
		int currentRank = currentSquare.getRank();
		char currentFile = currentSquare.getFile();
		
		int toRank = toSquare.getRank();
		char toFile = toSquare.getFile();
		
		/*
		 * Pawn Movement: One of the more complex pieces to program owing to its 3 different movement criteria, which are:
		 * 	1. First Move: Pawns can move 1 or 2 squares in the direction they are facing (their file) on their first move.
		 *  2. Second and later moves: 1 square in the direction they are facing only.
		 * 	2. Attack: Attacking for a Pawn is different from attacking with other pieces; Pawns can attack one diagonal on either
		 * 	   side of whichever direction they are facing.
		 *  Very Important: White Pawns can only advance their rank, while Black Pawns can only decrease their rank 
		 *  when they move. Checks must be included for both situations.
		 */
		
		//First Move Rule Check
		if(firstMoveDone == false) {	//need to check whether no piece is in between a pawn and its first move square
										//if the player chooses to take advantage of the First Move rule
			if(((currentRank + 2) == toRank && (theBoard.getSquare((toRank - 1), currentFile).hasPiece() == null)) 
					|| ((currentRank - 2) == toRank && (theBoard.getSquare((toRank + 1), currentFile).hasPiece() == null))) {
				if(toSquare.hasPiece() == null && currentFile == toFile)
					valid = true;
			}
		}
		
		//Regular Single Square Movement
		if((toRank == (currentRank + 1) || (toRank == (currentRank - 1))) && currentFile == toFile && (toSquare.hasPiece() == null))
			valid = true;
		
		//Attack Validation; one diagonal square on either side.
		Piece thePiece = toSquare.hasPiece();
		if(thePiece != null && (thePiece.getColor().equals(color) == false)) {
			if((toRank == (currentRank + 1)) || (toRank == (currentRank - 1))) {
				if(toFile == (currentFile + 1) || toFile == (currentFile -1))
					valid = true;
			}
		}
		return valid;
	}
}

package chess.model.board;

import chess.model.pieces.*;

public class Square {
	/*
	 * Square Terminology:
	 * Rank - (horizontal) row
	 * File - (vertical) column
	 */
	private int rank;
	private char file;
	private Piece piece;
	
	public Square(int rank, char file, Piece piece) {
		this.setRank(rank);
		this.setFile(file);
		this.piece = piece;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getRank() {
		return rank;
	}

	public void setFile(char file) {
		this.file = file;
	}

	public char getFile() {
		return file;
	}
	
	//This should probably be renamed to getPiece (default getter and setter names)
	//I've already made a bunch of code with hasPiece(), and it sounds better in a lot of board and piece logic.
	public Piece hasPiece() {
		return piece;
	}
	
	public void setPiece(Piece newPiece) {
		piece = newPiece;
	}
}

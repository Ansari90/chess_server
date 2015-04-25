package chess.model.data;

import java.util.ArrayList;

public class MoveHistory {
	private ArrayList<String> allMoves;
	private int moveNum;
	
	public MoveHistory() {
		allMoves = new ArrayList<String>();
		moveNum = 1;
	}
	
	public void add(String move) {
		allMoves.add(moveNum + ". " + move);
		moveNum++;
	}
	
	public ArrayList<String> getAllMoves() {
		return allMoves;
	}
}

package chess.model.data;

/*
 * This class has been designed to be more general than the game actually needs it to be.
 * Reason: Possibility of the addition of a game lobby and a different match making system.
 */
public class PlayerCombo {

	private String player1;
	private String player2;
	private String turnFor;
	private MoveHistory allMoves;
	
	public PlayerCombo(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
		turnFor = this.player1;
		allMoves = new MoveHistory();
	}
	
	public String getPlayer1() { return player1; }
	public String getPlayer2() { return player2; }
	
	public boolean hasPlayer(String player) {
		if (player.equals(player1) || player.equals(player2))
			return true;
		else
			return false;
	}
	
	public void setPlayer(String player) {
		if(needMore() == true) {
			if (player1 == null) {
				player1 = player;
			}
			else {
				player2 = player;
			}
		
			if (turnFor == null)
				turnFor = player;
		}
	}
	
	public boolean needMore() {
		if(player1 == null || player2 == null)
			return true;
		else
			return false;
	}
	
	public void moveMade() {
		if(turnFor.equals(player1) == true)
			turnFor = player2;
		else
			turnFor = player1;
	}
	
	public String whosTurn() {
		return turnFor;
	}
	
	public MoveHistory getAllMoves() {
		return allMoves;
	}
	
	public void addMove(String move) {
		allMoves.add(move);
	}
}

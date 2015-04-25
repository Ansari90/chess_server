package chess.model.data;

import java.util.HashMap;

public class PlayerList {
	private static HashMap<String, String> allPlayers = new HashMap<String, String>();
	private static PlayerList playerList = null;
	
	public static PlayerList getPlayerList() {
		if(playerList == null) {
			playerList = new PlayerList();
		}
		return playerList;
	}
	
	private PlayerList() {
		allPlayers.put("abdullah", "123");
		allPlayers.put("akram", "456");
		allPlayers.put("omair", "789");
		allPlayers.put("ayesha", "666");
	}
	
	public boolean validPlayer(String uname, String pword) {
		String storedPword = (String) allPlayers.get(uname);
		
		if(storedPword != null) {
			if(storedPword.equals(pword))
				return true;
		}
		return false;
	}
}

package mru.game.controller;

import java.util.ArrayList;

public class GameManager {
	
	/* In this class toy'll need these methods:
	 * A constructor
	 * A method to load the txt file into an arraylist (if it exists, so you check if the txt file exists first)
	 * A save method to store the arraylist into the the txt file 
	 * A method to search for a player based their name
	 * A method to find the top players
	 * Depending on your designing technique you may need and you can add more methods here 
	 */
	
	public GameManager() {
		// TODO: implement the GameManager constructor.
	}
	
	// BEGIN: save file implementation.
	public ArrayList<String[]> arrayList$StringArray$saveFile = new ArrayList<String[]>();
	public void loadSaveFile(String filename) {
		// TODO: check if the file exists, and then load it.
	}

}

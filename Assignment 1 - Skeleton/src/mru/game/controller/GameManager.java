package mru.game.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import mru.game.model.Player;

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
		// Main loop and main menu
		do {
			// Game menu triggered.
			
			// TODO: player must be initialized as new or with existing property values.
			Gambler player = new Gambler("Player", 100, 0);
			
			PuntoBancoGame currentGame = new PuntoBancoGame(player);
			
			// Game/betting menu and game loop
			boolean playAgainFlag = true;
			do {
				// Betting menu triggered.
				
				char betChoice = 'P'; // TODO: must be validated!
				int betAmount = 0; // TODO: Must be validated!
				currentGame.playRound(betChoice, betAmount);
				
				// TODO: return to betting menu.
			} while (playAgainFlag); // TODO: change the condition.
			
			
		} while (true); // TODO: change the condition.
	}
	
	public void loadTextFile(String filename) throws FileNotFoundException, IOException {
		
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<Player> listOfPlayers = new ArrayList<>();
		
		String line;
		
		while((line = br.readLine()) != null) {
			String[] dataLine = line.split(",");
			Player player = new Player(dataLine[0], Integer.parseInt(dataLine[1]), Integer.parseInt(dataLine[2]));
			listOfPlayers.add(player);
			
		}
	}
}

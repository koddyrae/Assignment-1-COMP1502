package mru.game.controller;

import java.io.*;
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
	ArrayList<Player> listOfPlayers = new ArrayList<>();
	
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
	
	/**
	 * @author Koddyrae
	 * User inputs a file name and if it exists, it will load a Player arraylist
	 * @param filename,  user inputted filename
	 * @throws IOException
	 *
	 */
	public void loadTextFile(String filename) throws IOException {
		File file = new File(filename);
		
		if (file.exists()) {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			while((line = br.readLine()) != null) {
				String[] dataLine = line.split(",");
				Player player = new Player((dataLine[0]), (Integer.parseInt(dataLine[1])), (Integer.parseInt(dataLine[2])));
				listOfPlayers.add(player);
				
			}
		}
		else {
			File newFile = new File("res/CasinoInfo.txt");
			newFile.createNewFile();
		}	
		
	}
	
	/**
	 * @author Koddyrae
	 * Method used to save the listOfPlayers arraylist to a text file called CasinoInfo in the res folder
	 * @throws IOException
	 *
	 */
	public void saveTextFile() throws IOException {
		try {
			FileOutputStream fos = new FileOutputStream("res/CasinoInfo.txt");
			PrintWriter pw = new PrintWriter(fos);
			
			for (Player player : listOfPlayers) {
				pw.println(player.toString());
				
			}
			pw.close();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}

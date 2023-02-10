package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.view.AppMenu;

public class GameManager {

	/*
	 * In this class toy'll need these methods: A constructor A method to load the
	 * txt file into an arraylist (if it exists, so you check if the txt file exists
	 * first) A save method to store the arraylist into the the txt file A method to
	 * search for a player based their name A method to find the top players
	 * Depending on your designing technique you may need and you can add more
	 * methods here
	 */
	ArrayList<Player> listOfPlayers;
	AppMenu appMenu;

	public GameManager() {
		// TODO: implement the GameManager constructor.
		// Main loop and main menu
		appMenu = new AppMenu();
		listOfPlayers = new ArrayList<>();
		boolean loopControl = true;

		char choice = appMenu.showMainMenu();
		mainLoop: do {
			if (choice == 'p') {
				// game loop
				do {
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
			if (choice == 's') {
				char searchChoice = appMenu.searchMenu();

				if (searchChoice == 't') {
					String[] splittedLine = findTopPlayers().split(",");
					System.out.printf("\t- TOP PLAYERS -\n");
					System.out.println("|=============|=============|");
					for (int i = 0; i < splittedLine.length; i+=2) {
						System.out.println("| " + splittedLine[i] + "| " + splittedLine[i+1] + " |");
						System.out.println("|-------------|------------|");
					}

					choice = appMenu.continueToMainMenu();
				}

				else if (searchChoice == 'n') {
					String searchName = appMenu.promptName();
					String[] splittedLine = findPlayersByName(searchName).split(",");
					System.out.printf("\t- TOP PLAYERS -\n");
					System.out.println("|=============|=============|");
					for (int i = 0; i < splittedLine.length; i+=3) {
						System.out.println("| " + splittedLine[i] + "| " + splittedLine[i+1] + " | " + splittedLine[i+2] + " |");
						System.out.println("|-------------|------------|");
					}
					choice = appMenu.continueToMainMenu();
				}

				else if (searchChoice == 'b') {
					choice = appMenu.showMainMenu();
					continue mainLoop;
				}
			}

			if (choice == 'e') {
				try {
					saveTextFile();
					System.out.println("Saving...");
					System.out.println("Done! Please visit us again");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loopControl = false;
				break; //im not sure if i need this lol
			}
		} while (loopControl);
	}


	/**
	 * @author Koddyrae User inputs a file name and if it exists, it will load a
	 *         Player arraylist
	 * @param filename, user inputted filename
	 * @throws IOException
	 *
	 */
	public void loadTextFile(String filename) throws IOException {
		File file = new File(filename);

		if (file.exists()) {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				String[] dataLine = line.split(",");
				Player player = new Player((dataLine[0]), (Integer.parseInt(dataLine[1])),
						(Integer.parseInt(dataLine[2])));
				listOfPlayers.add(player);

			}
		} else {
			File newFile = new File("res/CasinoInfo.txt");
			newFile.createNewFile();
		}

	}

	/**
	 * @author Koddyrae Method used to save the listOfPlayers arraylist to a text
	 *         file called CasinoInfo in the res folder
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

	/**
	 * @author Koddyrae Method to return the player(s) in the arraylist by name
	 * @param name user inputted search name
	 * @return name that contains the user input
	 */
	public String findPlayersByName(String name) {
		String result = "";

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
				result += listOfPlayers.get(i).toString() + ",";
			}
		}
		return result;
	}

	/**
	 * @author Koddyrae Method to return the top player(s) in the arraylist by wins
	 * @return top player(s) based on order from arraylist
	 */
	public String findTopPlayers() {
		String result = "";

		int max = listOfPlayers.get(0).getWins();

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (max < listOfPlayers.get(i).getWins()) {
				max = listOfPlayers.get(i).getWins();
			}
		}

		for (int j = 0; j < listOfPlayers.size(); j++) {
			if (max == listOfPlayers.get(j).getWins()) {
				result += listOfPlayers.get(j).getName() + "," + listOfPlayers.get(j).getWins() + ",";
			}
		}

		return result;

	}

}

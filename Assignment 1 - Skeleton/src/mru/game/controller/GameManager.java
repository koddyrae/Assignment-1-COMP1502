package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.view.AppMenu;
import mru.game.model.Record;

public class GameManager {

	/*
	 * In this class toy'll need these methods: A constructor A method to load the
	 * txt file into an arraylist (if it exists, so you check if the txt file exists
	 * first) A save method to store the arraylist into the the txt file A method to
	 * search for a player based their name A method to find the top players
	 * Depending on your designing technique you may need and you can add more
	 * methods here
	 * 
	 */
	
	AppMenu appMenu;

	public GameManager() throws IOException {
		// TODO: implement the GameManager constructor.
		// Main loop and main menu
		appMenu = new AppMenu();
		boolean loopControl = true;
		
		final String FILE_PATH = "res/CasinoInfo.txt";
		Record database = new Record(FILE_PATH);
		
		char choice = appMenu.showMainMenu();
		mainLoop: do {
			if (choice == 'p') {
				// game loop
				do {
					// TODO: player must be initialized as new or with existing property values.
					Gambler player = new Gambler("Player");

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
			else if (choice == 's') {
				char searchChoice = appMenu.searchMenu();

				if (searchChoice == 't') {
					String[] splittedLine = database.findTopPlayers().split(",");
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
					String[] splittedLine = database.findPlayersByName(searchName).split(",");
					System.out.printf("\t- PLAYER INFO -\n");
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

			else if (choice == 'e') {
				database.saveTextFile();
				System.out.println("Saving...");
				System.out.println("Done! Please visit us again");
				loopControl = false;
				break; //im not sure if i need this lol
			}
		} while (loopControl);
	}

}

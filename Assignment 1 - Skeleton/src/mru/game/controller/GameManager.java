package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.view.AppMenu;
import mru.game.model.Record;

public class GameManager {

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
					appMenu.printTopPlayers(database.findTopPlayers());
					choice = appMenu.continueToMainMenu();
				}

				else if (searchChoice == 'n') {
					String searchName = appMenu.promptName();
					appMenu.printPlayersByName(database.findPlayersByName(searchName));
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

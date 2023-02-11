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
            // Prepare a Gambler object before starting the game.
            String playerName = appMenu.promptName();
            if(record.doesPlayerExist()) {
                Gambler player = new Gambler(record.getPlayer(playerName.toUpperCase()));
                appMenu.welcomeMessage();
            } else {
                Gambler player = new Gambler(playerName);
            }

            PuntoBancoGame currentGame = new PuntoBancoGame(player);

					// Sitting at the casino table, players may place bets and wager on
					// their bet, and then after the round plays out according to the
					// rules of Punto Banco they are prompted whether or not to play
					// again.
					boolean playAgainFlag = true;
					do {
						// Betting menu triggered.

              char betChoice = appMenu.promptBet();
              int betAmount = appMenu.promptWager();
              currentGame.playRound(betChoice, betAmount);

              playAgainFlag = appMenu.promptPlayAgain();
					} while (playAgainFlag);

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
			}
		} while (loopControl);
	}

}

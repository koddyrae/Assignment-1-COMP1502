package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.view.AppMenu;
import mru.game.model.Record;

public class GameManager {
	private AppMenu appMenu = new AppMenu();
	private Record database;

	/**
	 * Creates the application, calling methods as necessary from the model and view
	 * packages.
	 *
	 * @author Bryce 'cybeR' Carson
	 * @throws IOException in the case that the database on-disk cannot be created
	 *                     or accessed.
	 */
	public GameManager() throws IOException {
		// Every instance of the application needs to associate with the
		// database.
		final String FILE_PATH = "res/CasinoInfo.txt";
		database = new Record(FILE_PATH);
		ArrayList<Gambler> casinoPatrons = database.getPatrons();

		// Main application menu loop.
		boolean exitFlag = false;
		do {
			char mainMenuChoice = appMenu.promptWithMainMenu();
			switch (mainMenuChoice) {
			case 'p':
				Gambler focalGambler = preparePuntoBancoTable(casinoPatrons);
				
				// The focal player leaves the crowd of patrons, plays a game, then rejoins the crowd.
				// Do NOT think to compose the saveTextFile function with playPuntoBanco. That breaks certain updating logic.
				casinoPatrons = playPuntoBanco(focalGambler, casinoPatrons);
				
				database.saveTextFile(casinoPatrons);
				
				break;
			case 's':
				searchRecords();
				
				break;
			case 'e':
				exitFlag = true;
				
				break;
			}
		} while (!exitFlag); // Raise flag to exit
	}

	/**
	 * This method creates the Gambler object necessary for a game to be played out,
	 * and then sets the table.
	 *
	 * @author Bryce 'cybeR' Carson
	 */
	private Gambler preparePuntoBancoTable(ArrayList <Gambler> casinoPatrons) {
		final boolean RETURNING_PLAYER = true;
		final boolean NEW_PLAYER = false;
		
		Gambler player;
		
		// Prompt the player for their name. If they are an existing player,
		// their record in the database will be used to create the Gambler
		// object representing them at the table, or they will have a new Player
		// object created for them in the database when the time comes to save
		// and exit. In the case that the Player has no money (or is in debt)
		// refuse them entry to the casino.
		String playerName = appMenu.promptName();
		Gambler temporaryGambler = new Gambler(playerName);
		
		// Create a temporary gambler to check the player's balance.
		if (casinoPatrons.contains(temporaryGambler)) {
			// Create a Gambler object to check the player's balance is positive.
			player = casinoPatrons.get(casinoPatrons.indexOf(temporaryGambler));
			
			int playerBalance = player.getBalance();
			
			if (playerBalance > 0) {
				appMenu.welcomeMessage(playerName, playerBalance, RETURNING_PLAYER);
			} else {
				/*
				 * The player is not admitted to the casino. This is not magical because it's a
				 * self-documenting setter.
				 */
				player.setAdmittedToCasino(false);
			}
		} else {
			player = new Gambler(playerName);
			appMenu.welcomeMessage(playerName, player.getBalance(), NEW_PLAYER);
		}

		return player;
	}

	private ArrayList<Gambler> playPuntoBanco(Gambler player, ArrayList<Gambler> casinoPatrons) {
		// Proceed with the games!
		if (player.getAdmittedToCasino()) {
			PuntoBancoGame currentGame;
			
			// Sitting at the casino table, players may place bets and wager on
			// their bet, and then after the round plays out according to the
			// rules of Punto Banco they are prompted whether or not to play
			// again.
			char playAgain;
			do {
				// Start a new game of Punto Banco using the player
				currentGame = new PuntoBancoGame(player);
				
				// Betting menu triggered.
				char betChoice = appMenu.promptBet();
				int betAmount = appMenu.promptWager(player.getBalance());

				currentGame.playRound(betChoice, betAmount);

				// Check that the player has not lost all of their money.
				if (player.getBalance() <= 0) {
					appMenu.brokeDisplay(); // The player is broke (has no money).
					playAgain = 'n';
				} else {
					// FIXME: Somehow, even though we're checking it, player's with a balance of zero are being readmitted to the casino floor.
					System.out.println(player);
					
					playAgain = appMenu.promptPlayAgain();
				}
			} while (playAgain != 'n' && player.getBalance() > 0); // Only loop while they WANT to play and CAN. People who want to play but CANT play MUST NOT play.
		} else {
			appMenu.refuseVisitor();
		}

		// Thank you, come again! If a player has been to the casino before,
		// they will also be found in the list of casinoPatrons, otherwise they
		// must be added to the list of casinoPatrons after the end of the game.
		// Formally, this depends on the Gambler.equals() method, which tests
		// equality by name ONLY.
		if (casinoPatrons.contains(player)) {
			// Update the focal patron's record on file.
			casinoPatrons.remove(player); // Remove player by name, the balance is stale data.
			casinoPatrons.add(player); // Add player object, containing name balance and wins.
		} else {
			casinoPatrons.add(player);
		}

		return casinoPatrons;
	}

	/**
	 * Search the database for particular records, then return the player to the main menu regardless.
	 *
	 * @author Bryce 'cybeR' Carson
	 * @throws IOException 
	 */
	private void searchRecords() throws IOException {
		char searchChoice = appMenu.promptWithSearchMenu();
		switch (searchChoice) {
		case 't':
			appMenu.printTopPlayers(database.findTopPlayers());
			appMenu.promptEnterToContinue();
			break;
		case 'n':
			String searchName = appMenu.promptName();
			appMenu.printPlayersByName(database.findPlayersByName(searchName));
			appMenu.promptEnterToContinue();
			break;
		}
	}
}

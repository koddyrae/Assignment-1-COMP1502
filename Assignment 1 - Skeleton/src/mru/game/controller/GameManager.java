package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.view.AppMenu;
import mru.game.model.Record;

public class GameManager {
	private AppMenu appMenu;
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

		// Create an application menu object to perform view operations for us.
		appMenu = new AppMenu();

		// Main application menu loop.
		boolean exitFlag = false;
		do {
			char mainMenuChoice = appMenu.promptWithMainMenu();
			switch (mainMenuChoice) {
			case 'p':
				// preparePuntoBancoTable creates the Gambler object that
				// playPuntoBanco requires as input.
				Gambler focalGambler = preparePuntoBancoTable();
				casinoPatrons = playPuntoBanco(focalGambler, casinoPatrons); // The focal player leaves the crowd of
																				// patrons, plays a game, then rejoins
																				// the crowd.
				break;
			case 's':
				searchRecords();
				break;
			case 'e':
				database.saveTextFile(casinoPatrons);
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
	private Gambler preparePuntoBancoTable() {
		final boolean returningPlayer = true;
		final boolean newPlayer = false;

		// Prepare a Gambler object before starting the game.
		Gambler player;

		// Prompt the player for their name. If they are an existing player,
		// their record in the database will be used to create the Gambler
		// object representing them at the table, or they will have a new Player
		// object created for them in the database when the time comes to save
		// and exit. In the case that the Player has no money (or is in debt)
		// refuse them entry to the casino.
		String playerName = appMenu.promptName();
		if (database.doesPlayerExist(playerName)) {
			// Create a Gambler object to check the player's balance is positive.
			player = new Gambler(database.getPlayer(playerName.toUpperCase()));
			int playerBalance = player.getBalance();
			if (playerBalance > 0) {
				appMenu.welcomeMessage(playerName, playerBalance, returningPlayer);
			} else {
				/*
				 * The player is not admitted to the casino. This is not magical because it's a
				 * self-documenting setter.
				 */
				player.setAdmittedToCasino(false);
			}
		} else {
			player = new Gambler(playerName);
			appMenu.welcomeMessage(playerName, player.getBalance(), newPlayer);
		}

		return player;
	}

	private ArrayList<Gambler> playPuntoBanco(Gambler player, ArrayList<Gambler> casinoPatrons) {
		// Proceed with the games!
		if (player.getAdmittedToCasino()) {
			// Start a new game of Punto Banco using the player
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

				// Check that the player has not lost all of their money.
				if (player.getBalance() <= 0) {
					appMenu.brokeDisplay(); // The player is broke (has no money).
					playAgainFlag = false;
				} else {
					playAgainFlag = appMenu.promptPlayAgain();
				}
			} while (playAgainFlag); // While the user wishes to continue playing.
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
	 * Search the database for particular records _until_ the player wants to return
	 * to the main menu.
	 *
	 * @author Bryce 'cybeR' Carson
	 */
	private void searchRecords() {
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

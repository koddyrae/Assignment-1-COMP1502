package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.view.AppMenu;
import mru.game.model.Record;

/**
 * Class responsible for all functionality related to connecting the components of the project
 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
 *
 */
public class GameManager {
	final private AppMenu APP_MENU = new AppMenu();
	final private Record DATABASE = new Record();

	/**
	 * Creates the application, calling methods as necessary from the model and view
	 * packages.
	 *
	 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
	 * @throws IOException the exception thrown in case of errors in creating/accessing the database
	 */
	public GameManager() throws IOException {
		ArrayList<Gambler> casinoPatrons;

		// Main application menu loop.
		boolean exitFlag = false;
		do {
			char mainMenuChoice = APP_MENU.promptWithMainMenu();
			switch (mainMenuChoice) {
			case 'p':
				// Casino patron list is updated from disk any time the user of the program
				// wants to play a game.
				casinoPatrons = DATABASE.getPatrons();

				// The focal player leaves the crowd of patrons, plays a game, then rejoins the
				// crowd.
				// Do NOT think to compose the saveTextFile function with playPuntoBanco. That
				// breaks certain updating logic.
				Gambler focalGambler = preparePuntoBancoTable(casinoPatrons, DATABASE);
				casinoPatrons = playPuntoBanco(focalGambler, casinoPatrons);

				// Update the database on disk.
				DATABASE.saveTextFile(casinoPatrons);

				break;
			case 's':
				casinoPatrons = DATABASE.getPatrons();
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
	private Gambler preparePuntoBancoTable(ArrayList<Gambler> casinoPatrons, Record database) {
		final boolean RETURNING_PLAYER = true;
		final boolean NEW_PLAYER = false;

		Gambler player;

		// Prompt the player for their name. If they are an existing player,
		// their record in the database will be used to create the Gambler
		// object representing them at the table, or they will have a new Player
		// object created for them in the database when the time comes to save
		// and exit. In the case that the Player has no money (or is in debt)
		// refuse them entry to the casino.
		String playerName = APP_MENU.promptName();

		boolean returningPlayerFlag = database.doesPlayerExist(playerName);

		if (returningPlayerFlag) {
			// Recreate the Gambler from the on-disk state if the player is
			// found in our database. indexOf uses the equals method, so we're
			// just getting the in-memory reflection of on-disk data by
			// searching through the in-memory reflection by testing each
			// element of the array list of Gambler objects by-name, which is
			// case-insensitive. This test should be robust, but yet we have to
			// FIXME.
			player = casinoPatrons.get(casinoPatrons.indexOf(new Gambler(playerName)));

			int playerBalance = player.getBalance();

			// Ensure the returning player HAS money to play with.
			if (playerBalance > 0) {
				APP_MENU.welcomeMessage(playerName, playerBalance, RETURNING_PLAYER);
			} else {
				/*
				 * The player is not admitted to the casino. This is not magical because it's a
				 * self-documenting setter.
				 */
				player.setAdmittedToCasino(false);
			}
		} else {
			player = new Gambler(playerName);
			APP_MENU.welcomeMessage(playerName, player.getBalance(), NEW_PLAYER);
		}

		return player;
	}

	/**
	 * Method to start the puntoBanco game
	 * 
	 * @param player        main player object that is being used for game
	 * @param casinoPatrons arraylist of players currently at the table
	 * @return arraylist the current list of players at table
	 */
	private ArrayList<Gambler> playPuntoBanco(Gambler player, ArrayList<Gambler> casinoPatrons) {
		// Proceed with the games!
		if (player.getAdmittedToCasino() && player.getBalance() > 0) {
			PuntoBancoGame currentGame;

			// Start a new game of Punto Banco using the player
			currentGame = new PuntoBancoGame(player);

			// Sitting at the casino table, players may place bets and wager on
			// their bet, and then after the round plays out according to the
			// rules of Punto Banco they are prompted whether or not to play
			// again.
			char playAgain;
			do {
				// Betting menu triggered.
				char betChoice = APP_MENU.promptBet();
				int betAmount = APP_MENU.promptWager(player.getBalance());

				currentGame.playRound(betChoice, betAmount);

				// Check that the player has not lost all of their money.
				if (player.getBalance() <= 0) {
					player.setAdmittedToCasino(false);
					APP_MENU.brokeDisplay(); // The player is broke (has no money).
					playAgain = 'n';
				} else {
					System.out.println(player);

					playAgain = APP_MENU.promptPlayAgain();
				}
			} while (playAgain != 'n' && player.getBalance() > 0 && player.getAdmittedToCasino()); 
		} else {
			APP_MENU.refuseVisitor();
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
	 * Search the database for particular records, then return the player to the
	 * main menu regardless.
	 *
	 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
	 * @throws IOException the exception thrown in case of errors in the appmenu
	 */
	private void searchRecords() throws IOException {
		char searchChoice = APP_MENU.promptWithSearchMenu();
		switch (searchChoice) {
		case 't':
			APP_MENU.printTopPlayers(DATABASE.findTopPlayers());
			APP_MENU.promptEnterToContinue();
			break;
		case 'n':
			String searchName = APP_MENU.promptName();
			APP_MENU.printPlayersByName(DATABASE.findPlayersByName(searchName));
			APP_MENU.promptEnterToContinue();
			break;
		}
	}
}

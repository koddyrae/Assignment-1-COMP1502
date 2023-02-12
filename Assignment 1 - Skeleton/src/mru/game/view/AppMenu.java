package mru.game.view;

import java.util.Scanner;

import mru.game.controller.Gambler;

/**
 * @author koddy Class for UI
 */
public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user It also
	 * prompts the user for the inputs and validates them
	 */
	Scanner key;

	public AppMenu() {
		key = new Scanner(System.in);
	}

	/**
	 * Method to print main menu
	 * 
	 * @return choice user choice to continue in mainmenu
	 */
	public char promptWithMainMenu() {
		char choice;
		do {
			System.out.println("Select One of these Options: ");
			System.out.println("\n\t (P) Play game");
			System.out.println("\t (S) Search");
			System.out.println("\t (E) Exit");
			System.out.print("\nEnter your choice: ");
			choice = key.nextLine().toLowerCase().charAt(0);
			if (choice == 'p' || choice == 's' || choice == 'e') {
				return choice;
			} else {
				System.out.println("Your input was invalid, please try again.");
			}

		} while (choice != 'p' || choice != 's' || choice != 'e');
		key.next();
		return choice;
	}

	/**
	 * Method to print search menu
	 * @return choice user choice to continue in submenu
	 */
	public char promptWithSearchMenu() {
		char choice;
		do {
			System.out.println("Select One of these Options: ");
			System.out.println("\n\t(T) Top player (Most number of wins)");
			System.out.println("\t(N) Looking for a Name");
			System.out.println("\t(B) Back to main menu");
			System.out.print("\nEnter a choice: ");
			choice = key.nextLine().toLowerCase().charAt(0);
			if (choice == 't' || choice == 'n' || choice == 'b') {
				return choice;
			} else {
				System.out.println("Your input was invalid, please try again.");
			}
		} while (choice != 't' || choice != 'n' || choice != 'b');
		key.nextLine();
		return choice;
	}

	/**
	 * Method to print starting game menu
	 * 
	 * @return choice user choice to continue in game menu
	 */
	public char promptBet() {
		System.out.println("Who do you want to bet on: ");
		System.out.println("\n\t(P) Player Wins");
		System.out.println(  "\t(B) Banker Wins");
		System.out.println(  "\t(T) Tie Game");
		
		char choice;
		do {
			System.out.print("\nEnter a choice: ");
			choice = key.nextLine().toLowerCase().charAt(0);
			
			// If the choice is NOT p, b, or t, warn the user. We will do-while until the input is valid.
			if ( !(choice == 'p' || choice == 'b' || choice == 't') ) {
				System.out.println("Your input was invalid, please try again.");
			}
		} while ( !(choice == 'p' || choice == 'b' || choice == 't') );
		
		// There is no need to flush the keyboard in this prompt because we acquire the entire line, then we acquire the first character. The line separator ("garbage") is already flushed.
		
		return choice;
	}

	/**
	 * Method to print top players
	 * 
	 * @param results results from findTopPlayers method in Record
	 */
	public void printTopPlayers(String results) {
		String[] splittedLine = results.split(",");
		System.out.printf("\t- TOP PLAYERS -\n");
		System.out.println("|=============|=============|");
		System.out.println("|=NAME========|=# WINS======|");
		for (int i = 0; i < splittedLine.length; i += 2) {
			System.out.println("| " + splittedLine[i] + "| " + splittedLine[i + 1] + " |");
			System.out.println("|-------------|------------|");
		}
	}

	/**
	 * Method to print players by name
	 * 
	 * @param results results from findPlayersByName method in Record
	 */
	public void printPlayersByName(String results) {
		String[] splittedLine = results.split(",");
		System.out.printf("\t- PLAYER INFO -\n");
		System.out.println("|=============|=============|=============|");
		System.out.println("|=NAME========|=BALANCE=====|=# WINS======|");
		
		if (splittedLine.length == 1) {
			System.out.println("|             |             |             |");
			System.out.println("|=============|=============|=============|");
			System.out.println("No players are found with this name.\n");
		}
		else {
			for (int i = 0; i < splittedLine.length; i += 3) {
				System.out
					.println("| " + splittedLine[i] + "| " + splittedLine[i + 1] + " | " + splittedLine[i + 2] + " |");
				System.out.println("|-------------|------------|");
			}
		}
	}

	/**
	 * Method to allow player to return back to main menu
	 * 
	 * @return choice user choice to continue in main menu
	 */
	public void promptEnterToContinue() {
		System.out.println("Press \"Enter\" to Continue...");
		key.nextLine();
	}

	/**
	 * Method to return user to main menu after invalid input
	 * 
	 * @return choice user choice when back at main menu
	 */
	public char invalidInputToMain() {
		System.out.println("Invalid Input...");
		System.out.println("Returning to Main Menu");
		char choice = promptWithMainMenu();
		return choice;
	}

	/**
	 * Method to return user to main menu a
	 * 
	 * @return choice user choice when back at main menu
	 */
	public void brokeDisplay() {
		System.out.println("You have no money to play...");
		System.out.println("Returning to main menu...");
	}

	public void refuseVisitor() {
		System.out.println("You are unable to play...");
		System.out.println("Returning to main menu...");
	}

	/**
	 * Method to ask user to input a name
	 * 
	 * @return name user inputted name
	 */
	public String promptName() {
		System.out.print("Enter your name: ");
		String name = key.nextLine();
		return name;
	}

	/**
	 * Method to ask user for bet amount, with only valid amounts permitted.
	 * 
	 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga 
	 * @return bet amount
	 */
	public int promptWager(int playerBalance) {
		System.out.println("How much do you wager on your bet?");
		
		int bet = 0;
		
		// Repeatedly prompt the user for a valid bet (must be positive and must be an amount they carry).
		do {
			System.out.print("Enter a positive, whole dollar amount (an intenger): ");
			bet = key.nextInt();
			
			if(  !(bet > 0 && bet <= playerBalance) ) {
				System.out.println("You cannot bet zero or an amount greater than your current balance ($" + playerBalance + ").");
			}
		} while( !(bet > 0 && bet <= playerBalance) );
		
		key.nextLine(); // Flush keyboard output.
		
		return bet;
	}

	/**
	 * Method to ask user to play again
	 * 
	 * @return boolean on if player wants to play again or not
	 */
	public char promptPlayAgain() {
		char choice;
			do {
				System.out.print("Do you wish to play again? (Y/N)");
				choice = key.nextLine().toLowerCase().charAt(0);
				if (choice == 'y' || choice == 'n') {
					return choice;
				}
				else {
					System.out.println("Your input was invalid, please try again.");
				}
			} while (choice != 'y' || choice != 'n');
			key.nextLine();
			return choice;
	}

	/**
	 * Method to welcome player to game
	 * 
	 * @param name            name of gambler
	 * @param balance         balance of gambler
	 * @param returningPlayer checks player is new or not
	 */
	public void welcomeMessage(String name, int balance, boolean returningPlayer) {
		String maybeBack = returningPlayer ? "back " : "";
		String maybeNew = returningPlayer ? "" : " initial"; // The truth-table is the reverse of the above line.
		System.out.println("**************************************************");
		System.out.println(
				"***  Welcome " + maybeBack + name + "   --- Your" + maybeNew + " balance is: " + balance + "$    ***");
		System.out.println("**************************************************");
	}

	/**
	 * Method to print the game round and show the result
	 * 
	 * @param player1   player object
	 * @param banker    banker object
	 * @param amountWon amount won/lost in round
	 * @param result    result boolean to check if won/lost
	 */
	public void roundDisplay(Gambler player1, Gambler banker, int amountWon, boolean result) {
		String maybeWin = result ? " Won " : " Lose ";

		System.out.printf("\t - PUNTO BANCO -\n");
		System.out.println("+==================+==================+");
		System.out.println("|+" + player1.getName() + "==========+" + banker.getName() + "===========+|");
		for (int i = 0; i < 3; i++) {
			System.out.println("|+" + player1.getHand().get(i) + "      +" + banker.getHand().get(i) + "      +|");
			System.out.println("+------------------+-------------------+");
		}
		System.out.println("|+" + player1.getName() + " POINTS: " + player1.getScore() + "|" + banker.getName()
				+ " POINTS: " + banker.getScore() + "+|");

		System.out.println("+==================+==================+");
		System.out.println("+|" + player1.getName() + maybeWin + "$ " + amountWon + " |+");
		System.out.println("+==================+==================+");

	}

}

package mru.game.view;

import java.util.Scanner;

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	Scanner key;
	
	public AppMenu() {
		key = new Scanner(System.in);
	}
	
	public char showMainMenu() {
		System.out.println("Select One of these Options: ");
		System.out.println("\n\t (P) Play game");
		System.out.println("\t (S) Search");
		System.out.println("\t (E) Exit");
		System.out.println("\nEnter a choice: ");
		char choice = key.nextLine().toLowerCase().charAt(0);		
		return choice;
	}
	
	public char searchMenu() {
		System.out.println("Select One of these Options: ");
		System.out.println("\n\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to main menu");
		System.out.println("\nEnter a choice: ");
		char choice = key.nextLine().toLowerCase().charAt(0);		
		return choice;
	}
	
	public char gameMenu() {
		System.out.println("Who do you want to bet on: ");
		System.out.println("\n\t(P) Player Wins");
		System.out.println("\t(B) Banker Wins");
		System.out.println("\t(T) Tie Game");
		System.out.println("\nEnter a choice: ");
		char choice = key.nextLine().toLowerCase().charAt(0);		
		return choice;
	}
	
	public void printTopPlayers(String results) {
		String[] splittedLine = results.split(",");
		System.out.printf("\t- TOP PLAYERS -\n");
		System.out.println("|=============|=============|");
		System.out.println("|=NAME========|=WINS========|");
		for (int i = 0; i < splittedLine.length; i+=2) {
			System.out.println("| " + splittedLine[i] + "| " + splittedLine[i+1] + " |");
			System.out.println("|-------------|------------|");
		}
	}
	

	public char continueToMainMenu() {
		System.out.println("Press \"Enter\" to Continue...");
		key.nextLine();
		char choice = showMainMenu();
		return choice;
	}
	
	public String promptName() {
		System.out.println("Enter your name: ");
		String name = key.nextLine();
		return name;
	}
}

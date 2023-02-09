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
	
	public String showMainMenu() {
		System.out.println("Select One of these Options: ");
		System.out.println("\n\t (P) Play game");
		System.out.println("\t (S) Search");
		System.out.println("\t (E) Exit");
		System.out.println("\nEnter a choice: ");
		String choice = key.nextLine();
		return choice;
	}
	
	public String searchMenu() {
		System.out.println("Select One of these Options: ");
		System.out.println("\n\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to main menu");
		System.out.println("\nEnter a choice: ");
		String choice = key.nextLine();
		return choice;
	}
	
	public String promptName() {
		System.out.println("What is your name:");
		String choice = key.nextLine();
		return choice;
	}
}

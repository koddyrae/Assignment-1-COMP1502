package mru.game.view;

import java.util.Scanner;

public class AppMenu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */
	
	public void showMainMenu() {
		System.out.println("Select One of these Options: ");
		System.out.println("\n\t (P) Play game");
		System.out.println("\t (S) Search");
		System.out.println("\t (E) Exit");
		System.out.println("\nEnter a choice: ");
		
		Scanner key = new Scanner(System.in);
		String choice = key.nextLine();
	}
}

package mru.game.model;

import mru.game.controller.Gambler;

/**
 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
 *  This class represent each player record in the Database
 */
public class Player {

	private String name;
	private int balance;
	private int numOfWins;

	/**
	 * @author Bryce 'cybeR' Carson
	 * @param Gambler gambler object from punto banco game 
	 */
	public Player(Gambler gambler) {
		name = gambler.getName();
		balance = gambler.getBalance();
		numOfWins = gambler.getWins();
	}

	/**
	 * Constructor for player object
	 * 
	 * @param name the name of the player
	 * @param balance the current/inital balance of the player
	 * @param wins the number of wins the player has
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}

	/**
	 * @return name the name of player
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return balance the balance of the player
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @return wins the number of wins
	 */
	public int getWins() {
		return numOfWins;
	}

	/**
	 * Override method to change player object into string
	 */
	public String toString() {
		return name + "," + balance + "," + numOfWins;
	}
}

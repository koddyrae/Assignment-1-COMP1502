package mru.game.model;

import mru.game.controller.Gambler;

/**
 * @author Koddy Rae Madriaga
 *  This class represent each player record in the Database It is basically a model class for each record in the txt file
 */
public class Player {

	private String name;
	private int balance;
	private int numOfWins;

	/**
	 * @author Bryce 'cybeR' Carson
	 * @param Gambler: its a gambler, a sinful, sinful gambler.
	 */
	public Player(Gambler gambler) {
		name = gambler.getName();
		balance = gambler.getBalance();
		numOfWins = gambler.getWins();
	}

	/**
	 * Constructor for player object
	 * 
	 * @param name
	 * @param balance
	 * @param wins
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public int getWins() {
		return numOfWins;
	}

	public String toString() {
		return name + "," + balance + "," + numOfWins;
	}
}

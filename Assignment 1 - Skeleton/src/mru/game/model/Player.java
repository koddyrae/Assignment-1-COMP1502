package mru.game.model;

import mru.game.controller.Gambler;

/** This class represent each player record in the Database It is basically a model class for each record in the txt file
 * @author Koddy Rae Madriaga
 */
public class Player {

	private String name;
	private int balance;
	private int numOfWins;

	/** A constructor for creating Players from Gamblers before dumping the player information to disk.
	 * @author Bryce 'cybeR' Carson
	 * @param gambler The gambler object from which to create the Player object.
	 */
	public Player(Gambler gambler) {
		name = gambler.getName();
		balance = gambler.getBalance();
		numOfWins = gambler.getWins();
	}

	/**
	 * Constructor for player object
	 * @author Koddy Rae Madriaga
	 * @param name The name of the player.
	 * @param balance The amount of money the player has.
	 * @param numOfWins The number of rounds of Punto Banco the player has won in their lifetime.
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}
 
	/** Get the player's name.
	 * @author Koddy Rae Madriaga
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/** Get the player's balance.
	 * @author Koddy Rae Madriaga
	 * @return balance
	 */
	public int getBalance() {
		return balance;
	}

	/** Get the count of how many games of Punto Banco the player has won.
	 * @author Koddy Rae Madriaga
	 * @return numOfWins
	 */
	public int getWins() {
		return numOfWins;
	}

	/** A stringified, pretty-printed object representation of Player class objects.
	 * @return name balance numOfwins 
	 */
	public String toString() {
		return name + "," + balance + "," + numOfWins;
	}
}

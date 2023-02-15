package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

/**
 * Class responsible for the functionality of gambler at the table in a game
 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
 */
public class Gambler {
	final private int BANKERS_BALANCE = -1;
	final private String BANKERS_NAME = "BANKER";

	private String name;
	private int balance = 0;
	private int numberOfWins = 0;
	private int score = 0;

	private boolean admittedToCasino = true; // True by default.

	public ArrayList<Card> hand = new ArrayList<Card>();

	/**
	 * @param isBanker the check if it is the banker
	 */
	public Gambler(boolean isBanker) {
		this.balance = BANKERS_BALANCE;
		this.name = BANKERS_NAME;
	}

	/**
	 * @param name the name of the gambler
	 */
	public Gambler(String name) {
		this.name = name;
		this.balance = 100;
		this.numberOfWins = 0;
	}

	/**
	 * @param player the player object being turned into a gambler
	 */
	public Gambler(Player player) {
		this.name = player.getName();
		this.balance = player.getBalance();
		this.numberOfWins = player.getWins();
	}

	/**
	 * @return score the calculated score of the gambler's hand
	 */
	public int scoreHand() {
		int score = 0;

		for (Card card : this.hand) {
			// Recall that Aces are ones, and have a value of one.
			// Therefore all cards less than ten are worth their rank.
			score += (card.getRank() <= 9) ? card.getRank() : 0;
		}

		return score % 10;
	}

	/**
	 * @param cardToAddToHand the current card being added to the hand
	 */
	public void addCardToHand(Card cardToAddToHand) {
		this.hand.add(cardToAddToHand);
	}

	/**
	 * Method to increment the number of wins by 1
	 */
	public void incrementNumberOfWins() {
		this.numberOfWins++;
	}

	/**
	 * @return name the name of the gambler
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return balance the balance of the gambler
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * @return numberOfWins the number of wins
	 */
	public int getWins() {
		return numberOfWins;
	}

	/**
	 * @param balance the balance of the gambler
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * @param wins the wins of the gambler
	 */
	public void setWins(int wins) {
		this.numberOfWins = wins;
	}

	/**
	 * @return hand the arraylist of the current hand
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/**
	 * @return score the score of the gambler
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score of the gambler
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return admittedToCasino the boolean to check if the player was allowed into the casino
	 */
	public boolean getAdmittedToCasino() {
		return this.admittedToCasino;
	}

	/**
	 * @param admittedToCasino the boolean to check if the player was allowed into the casino
	 */
	public void setAdmittedToCasino(boolean admittedToCasino) {
		this.admittedToCasino = admittedToCasino;
	}

	/**
	 * Method to check if two objects are the same
	 */
	@Override
	public boolean equals(Object o) {
		// Return true if the object is being compared with itself.
		if (this == o) {
			return true;
		}

		// Return false if the object is not of the same type.
		if (!(o instanceof Gambler)) {
			System.out.println(
					"DEBUG: Gambler.equals(Object o): you done called the equals method with something that couldn't be safely type casted to Gambler. Don't do that.");
			return false;
		} else {
			// Type cast and test the value of the name field.
			Gambler tempGambler = (Gambler) o;
			String thisName = this.getName().toUpperCase();
			String tempGamblerName = tempGambler.getName().toUpperCase();

			// Finally make the name field value comparison (use the
			// String.equals(String s) method).
			return thisName.equals(tempGamblerName);
		}
	}

	/**
	 * A bare-bones toString method for debugging purposes.
	 * 
	 * @author Bryce 'cybeR' Carson
	 * @return String the string to print.
	 */
	public String toString() {
		return this.getName() + " " + this.getBalance() + " " + this.getWins();
	}
}

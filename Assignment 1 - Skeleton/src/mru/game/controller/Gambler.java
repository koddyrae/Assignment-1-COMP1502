package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

/**
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

	public Gambler(boolean isBanker) {
		this.balance = BANKERS_BALANCE;
		this.name = BANKERS_NAME;
	}

	public Gambler(String name) {
		this.name = name;
		this.balance = 100;
		this.numberOfWins = 0;
	}

	public Gambler(Player player) {
		this.name = player.getName();
		this.balance = player.getBalance();
		this.numberOfWins = player.getWins();
	}

	public int scoreHand() {
		int score = 0;

		for (Card card : this.hand) {
			// Recall that Aces are ones, and have a value of one.
			// Therefore all cards less than ten are worth their rank.
			score += (card.getRank() <= 9) ? card.getRank() : 0;
		}

		return score % 10;
	}

	public void addCardToHand(Card cardToAddToHand) {
		this.hand.add(cardToAddToHand);
	}

	public void incrementNumberOfWins() {
		this.numberOfWins++;
	}

	public String getName() {
		return name;
	}

	public int getBalance() {
		return balance;
	}

	public int getWins() {
		return numberOfWins;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public void setWins(int wins) {
		this.numberOfWins = wins;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean getAdmittedToCasino() {
		return this.admittedToCasino;
	}

	public void setAdmittedToCasino(boolean admittedToCasino) {
		this.admittedToCasino = admittedToCasino;
	}

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

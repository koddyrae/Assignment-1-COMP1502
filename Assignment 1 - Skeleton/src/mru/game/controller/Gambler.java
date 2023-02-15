package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

/** A class representing gamblers at the casino. They have names, balances, scores, hands, how many games they've won, and whether or not they are allowed to enter the casino again in the future.
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

	/** The array list of Card objects that a player currently holds.
	 * @author Bryce 'cybeR' Carson 
	 */
	public ArrayList<Card> hand = new ArrayList<Card>();

	/** A constructor which is used only to create a banker for the player to play against.
	 * @author Bryce 'cybeR' Carson
	 * @param isBanker a boolean value indicating whether the gambler to instantiate represents a Banker. This method should not be used with the value of FALSE.
	 */
	public Gambler(boolean isBanker) {
		this.balance = BANKERS_BALANCE;
		this.name = BANKERS_NAME;
	}

	/** A constructor used to create a new player or a temporary Gambler for checking if a player happens to be a returning player.
	 * @author Bryce 'cybeR' Carson
	 * @param name The string to assign as the new gambler's name.
	 */
	public Gambler(String name) {
		this.name = name;
		this.balance = 100;
		this.numberOfWins = 0;
	}

	/** The player object for which to create a gambler from. This is used during program initialization, or whenever data is read from disk and Gambler objects are needed using on-disk data.
	 * @author Bryce 'cybeR' Carson
	 * @param player The player object from which to create the gambler.
	 */
	public Gambler(Player player) {
		this.name = player.getName();
		this.balance = player.getBalance();
		this.numberOfWins = player.getWins();
	}

	/** Score the player's hand according to the rules of Punto Banco.
	 * @author Bryce 'cybeR' Carson
	 * @return score The integer score of the player's hand of Card objects.
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

	/** Add a Card object to the player's hand.
	 * @author Bryce 'cybeR' Carson
	 * @param cardToAddToHand The card object to add to the player's hand.
	 */
	public void addCardToHand(Card cardToAddToHand) {
		this.hand.add(cardToAddToHand);
	}

	/** Increment the number of wins for this gambler.
	 * @author Bryce 'cybeR' Carson
	 */
	public void incrementNumberOfWins() {
		this.numberOfWins++;
	}

	/** Return the name of the player.
	 * @author Koddy Rae Madriaga 
	 * @return name the name of the player, a String.
	 */
	public String getName() {
		return name;
	}

	/** Return the balance of the player.
	 * @author Koddy Rae Madriaga
	 * @return balance, the integer balance of the player.
	 */ 
	public int getBalance() {
		return balance;
	}

	/** Return the integer number of wins of the player.
	 * @author Koddy Rae Madriaga
	 * @return numberOfWins, the integer number of wins of the player.
	 */
	public int getWins() {
		return numberOfWins;
	}

	/** Change the player's balance to given integer.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @param balance The new balance of the player.
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/** Change the player's balance to given integer.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @param wins The count of the rounds the player has won in their lifetime.
	 */
	public void setWins(int wins) {
		this.numberOfWins = wins;
	}

	/** Return the array list of cards of the player. The player's hand of cards.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @return hand, and an array list of cards.
	 */
	public ArrayList<Card> getHand() {
		return hand;
	}

	/** Return the player's score.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @return score, the integer score of the player; this is equivalent to the scoring of their hand, when used.
	 */
	public int getScore() {
		return score;
	}

	/** Set the player's score to a given integer.
	 * @author Koddy Rae Madriaga
	 * @param score The score to set for this player.
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/** Return a boolean indicating whether or not the player should be admitted to the casino.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @return admittedToCasino, a boolean
	 */
	public boolean getAdmittedToCasino() {
		return this.admittedToCasino;
	}

	/** Set whether or not the player should be admitted to the casino.
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @param admittedToCasino, a boolean indicating whether the player should be admitted to the casino in the future.
	 */
	public void setAdmittedToCasino(boolean admittedToCasino) {
		this.admittedToCasino = admittedToCasino;
	}

	/** Determine if the gambler objects should be considered equal (which is determined case-insensitively by their names only).
	 * @author Bryce 'cybeR' Carson
	 * @return true, if the names of the Gambler objects are equal.
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

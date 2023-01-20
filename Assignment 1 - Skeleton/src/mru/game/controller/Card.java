package mru.game.controller;

/**
 * This class represents a card
 * @author ksalmani
 * @version 1.0
 */
public class Card {

	/**
	 * rank represents the rank of the current card
	 */
	private int rank;
	
	/**
	 * suit represents the suit of the current card
	 */
	private String suit;

	/**
	 * This constructor sets the rank and suit of card
	 * @param rank shows the rank of the card
	 * @param suit shows the suit of the card
	 */
	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * rank getter method
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * rank setter method
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * suit getter method
	 * @return the suit
	 */
	public String getSuit() {
		return suit;
	}

	/**
	 * suit setter method
	 * @param suit the suit to set
	 */
	public void setSuit(String suit) {
		this.suit = suit;
	}

	/**
	 * This method overrides the toString method and shows the dat in the format we want
	 */
	public String toString() {
		
		String name = "King of ";
		
		if (rank >= 2 && rank <= 10)
			name = rank + " of ";
		else if (rank == 1)
			name = "Ace of ";
		else if (rank == 11)
			name = "Jack of ";
		else if (rank == 12)
			name = "Queen of ";
		
		return name + suit;
	}

}

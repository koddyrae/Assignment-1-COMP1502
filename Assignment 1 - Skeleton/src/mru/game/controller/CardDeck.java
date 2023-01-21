package mru.game.controller;

import java.util.ArrayList; // ie, how to avoid the init-0 problem.
import java.util.Collections;

/**
 * This class represents a card deck
 * @author ksalmani
 * @version 1.0
 */
public class CardDeck {
	
	/**
	 * deck holds all of the cards that currently are in the current deck
	 */
	private ArrayList <Card> deck;
	
	/**
	 * This constructor initiate the ArrayList and calls the respective methods to create a new deck
	 */
	public CardDeck() {
		deck = new ArrayList<Card>();
		createDeck();
		shuffleDeck();
	}

	/**
	 * This method creates the deck
	 */
	private void createDeck() {
		// suits holds the name of the suits
		String[] suits = {"Spades", "Diamond", "Clubs", "Hearts"};
		
		/*
		 * The while loop creates a whole new deck based on their suit and rank
		 */
		int i = 0, j = 1;
		while ((i < 4) && (j <= 13)) {
			i++; j++; // increment
			// e.g. new Card (0, 1); new Card (3, 12) => meaning: Ace of Spaces; Queen of Hearts.
			deck.add(new Card (j, suits[i]));
		}
		
	}

	/**
	 * this method shuffle the deck after creating a new deck
	 */
	private void shuffleDeck() {
		Collections.shuffle(deck); 
	}

	/**
	 * The deck getter method
	 * @return the deck
	 */
	public ArrayList<Card> getDeck() {
		return deck;
	}
}

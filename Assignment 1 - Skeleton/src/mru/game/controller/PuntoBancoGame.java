package mru.game.controller;

import java.util.ArrayList;

/**
 * 
 * @author Bryce 'cybeR' Carson
 *
 */
public class PuntoBancoGame {
	
	public ArrayList <Gambler> seatsAtTable;
	public CardDeck deck;

	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	public PuntoBancoGame(Gambler player) {
		this.deck = new CardDeck(); // Ensure a fresh deck of cards is available.
		this.seatsAtTable.add(new Gambler(true)); // Ensure a banker is at the table, awaiting players.
		// Call procedures to play out the round. The player and deck should be  enough information.
		
		this.seatsAtTable.add(new Gambler(false));
	}
	
	public void playRound(char betChoice, int betAmount) {
		// Deal 2 cards to the player and 2 cards to the Banker
		for(Gambler seat : this.seatsAtTable) {
			seat.hand.addAll(drawCards(2));
		}
		
		// Score hands (are they high?) and deal a third card if necessary.
		for(Gambler seat : this.seatsAtTable) {
			if(seat.scoreHand() >= 8) {
				break;
			} else {
				// TODO: deal additional cards according to the logic.
			}
		}

		// Score hands
		// Recall that BANKER sat at the table first.
		int bankerScore = this.seatsAtTable.get(0).scoreHand();
		int playerScore = this.seatsAtTable.get(1).scoreHand();

		// Bet successful?
		// TODO: FIXME: these cases are defensive and don't rely on pre-validated input.
		switch (betChoice) {
		case 'T': case 't':
			if (playerScore == bankerScore) {
				System.out.println("Player bet successfully!");
			} else {
				System.out.println("Player lost their bet.");
			}
			break;
		case 'P': case 'p':
			if (playerScore == bankerScore) {
				System.out.println("Player bet successfully!");
			} else {
				System.out.println("Player lost their bet.");
			}
			break;
		case 'B': case 'b':
			if (playerScore == bankerScore) {
				System.out.println("Player bet successfully!");
			} else {
				System.out.println("Player lost their bet.");
			}
			break;
		}
	}
	
	/**
	 * 
	 * @param numberOfCards The number of cards to get from the deck currently in play.
	 * @return ArrayList <Card> An ArrayList of Card(s) is returned, all of which can be added to a Gambler's hand.
	 */
	public ArrayList <Card> drawCards(int numberOfCards) {
		ArrayList <Card> cards = new ArrayList <Card>();
		for(int i = 0; i <= numberOfCards; i++) {
			if(deck.getDeck().size() >= 1) {
				cards.add(deck.getDeck().remove(0));
			} else {
				deck.createDeck();
				deck.shuffleDeck();
				cards.add(deck.getDeck().remove(0));
			}
			
		}
		return cards;
	}
}

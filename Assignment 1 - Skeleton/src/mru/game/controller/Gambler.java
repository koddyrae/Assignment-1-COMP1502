package mru.game.controller;

import java.util.ArrayList;

/**
 * 
 * @author Bryce 'cybeR' Carson
 *
 */
public class Gambler {
	public String name;
	public int saveFileLine;
	
	// No magic numbers! The banker's save file line could be any number; it is ignored. This avoids polymorphism.
	// IE "All gambler's are the same, but bankers can't be saved!"
	final private int BANKER$SAVE_FILE_LINE = -1;
	
	public ArrayList <Card> hand;
	
	public Gambler (boolean isBanker, CardDeck cardDeckOnTheTable, int saveFileLine) {
		if(isBanker || (saveFileLine == -1)) {
			// What do we do differently if the user of cards is a banker?
			// A banker has no name. Bankers are nameless pawns of the system of capitalism.
		}
		
		// TODO: set the Gambler's name after creation. It's a public variable, so we can name the Gambler post-hoc.
		// this.name = ;
		
		// There may be more than one card deck in the future; 
		takeCards(cardDeckOnTheTable);
	}

	public void takeCards(CardDeck deckOfCards) {
		ArrayList <Card> deck = deckOfCards.getDeck();
		int cardsRemainingInDeck = deck.size();
		
		
		switch (cardsRemainingInDeck) {
			case 0:
				// TODO: No cards remain in the deck. Implement.
				break;
			case 1:
				// TODO: Only one card remains in the deck. Implement.
				break;
			case 2:
				System.out.println("DEVEL: WARNING: switch statement case 2 entered; only two cards remain!");
				
				// Do not break because the remaining two cards will be returned by the default case below.
				// break;
			default: // Add two cards to the Gambler's hand.
				this.hand.add(deck.remove(cardsRemainingInDeck - 1));
				this.hand.add(deck.remove(cardsRemainingInDeck - 2));
		}
	}
}

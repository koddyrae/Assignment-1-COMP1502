package mru.game.controller;

/**
 * 
 * @author Bryce 'cybeR' Carson
 *
 */
public class PuntoBancoGame {
	
	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */
	public CardDeck cardDeckOnTheTable = new CardDeck();
	
	// A player is a person with a name, an entry in the saveFile file (a line number), and a hand of cards.
	public Gambler player = new Gambler(false, cardDeckOnTheTable, 0);
	// TODO: name the player.
	// player.name = ;
	
	// A banker is a capitalist collaborator with a title (no name), and a hand of cards.
	public Gambler banker = new Gambler(true, cardDeckOnTheTable, -1);
}

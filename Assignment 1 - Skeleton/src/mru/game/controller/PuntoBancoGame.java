package mru.game.controller;

import java.util.ArrayList;
import mru.game.view.*;

/**
 *
 * @author Bryce 'cybeR' Carson
 *
 */
public class PuntoBancoGame {

	public ArrayList<Gambler> seatsAtTable;
	public CardDeck deck;
	private Gambler player;
	private Gambler banker;
	private AppMenu appMenu;

	/**
	 * In this class you implement the game You should use CardDeck class here See
	 * the instructions for the game rules
	 */
	public PuntoBancoGame(Gambler player) {
		appMenu = new AppMenu(); // initializes appmenu
		this.deck = new CardDeck(); // Ensure a fresh deck of cards is available.
		this.player = player;
		this.banker = new Gambler(true); // Create a banker.
	}

	/**
	 * Method that calculates all the logic of the Punto Banco
	 * 
	 * @param betChoice user inputted bet (Player, Banker, Tie)
	 * @param betAmount user inputted bet amount
	 */
	public void playRound(char betChoice, int betAmount) {
		// Deduct the wager from the player's balance.
		player.setBalance(player.getBalance() - betAmount);

		// Deal 2 cards to the player and 2 cards to the Banker
		player.hand.addAll(drawCards(2));
		banker.hand.addAll(drawCards(2));

		// Score hands
		int playerScore = player.scoreHand();
		int bankerScore = banker.scoreHand();

		// If both hands are low, more cards are dealt according to the rules.
		// Overall, the conditional logic is if(if-else), with switch-cases in the
		// interior if.
		if (!(playerScore >= 8 || bankerScore >= 8)) {
			if (playerScore >= 0 && playerScore <= 5) {
				// Player draws a third card.
				player.hand.addAll(drawCards(1));

				// Banker draws a third card depending on the rank of the player's third card.
				switch (player.hand.get(2).getRank()) {
				case 2:
				case 3:
					if (bankerScore >= 0 && bankerScore <= 4) {
						// Banker draws a third card.
						banker.hand.addAll(drawCards(1));
					}
					break;
				case 4:
				case 5:
					if (bankerScore >= 0 && bankerScore <= 5) {
						// Banker draws a third card.
						banker.hand.addAll(drawCards(1));
					}
					break;
				case 6:
				case 7:
					if (bankerScore >= 0 && bankerScore <= 6) {
						// Banker draws a third card.
						banker.hand.addAll(drawCards(1));
					}
					break;
				case 8:
					if (bankerScore >= 0 && bankerScore <= 2) {
						// Banker draws a third card.
						banker.hand.addAll(drawCards(1));
					}
					break;
				case 1:
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
					if (bankerScore >= 0 && bankerScore <= 3) {
						// Banker draws a third card.
						banker.hand.addAll(drawCards(1));
					}
					break;
				}
			} else if (bankerScore >= 0 && bankerScore <= 5) {
				// Banker draws a third card without the player having drawn a third card.
				banker.hand.addAll(drawCards(1));
			}
		}

		// Score hands and set the score field for each Gambler object for use in
		// roundDisplay.
		banker.setScore(banker.scoreHand());
		player.setScore(player.scoreHand());

		// Bet successful?
		switch (betChoice) {
		case 't':
			if (playerScore == bankerScore) {
				player.setBalance(player.getBalance() + 6 * betAmount);
				appMenu.roundDisplay(player, banker, 5 * betAmount, true);
			} else {
				appMenu.roundDisplay(player, banker, betAmount, false);
			}

			break;
		case 'p':
			if (playerScore > bankerScore) {
				player.setBalance(player.getBalance() + 2 * betAmount);
				appMenu.roundDisplay(player, banker, betAmount, true);
			} else {
				appMenu.roundDisplay(player, banker, betAmount, false);
			}

			break;
		case 'b':
			if (playerScore < bankerScore) {
				player.setBalance(player.getBalance() + 2 * betAmount);
				appMenu.roundDisplay(player, banker, betAmount, true);
			} else {
				appMenu.roundDisplay(player, banker, betAmount, false);
			}

			break;
		}

		// Discard the hands of the player and the banker.
		player.hand.removeAll(player.hand);
		banker.hand.removeAll(banker.hand);
	}

	/**
	 * @author Bryce 'cybeR' Carson
	 * @param numberOfCards The number of cards to get from the deck currently in
	 *                      play.
	 * @return ArrayList <Card> An ArrayList of Card(s) is returned, all of which
	 *         can be added to a Gambler's hand.
	 */
	private ArrayList<Card> drawCards(int numberOfCards) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i <= numberOfCards; i++) {
			if (deck.getDeck().size() >= 1) {
				cards.add(deck.getDeck().remove(0));
			} else {
				this.deck = new CardDeck();
				cards.add(deck.getDeck().remove(0));
			}
		}
		return cards;
	}
}

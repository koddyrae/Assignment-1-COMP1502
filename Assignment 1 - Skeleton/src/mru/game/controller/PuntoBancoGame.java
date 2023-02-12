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
	private AppMenu appMenu;

	/**
	 * In this class you implement the game You should use CardDeck class here See
	 * the instructions for the game rules
	 */
	public PuntoBancoGame(Gambler player) {
		appMenu = new AppMenu(); //initializes appmenu
		this.deck = new CardDeck(); // Ensure a fresh deck of cards is available.
		this.player = player;
		this.seatsAtTable = new ArrayList<>();
		this.seatsAtTable.add(new Gambler(true)); // Ensure a banker is at the table, awaiting players.
		this.seatsAtTable.add(player);
	}

	public void playRound(char betChoice, int betAmount) {
		int bankerScore = 0;
		int playerScore = 0;

		// Deduct the wager from the player's balance.
		// Recall that the player sits at the table second.
		this.seatsAtTable.get(1).balance -= betAmount;

		// Deal 2 cards to the player and 2 cards to the Banker
		for (Gambler seat : this.seatsAtTable) {
			seat.hand.addAll(drawCards(2)); // TODO:needs to be fixed, seat.hand is null
		}

		// Score hands (are they high?) and deal a third card if necessary.
		for (Gambler seat : this.seatsAtTable) {
			if (seat.scoreHand() >= 8) {
				break;
			} else {
				// Score hands
				// Recall that BANKER sat at the table first.
				bankerScore = this.seatsAtTable.get(0).scoreHand();
				playerScore = this.seatsAtTable.get(1).scoreHand();

				if (playerScore >= 0 && playerScore <= 5) {
					ArrayList<Card> playerHand = this.seatsAtTable.get(1).hand;
					// Player draws a third card.
					playerHand.addAll(drawCards(1));

					// Banker draws a third card, conditionally.
					switch (playerHand.get(2).getRank()) {
					case 2:
					case 3:
						if (bankerScore >= 0 && bankerScore <= 4) {
							// Banker draws a third card.
							this.seatsAtTable.get(0).hand.addAll(drawCards(1));
						}
						break;
					case 4:
					case 5:
						if (bankerScore >= 0 && bankerScore <= 5) {
							// Banker draws a third card.
							this.seatsAtTable.get(0).hand.addAll(drawCards(1));
						}
						break;
					case 6:
					case 7:
						if (bankerScore >= 0 && bankerScore <= 6) {
							// Banker draws a third card.
							this.seatsAtTable.get(0).hand.addAll(drawCards(1));
						}
						break;
					case 8:
						if (bankerScore >= 0 && bankerScore <= 2) {
							// Banker draws a third card.
							this.seatsAtTable.get(0).hand.addAll(drawCards(1));
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
							this.seatsAtTable.get(0).hand.addAll(drawCards(1));
						}
						break;
					default:
						System.out.println(
								"DEVEL: DEBUG: ERROR: the default case should never be reached during normal operation.");
					}
				} else if (bankerScore >= 0 && bankerScore <= 5) {
					// Banker draws a third card.
					this.seatsAtTable.get(0).hand.addAll(drawCards(1));
				}
			}
		}

		// Score hands
		// Recall that BANKER sat at the table first.
		bankerScore = this.seatsAtTable.get(0).scoreHand();
		this.seatsAtTable.get(0).setScore(bankerScore);
		playerScore = this.seatsAtTable.get(1).scoreHand();
		this.seatsAtTable.get(1).setScore(playerScore);

		// Bet successful?
		switch (betChoice) {
		case 't':
			if (playerScore == bankerScore) {
				player.setBalance(player.getBalance() + 6 * betAmount);
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), 5 * betAmount, true);
			} else {
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), betAmount, false);
			}
			break;
		case 'p':
			if (playerScore > bankerScore) {
				player.setBalance(player.getBalance() + 2 * betAmount);
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), betAmount, true);

			} else {
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), betAmount, false);
			}
			break;
		case 'b':
			if (playerScore < bankerScore) {
				player.setBalance(player.getBalance() + 2 * betAmount);
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), betAmount, true);
			} else {
				appMenu.roundDisplay(player, this.seatsAtTable.get(0), betAmount, false);
			}
			break;
		}
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

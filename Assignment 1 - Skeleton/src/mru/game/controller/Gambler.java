package mru.game.controller;

import java.util.ArrayList;

/**
 * @author Bryce 'cybeR' Carson
 */
public class Gambler {
    final private int BANKERS_BALANCE = -1;
    final private String BANKERS_NAME = "BANKER";

    private String name = BANKERS_NAME;
    public int balance = 0;
    private int numberOfWins = 0;

    public ArrayList <Card> hand;

    public Gambler (boolean isBanker) {
        this.balance = BANKERS_BALANCE;
    }

    public Gambler (String name, int balance, int numberOfWins) {
        this.name = name;
        this.balance = balance;
        this.numberOfWins = numberOfWins;
    }

    public int scoreHand() {
        int score = 0;

        for(Card card : this.hand) {
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


}

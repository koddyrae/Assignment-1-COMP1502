package mru.game.controller;

import java.util.ArrayList;

import mru.game.model.Player;

/**
 * @author Bryce 'cybeR' Carson
 */
public class Gambler {
    final private int BANKERS_BALANCE = -1;
    final private String BANKERS_NAME = "BANKER";

    private String name = BANKERS_NAME;
    public int balance = 0;
    private int numberOfWins = 0;
    private int score = 0;

    private boolean admittedToCasino = true; // True by default.

    public ArrayList <Card> hand = new ArrayList<Card>();

    public Gambler (boolean isBanker) {
        this.balance = BANKERS_BALANCE;
    }

    public Gambler (String name) {
        this.name = name;
        this.balance = 100;
        this.numberOfWins = 0;
    }

    public Gambler (Player player) {
        this.name = player.getName();
        this.balance = player.getBalance();
        this.numberOfWins = player.getWins();
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

    public ArrayList <Card> getHand() {
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

    public boolean equals(Gambler anyGambler) {
        return this.getName() == anyGambler.getName();
    }
}

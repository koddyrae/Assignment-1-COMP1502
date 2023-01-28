package mru.game.model;

/**
 * @author Koddy Madriaga
 *
 */
public class Player {
	
	/**
	 * This class represent each player record in the Database
	 * It is basically a model class for each record in the txt file
	 */
	private String name;
	private int balance;
	private int numOfWins;
	
	/**
	 * Default constructor for player object
	 */
	
	public Player() {
		
	}
	
	/**
	 * Constructor for player object
	 * @param name
	 * @param balance
	 * @param wins
	 */
	public Player(String name, int balance, int numOfWins) {
		this.name = name;
		this.balance = balance;
		this.numOfWins = numOfWins;
	}
	
}

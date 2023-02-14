package mru.game.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Card;
import mru.game.controller.Gambler;
import mru.game.model.Player;

class GamblerTest {
	Gambler banker;
	Gambler testGambler;
	Gambler testPlayer;
	Player player;
	Card testCard;
	
	@BeforeEach
	public void setUp() {
		banker = new Gambler(true); // Create a banker.
		testGambler = new Gambler("Banker");
		player = new Player("ah", 10, 10);
		testPlayer = new Gambler(player);
		testCard = new Card(11, "Diamond");
		testGambler.addCardToHand(testCard);
	}
	
	@Test
	void testEquals() {
		assertEquals(true, banker.equals(testGambler));
	}
	
	@Test
	void testGambler() {
		assertNotEquals(player.toString(), testPlayer.toString());
	}

	@Test 
	void testScoreHand() {
		assertEquals(0, testGambler.scoreHand());
	}

	@Test
	void testAddCardToHand() {
		assertNotNull(testGambler.getHand());	
	}
	
	@Test
	void testSetAndGetBalance() {
		testGambler.setBalance(100);
		assertEquals(100, testGambler.getBalance());
	}
	
	@Test
	void testSetAndGetWins() {
		testGambler.setWins(10);
		assertEquals(10, testGambler.getWins());
	}
	
	@Test
	void testSetAndGetScore() {
		testGambler.setScore(10);
		assertEquals(10, testGambler.getScore());
	}
	
	@Test 
	void testSetAndGetAdmitted() {
		testGambler.setAdmittedToCasino(true);
		assertTrue(testGambler.getAdmittedToCasino());
	}
	
	@Test
	void testIncrementWins() {
		testGambler.incrementNumberOfWins();
		assertEquals(1, testGambler.getWins());
	}
}

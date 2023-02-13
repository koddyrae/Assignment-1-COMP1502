package mru.game.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Gambler;
import mru.game.model.Player;

class PlayerTest {
	Player test;
	Gambler test1;
	
	@BeforeEach
	void setUp() {
		test = new Player("Test", 10, 10);
		test1 = new Gambler("Test");
	}

	@Test
	void testGetPlayerName() {
		assertEquals("Test", test.getName());
	}
	
	@Test
	void testGetPlayerBalance() {
		assertEquals(10, test.getBalance());
	}
	
	@Test
	void testGetPlayerWins() {
		assertEquals(10, test.getWins());
	}
	
	@Test
	void testPlayerFromGambler() {
		Player test2 = new Player(test1);
		assertNotNull(test2);
	}
	
	@Test
	void testToString() {
		assertEquals("Test,10,10", test.toString());
	}
}

package mru.game.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import mru.game.controller.Gambler;
import mru.game.model.Player;

class GamblerTest {
	Gambler banker;
	Gambler testGambler;
	Gambler testPlayer;
	Player player;
	
	@BeforeEach
	public void setUp() {
		banker = new Gambler(true); // Create a banker.
		testGambler = new Gambler("Banker");
		player = new Player("ah", 10, 10);
		// A non-(financial)-banker gambler named Banker; named after one who banks, as in a pilot banking the plane in a given direction. Ask their parents, not me.
		testPlayer = new Gambler(player);
	}
	
	@Test
	void testEquals() {
		assertEquals(true, banker.equals(testGambler));
	}
	
	@Test
	void testGambler() {
		assertNotEquals(player.toString(), testPlayer.toString());
	}

}

package mru.game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Gambler;

class GamblerTest {
	Gambler banker;
	Gambler testGambler;
	
	@BeforeEach
	public void initializeGamblers() {
		this.banker = new Gambler(true); // Create a banker.
		this.testGambler = new Gambler("Banker"); // A non-(financial)-banker gambler named Banker; named after one who banks, as in a pilot banking the plane in a given direction. Ask their parents, not me.
	}

	@Test
	void testEquals(Gambler banker, Gambler testGambler) {
		assertEquals(banker, testGambler);
	}

}

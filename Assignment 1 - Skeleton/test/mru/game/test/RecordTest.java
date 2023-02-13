package mru.game.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

import mru.game.controller.Gambler;
import mru.game.model.Player;
import mru.game.model.Record;

import org.junit.jupiter.api.Test;

class RecordTest {
	Record db;
	ArrayList<Player> listOfTestPlayers;
	ArrayList<Gambler> listOfTestGamblers;
	
	@BeforeEach
	void setUp() throws IOException {
		db = new Record();
		listOfTestPlayers = new ArrayList<>();
		listOfTestPlayers.add(new Player("Eli", 1000, 18));
		listOfTestGamblers = new ArrayList<>();
		listOfTestGamblers.add(new Gambler("koddy"));
		listOfTestGamblers.add(new Gambler("koddy"));
	}
	
	@Test
	void testFindTopPlayers() throws IOException {
		assertEquals("debugMeSenpai,666,", db.findTopPlayers());
		assertNotNull(db.findTopPlayers());
	}

	@Test 
	void testFindPlayersByName() throws IOException {
		assertEquals("debugMeSenpai,0,666,", db.findPlayersByName("debugMeSenpai"));
	}
	
	@Test
	void testDoesPlayerExist() {
		assertTrue("koddy", db.doesPlayerExist("koddy"));
		//assertEquals(true, db.doesPlayerExist("koddy"));
	}
	
	@Test
	void testGetPatrons() throws IOException {
		assertNotNull(db.getPatrons());
		
	}
	
	@Test
	void testGetPlayer() {
		assertNotNull(db.doesPlayerExist("ali"));
		assertNull(db.getPlayer("koddy")); //why am i showing up as null
	}
	
	@Test
	void testSaveTextFile() {
		
	}
}

package mru.game.test;

import static org.junit.Assert.assertFalse;
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
		assertEquals("Khosro,18,Eli,18,", db.findTopPlayers());
		assertNotNull(db.findTopPlayers());
	}

	@Test 
	void testFindPlayersByName() throws IOException {
		assertEquals("Khosro,1639,18,", db.findPlayersByName("khosro"));
	}
	
	@Test
	void testDoesPlayerExist() {
		assertFalse("koddy", db.doesPlayerExist("koddy"));
		//assertEquals(true, db.doesPlayerExist("koddy"));
	}
	
	@Test
	void testGetPatrons() throws IOException {
		assertNotNull(db.getPatrons());
		
	}
	
	@Test
	void testGetPlayer() {
		assertNotNull(db.doesPlayerExist("ali"));
		assertNull(db.getPlayer("koddy"));
	}
	
	@Test
	void testSaveTextFile() {
		// i dont know how to test this
	}
}

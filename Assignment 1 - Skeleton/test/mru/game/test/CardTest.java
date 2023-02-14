package mru.game.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.Card;

class CardTest {
	Card test;
	Card test1;
	Card test2;
	Card test3;
	
	@BeforeEach
	void setUp() {
		test = new Card(10, "Diamond");
		test1 = new Card(1, "Diamond");
		test2 = new Card(11, "Diamond");
		test3 = new Card(12, "Diamond");
	}
	
	@Test
	void testGetRank() {
		assertEquals(10, test.getRank());
	}
	
	@Test
	void testSetRank() {
		test.setRank(11);
		assertEquals(11, test.getRank());
	}
	
	@Test 
	void testGetSuit() {
		assertEquals("Diamond", test.getSuit());
	}
	
	@Test
	void testSetSuit() {
		test.setSuit("Spade");
		assertEquals("Spade", test.getSuit());
	}
	
	@Test 
	void testToString() {
		assertEquals("10 of Diamond", test.toString());
		assertEquals("Ace of Diamond", test1.toString());
		assertEquals("Jack of Diamond", test2.toString());
		assertEquals("Queen of Diamond", test3.toString());
	}
}

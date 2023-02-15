package mru.game.test;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mru.game.controller.CardDeck;

class CardDeckTest {
	CardDeck test;
	
	@BeforeEach
	void setUp() {
		test = new CardDeck();
	}
	
	@Test
	void testGetDeck() {
		assertNotNull(test.getDeck());
	}
}

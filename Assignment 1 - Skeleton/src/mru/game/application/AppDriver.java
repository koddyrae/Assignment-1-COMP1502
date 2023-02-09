package mru.game.application;

import mru.game.controller.GameManager;

public class AppDriver {

	public static void main(String[] args) {

		/**
		 * @author Bryce 'cybeR' Carson
		 * 
		 * FIXME: It feels very wrong to have a separate class contain the main loop for
		 * the game. There is also the question of how a main loop operates when it is
		 * contained inside a constructor.
		 * 
		 * NOTE: there is no need to assign this to a global variable because the object
		 * will not be reused. It is an anonymous object which is the main program loop.
		 */
		new GameManager(); // Contains the main loop.
	}

}

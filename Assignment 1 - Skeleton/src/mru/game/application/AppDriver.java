package mru.game.application;

import java.io.IOException;

import mru.game.controller.GameManager;

/** The containing class for the main program method.
 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
 */
public class AppDriver {

	/**
	 * The main method of the program.
	 * @param args The command-line arguments of the program. They are not used.
	 * @throws IOException The application uses unhandled File access. Sorry, we haven't studied try-catch in depth.
	 */
	public static void main(String[] args) throws IOException {

		/*
		 * NOTE: there is no need to assign this to a global variable because the object
		 * will not be reused. It is an anonymous object which is the main program loop.
		 */
		new GameManager();
	}

}

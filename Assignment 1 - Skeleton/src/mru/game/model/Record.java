package mru.game.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Gambler;

/** A class which represents the on-disk database and contains all methods for interacting with it.
 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
 */
public class Record {
	final private String FILE_PATH = "res/CasinoInfo.txt";

	private ArrayList<Player> listOfPlayers;

	/**
	 * Constructor for record holding the listOfPlayers arraylist
	 * @throws IOException If the text file cannot be read, an IOException will be thrown.
	 */
	public Record() throws IOException {
		this.listOfPlayers = loadTextFile();
	}

	/** Get a Player object from the records by name.
	 * @author Bryce 'cybeR' Carson
	 * @param name the upper case name of the Player object to get.
	 * @return result
	 */
	public Player getPlayer(String name) {
		Player result = null;
		for (Player player : listOfPlayers) {
			if (player.getName().toUpperCase().equals(name)) {
				result = player; // this line isn't getting accessed in the test
			}
		}
		return result;
	}

	/**
	 * Loads a text file from disk, if it exists. If the file does not exist, it is created.
	 * 
	 * @author Koddy Rae Madriaga
	 * @throws FileNotFoundException, IOException the exception thrown in case of file not found in file path or errors in the scanner
	 * @return ArrayList<Player> an array list of player objects.
	 */
	private ArrayList<Player> loadTextFile() throws FileNotFoundException, IOException {
		File file = new File(FILE_PATH);

		ArrayList<Player> playerRecordsFromDisk = new ArrayList<Player>();

		if (file.exists()) {
			FileReader fr = new FileReader(FILE_PATH);
			Scanner scanner = new Scanner(fr);

			while (scanner.hasNextLine()) {
				String[] lineData = scanner.nextLine().split(",", 3);
				Player player = new Player(lineData[0], Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]));

				// Add the player to the list in the global scope.
				playerRecordsFromDisk.add(player);
			}

			scanner.close();

			return playerRecordsFromDisk;

		} else {
			File newFile = new File("res/CasinoInfo.txt");
			newFile.createNewFile();
			return new ArrayList<Player>();
		}
	}

	/**
	 * Method used to save the listOfPlayers arraylist to a text file called CasinoInfo in the res folder
	 * 
	 * @author Koddy Rae Madriaga, Bryce 'cybeR' Carson
	 * @param casinoPatrons The array list of Gambler objects which tracks all of the player's known to the application, both on-disk and in-memory.
	 * @throws FileNotFoundException If the file cannot be written to a FileNotFoundException will be thrown.
	 */
	public void saveTextFile(ArrayList<Gambler> casinoPatrons) throws FileNotFoundException {
		// NOTE: The simplest constructor is the correct one; this overrides data to the
		// specified file, exactly what we want.
		PrintWriter pw = new PrintWriter(FILE_PATH);

		for (Gambler gambler : casinoPatrons) {
			pw.println(new Player(gambler));
		}

		// Tidy up after ourselves.
		pw.close();
	}

	/** Ascertain whether the player exists in the database.
	 * @author Bryce 'cybeR' Carson
	 * @param name The name to search for in the Database.
	 * @return exist
	 */
	public boolean doesPlayerExist(String name) {
		boolean exist = false;
		for (Player player : listOfPlayers) {
			if (player.getName().toUpperCase().equals(name.toUpperCase())) {
				exist = true;
			}
		}
		return exist;
	}

	/**
	 * Method to return the player(s) in the arraylist by name
	 * 
	 * @author Koddy Rae Madriaga
	 * @param name user inputted search name
	 * @return name that contains the user input
	 * @throws IOException the exception thrown in case of errors in loadTextFile
	 */
	public String findPlayersByName(String name) throws IOException {
		String result = "";

		// Reload the data from disk before we use it.
		loadTextFile();

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(i).getName().toUpperCase().equals(name.toUpperCase())) {
				result += listOfPlayers.get(i).toString() + ",";
			}
		}
		return result;
	}

	/**
	 * Method to return the top player(s) in the arraylist by wins
	 * 
	 * @author Koddy Rae Madriaga
	 * @return results the top player(s) based on order from arraylist
	 */
	public String findTopPlayers() {
		String result = "";

		int max = listOfPlayers.get(0).getWins();

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (max < listOfPlayers.get(i).getWins()) {
				max = listOfPlayers.get(i).getWins();
			}
		}

		for (int j = 0; j < listOfPlayers.size(); j++) {
			if (max == listOfPlayers.get(j).getWins()) {
				result += listOfPlayers.get(j).getName() + "," + listOfPlayers.get(j).getWins() + ",";
			}
		}

		return result;

	}

	/**
	 * Method to return an arraylist of current patrons
	 * @author Bryce 'cybeR' Carson
	 * @return patrons The array list of gamblers that are patrons of the casino.
	 * @throws IOException If the database could not be loaded an IOException will occur.
	 */
	public ArrayList<Gambler> getPatrons() throws IOException {

		// Update the listOfPlayers from disk.
		ArrayList<Player> recordsLoadedFromDisk = loadTextFile();

		ArrayList<Gambler> patrons = new ArrayList<Gambler>();
		for (Player record : recordsLoadedFromDisk) {
			patrons.add(new Gambler(record)); // Create a gambler from the data on record.
		}

		return patrons;
	}

}

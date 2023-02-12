package mru.game.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Gambler;

/**
 * Class for all file handling done in project
 * @author Bryce 'cybeR' Carson, Koddy Rae Madriaga
 *
 */
public class Record {
  private String filename;

  private ArrayList <Player> listOfPlayers = new ArrayList<Player>();


  public Record(String filename) throws IOException {
    this.filename = filename;
    loadTextFile(); // NOTE: setListOfPlayers is done in this method without a setter because it is done privately.
  }

    /**
     * @author Bryce 'cybeR' Carson
     * @param name: an uppercase String of the player object to get.
     * @return Player: a Player object
     */
    public Player getPlayer(String name) {
    	Player result = null;
        for(Player player : listOfPlayers) {
            if(player.getName().toUpperCase().equals(name)) {
            	result = player;
            }
        }
        return result;
    }

	/**
	 * User inputs a file name and if it exists, it will load a Player arraylist
	 * @author Koddy Rae Madriaga 
	 * @param filename, user inputed filename
	 * @throws IOException throws exception for problems that can occur with scanner and filereader
	 *
	 */
	private void loadTextFile() throws IOException {
		File file = new File(this.filename);

		if (file.exists()) {
			FileReader fr = new FileReader(filename);
			Scanner scanner = new Scanner(fr);

			while (scanner.hasNextLine()) {
				String[] lineData = scanner.nextLine().split(",", 3);
				Player player = new Player(lineData[0], Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]));

				// Add the player to the list in the global scope.
				this.listOfPlayers.add(player);
			}

			scanner.close();
		} else {
			File newFile = new File("res/CasinoInfo.txt");
			newFile.createNewFile();
		}
	}

    /**
     * @author Koddy Rae Madriaga
     * @contributor Bryce 'cybeR' Carson
     * Method used to save the listOfPlayers arraylist to a text file called CasinoInfo in the res folder
     * @throws IOException for IO problems that can occur with the fileoutputstream and printwriter
     */
    public void saveTextFile(ArrayList <Gambler> casinoPatrons) throws IOException {

        // Restructure the data we need into what we care about for on-disk storage.
        ArrayList <Player> patronData =	new ArrayList<Player>();
        
        for(Gambler gambler : casinoPatrons) {
            patronData.add(new Player(gambler));
        }
        
        FileOutputStream fos = new FileOutputStream("res/CasinoInfo.txt");
        PrintWriter pw = new PrintWriter(fos);
        
        for (Player patron : patronData) {
        	pw.println(patron);
        }
        
        pw.close();
        fos.close();
    }


    /**
     * @author Bryce 'cybeR' Carson
     * @param String name: the name to search for in the Database.
     */
    public boolean doesPlayerExist(String name) {
    	boolean exist = false;
        for(Player player : listOfPlayers) {
            if(player.getName().toUpperCase().equals(name.toUpperCase())) {
            	exist = true;
            }
        }
        return exist;
    }


	/**
	 * Method to return the player(s) in the arraylist by name
	 * @author Koddy Rae Madriaga
	 * @param name user inputted search name
	 * @return name that contains the user input
	 */
	public String findPlayersByName(String name) {
		String result = "";

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(i).getName().toUpperCase().equals(name.toUpperCase())) {
				result += listOfPlayers.get(i).toString() + ",";
			}
		}
		return result;
	}
	
	/**
	 * Method to return the top player(s) in the arraylist by wins
	 * @author Koddy Rae Madriaga 
	 * @return top player(s) based on order from arraylist
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
     * Method to return an arraylist of gamblers
     * @return arraylist of gamblers
     */
    public ArrayList <Gambler> getPatrons() {
        ArrayList <Gambler> patrons = new ArrayList<Gambler>();
        for(Player record : listOfPlayers) {
            patrons.add(new Gambler(record)); // Create a gambler from the data on record.
        }

        return patrons;
    }

}

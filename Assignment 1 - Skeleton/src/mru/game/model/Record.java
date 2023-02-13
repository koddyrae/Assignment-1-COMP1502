package mru.game.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import mru.game.controller.Gambler;

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
	 * @author Koddyrae User inputs a file name and if it exists, it will load a
	 *         Player arraylist
	 * @param filename, user inputed filename
	 * @throws IOException
	 *
	 */
	private void loadTextFile() throws IOException {
		File file = new File(this.filename);

		if (file.exists()) {
			FileReader fr = new FileReader(filename);
			Scanner scanner = new Scanner(fr);

			// Empty the list of players if it already exits.
			if(listOfPlayers.size() > 0) {
				listOfPlayers.removeAll(listOfPlayers);
			}
			
			// (Re-)populate the list of players from disk.
			while (scanner.hasNextLine()) {
				String[] lineData = scanner.nextLine().split(",", 3);
				Player player = new Player(lineData[0], Integer.parseInt(lineData[1]), Integer.parseInt(lineData[2]));

				// Add the player to the list in the global scope.
				listOfPlayers.add(player);
			}

			scanner.close();
		} else {
			File newFile = new File("res/CasinoInfo.txt");
			newFile.createNewFile();
		}
	}

    /**
     * @author Koddyrae
     * @contributor Bryce 'cybeR' Carson
     * Method used to save the listOfPlayers arraylist to a text file called CasinoInfo in the res folder
     * @throws IOException
     * TODO: this must be re-written using methods and packages we've been shown. He haven't been shown try-catch, so we can't use it.
     */
    public void saveTextFile(ArrayList <Gambler> casinoPatrons) throws IOException {

        // Restructure the data we need into what we care about for on-disk storage.
        ArrayList <Player> patronData =	new ArrayList<Player>();
        for(Gambler gambler : casinoPatrons) {
            patronData.add(new Player(gambler));
        }

        // Write the data to disk, overwriting any existing data.
        // FIXME: this does not overwrite data, it appends.
        // FIXME: because we were not explicitly taught try-catch, we need to simply use "throws IOException", and not use try-catch. This needs to be addressed program-wide. NO try-catch, Koddy, buddy, unless I'm wrong and we were shown it.
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            PrintWriter pw = new PrintWriter(fos);

            for (Player patron : patronData) {
                pw.println(patron);
            }

            // Tidy up after ourselves.
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
	 * @author Koddyrae Method to return the player(s) in the arraylist by name
	 * @param name user inputted search name
	 * @return name that contains the user input
	 * @throws IOException 
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
	 * @author Koddyrae Method to return the top player(s) in the arraylist by wins
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

    public ArrayList <Gambler> getPatrons() throws IOException {

        // Update the listOfPlayers from disk.
        loadTextFile();

        ArrayList <Gambler> patrons = new ArrayList<Gambler>();
        for(Player record : listOfPlayers) {
            patrons.add(new Gambler(record)); // Create a gambler from the data on record.
        }

        // FIXME: DEBUG: print the patrons.
        System.out.println("DEBUG: Record.getPatrons(): this is the listOfPlayers that was loaded, and converted into casinoPatrons (Gambler arrayList).");
        for(Gambler patron : patrons) {
        	System.out.println(patron);
        }
        System.out.println("");
        
        return patrons;
    }

}

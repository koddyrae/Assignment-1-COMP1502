package mru.game.model;

import java.io.*;
import java.util.ArrayList;

public class Record {
  private String filename;

  private ArrayList <Player> listOfPlayers;

  public Record(String filename) throws IOException {
    this.filename = filename;
    loadTextFile(); // NOTE: setListOfPlayers is done in this method without a setter because it is done privately.
  }

  /**
     * @author Koddyrae
     * User inputs a file name and if it exists, it will load a Player arraylist
     * @param filename,  user inputed filename
     * @throws IOException
     *
     */
    private void loadTextFile() throws IOException {
        File file = new File(this.filename);

        if (file.exists()) {
            FileReader fr = new FileReader(filename);
            try (BufferedReader br = new BufferedReader(fr)) {
        /* NOTE: the line should be initialized outside of the condition so
           that the condition does not act as a value-setting function.
           Stylistically, this is easier to read, Khoddy, but it also avoids
           the tendency to create objects or values inside a condition which
           will be run repeatedly.*/
        String line = br.readLine();
        while(line != null) {
            /* NOTE: I renamed this variable because it is a dependent of
               line, so it should be named using suffixing. It also refers
               to a line of data, rather than a data-carrying line (as
               opposed to a kind of object called lines that may or may not
               hold data.)*/
            String[] lineData = line.split(",");
            Player player = new Player(lineData[0],
                                       Integer.parseInt(lineData[1]),
                                       Integer.parseInt(lineData[2]));

            // Add the player to the list in the global scope.
            listOfPlayers.add(player);
        }
      } catch (NumberFormatException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
        }
        else {
            File newFile = new File("res/CasinoInfo.txt");
            newFile.createNewFile();
        }

    }

    /**
     * @author Bryce 'cyberR' Carson
     * This method takes a Gambler and uses it to update the Gambler's record

    /**
     * @author Koddyrae
     * Method used to save the listOfPlayers arraylist to a text file called CasinoInfo in the res folder
     * @throws IOException
     * TODO: this must be re-written using methods and packages we've been shown. He haven't been shown try-catch, so we can't use it.
     */
    public void saveTextFile() throws IOException {
        try {
            FileOutputStream fos = new FileOutputStream("res/CasinoInfo.txt");
            PrintWriter pw = new PrintWriter(fos);

            for (Player player : listOfPlayers) {
                pw.println(player.toString());

            }
            pw.close();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
	/**
	 * @author Koddyrae Method to return the player(s) in the arraylist by name
	 * @param name user inputted search name
	 * @return name that contains the user input
	 */
	public String findPlayersByName(String name) {
		String result = "";

		for (int i = 0; i < listOfPlayers.size(); i++) {
			if (listOfPlayers.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
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

}

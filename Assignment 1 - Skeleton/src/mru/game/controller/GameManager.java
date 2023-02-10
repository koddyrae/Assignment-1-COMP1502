package mru.game.controller;

import java.io.*;
import java.util.ArrayList;

import mru.game.model.Player;
import mru.game.model.Record;
import mru.game.view.AppMenu;

public class GameManager {

    public AppMenu appMenu;

    final String FILENAME = "res/CasinoInfo.txt";

    public GameManager() throws IOException {

        // Load the database or create it.
        Record database = new Record(FILENAME);
    }


    /**
     * @author Koddyrae
     * Method to return the player(s) in the arraylist by name
     * @param name user inputted search name
     * @return name that contains the user input
     */
    public String findPlayersByName(String name) {
        String result = "";

        for (int i = 0; i < listOfPlayers.size(); i++) {
            if (listOfPlayers.get(i).getName().toUpperCase().contains(name.toUpperCase())) {
                result += listOfPlayers.get(i).toString() + " ";
            }
        }
        return result;
    }

    /**
     * @author Koddyrae
     * Method to return the top player(s) in the arraylist by wins
     * @return top player(s) based on order from arraylist
     */
    public String findTopPlayers() {
        String result = "";

        int max = listOfPlayers.get(0).getWins();

        for(int i = 0; i < listOfPlayers.size(); i++) {
            if (max < listOfPlayers.get(i).getWins()) {
                max = listOfPlayers.get(i).getWins();
            }
        }

        for (int j = 0; j < listOfPlayers.size(); j++) {
            if (max == listOfPlayers.get(j).getWins()) {
                result += listOfPlayers.get(j).getName() + " " + listOfPlayers.get(j).getWins() + " ";
            }
        }

        return result;

    }


}

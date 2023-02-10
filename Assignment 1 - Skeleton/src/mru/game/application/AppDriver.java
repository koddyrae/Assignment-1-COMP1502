package mru.game.application;

import java.io.IOException;

import mru.game.controller.GameManager;

/**
 * @author: Bryce 'cybeR' Carson
 */
public class AppDriver {

    public static void main(String[] args) throws IOException {

        /* NOTE: there is no need to assign this to a global variable because the
           object will not be reused. It is an anonymous object which is the main
           program loop. */
         new GameManager();
    }

}

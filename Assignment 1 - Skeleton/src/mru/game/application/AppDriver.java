package mru.game.application;

import mru.game.controller.GameManager;

/**
 * @author: Bryce 'cybeR' Carson
 */
public class AppDriver {

    public static void main(String[] args) {

        /* NOTE: there is no need to assign this to a global variable because the
           object will not be reused. It is an anonymous object which is the main
           program loop. */
        GameManager gm = new GameManager();
    }

}

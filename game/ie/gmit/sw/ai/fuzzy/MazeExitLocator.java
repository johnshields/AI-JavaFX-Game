package ie.gmit.sw.ai.fuzzy;

import ie.gmit.sw.ai.GameWindow;
import javafx.application.Platform;

/**
 * Class MazeExitLocator - Called in keyPressed in GameWindow.
 * Works with TempRadius to help the Player find the mazeExit.
 *
 * @author John Shields - G00348436
 */

public class MazeExitLocator {
    public void mazeExitLocator() {
        TempRadius tr = new TempRadius();
        // Set inputs for the Fuzzy Logic Controller.
        int fuzzyValue = tr.getTempRadius(GameWindow.playerLocation, GameWindow.mazeExit);

        // if statement to use getTempRadius return value to determinate the Player's tempRadius.
        if (fuzzyValue >= 180) {
            System.out.println("Player is frostbite");
        } else if (fuzzyValue >= 150) {
            System.out.println("Player is freezing");
        } else if (fuzzyValue >= 120) {
            System.out.println("Player is cold");
        } else if (fuzzyValue >= 80) {
            System.out.println("Player is chilly");
        } else if (fuzzyValue >= 60) {
            System.out.println("Player is warmish");
        } else if (fuzzyValue >= 30) {
            System.out.println("Player is warm");
        } else if (fuzzyValue >= 20) {
            System.out.println("Player is hot");
        } else if (fuzzyValue >= 10) {
            System.out.println("Player is red hot");
        }
        // if Player is at the exit of the maze (randomly generated) game is won.
        if (GameWindow.playerLocation == GameWindow.mazeExit) {
            System.out.println("You escaped the Maze!\nGame Won!");
            Platform.exit(); // Exit GUI.
        }
    }
}

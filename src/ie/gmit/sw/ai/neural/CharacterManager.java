package ie.gmit.sw.ai.neural;

import ie.gmit.sw.ai.CharacterTask;
import ie.gmit.sw.ai.Command;
import ie.gmit.sw.ai.GameWindow;
import javafx.application.Platform;

/**
 * Class CharacterManager - Called in GameModel.
 * Works with nnTask from NNCharacterTask and CharacterTask to control the Characters.
 *
 * @author John Shields - G00348436
 */

public class CharacterManager implements Command {
    private int energy;
    private int target;
    private int redBull;
    private int gun;
    private static int playerLives = 5;
    public static String action = "";

    @Override
    public void execute() {
        // Get an output value from NN to control the characters based on the changing stats.
        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, redBull, gun, target);

        switch (output) {
            case 0 -> panic();
            case 1 -> attack();
            case 2 -> hide();
            case 3 -> run();
        }

        // Kill off Player and exit GUI.
        if (playerLives == 0) {
            System.out.println("Game Lost!\nYou Died!");
            //Platform.exit();
        }
    }

    // Expected to transition to Attack.
    public void panic() {
        action = "panic";
        energy = 0;
        redBull = 0;
        gun = 1;
        target = 2;
    }

    // Expected to transition to Run.
    public void hide() {
        action = "hide";
        energy = 0;
        redBull = 1;
        gun = 0;
        target = 1;
    }

    // Expected to transition to Panic.
    public void run() {
        action = "run";
        energy = 2;
        redBull = 0;
        gun = 1;
        target = 1;
    }

    // Expected to transition to Hide.
    public void attack() {
        action = "attack";
        energy = 2;
        redBull = 0;
        gun = 0;
        target = 1;
        // if Player is in range take off a life.
        if (CharacterTask.ghostLocation == GameWindow.playerLocation) {
            playerLives = playerLives - 1;
            System.out.println("Player Lives: " + playerLives);
        }
    }
}

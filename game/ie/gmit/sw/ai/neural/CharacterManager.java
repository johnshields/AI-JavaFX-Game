package ie.gmit.sw.ai.neural;

import ie.gmit.sw.ai.CharacterTask;
import ie.gmit.sw.ai.Command;
import ie.gmit.sw.ai.GameWindow;
import javafx.application.Platform;

/**
 * Class CharacterManager - Called in GameModel.
 * Works with nnTask from NNCharacterTask and CharacterTask to Control the Characters.
 *
 * @author John Shields - G00348436
 */

public class CharacterManager implements Command {
    private int energy;
    private int target;
    private int redBull;
    private int gun;
    private static int playerLives = 10;
    public static String action = "";

    @Override
    public void execute() {
        // Get a value to control the character based on the changing stats.
        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, redBull, gun, target);

        switch (output) {
            case 0 -> panic();
            case 1 -> attack();
            case 2 -> hide();
            case 3 -> run();
        }
    }

    // 2, 0, 1, 1 = Panic
    // 0, 1, 0, 2 = Attack
    // 2, 0, 0, 0 = Hide
    // 0, 1, 0, 1 = Run

    // expected to go to Attack
    public void panic() {
        //System.out.println("Panic");
        action = "panic";
        energy = 0;
        redBull = 1;
        gun = 0;
        target = 2;
    }

    // expected to go to Run
    public void hide() {
        //System.out.println("Hide");
        action = "hide";
        energy = 0;
        redBull = 1;
        gun = 0;
        target = 1;
    }

    // expected to go to Panic
    public void run() {
        //System.out.println("Run");
        action = "run";
        energy = 2;
        redBull = 0;
        gun = 1;
        target = 1;
    }

    // expected to go to Hide
    public void attack() {
        System.out.println("Attack");
        // if Player is in range take off a life.
        if (CharacterTask.ghostLocation == GameWindow.playerLocation) {
            energy = 2;
            redBull = 0;
            gun = 0;
            target = 0;
            playerLives = playerLives - 1;
            System.out.println("Player Lives: " + playerLives);
        }

        // Kill off Player and exit GUI.
        if (playerLives == 0) {
            System.out.println("Game Lost!\nYou Died!");
            Platform.exit();
        }
    }
}

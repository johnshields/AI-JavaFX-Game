package ie.gmit.sw.ai;

import javafx.application.Platform;

public class CharacterManager implements Command {
    private static int playerLives = 10;
    private int energy;
    private int target;
    private int sword;
    private int gun;
    public static String action = "";

    public void panic() {
        action = "panic";
        energy = 0;
        target = 0;
        sword = 0;
        gun = 0;
    }

    public void hide() {
        action = "hide";
        energy = 2;
        target = 0;
        sword = 1;
        gun = 1;
    }

    public void run() {
        action = "run";
        energy = 1;
        target = 0;
        sword = 0;
        gun = 0;
    }

    public void attack() {
        System.out.println("Attack");
        target = 0;
        energy = 1;
        sword = 1;
        gun = 1;
        // if Player is in range take off a life.
        if (CharacterTask.ghostLocation == GameWindow.playerLocation) {
            System.out.println("Hit Player");
            playerLives = playerLives - 1;
            System.out.println("Player Lives: " + playerLives);
        }

        // Kill off Player and exit GUI.
        if (playerLives == 0) {
            System.out.println("Game Lost!\nYou Died!");
            Platform.exit();
        }
    }

    @Override
    public void execute() {
        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, sword, gun, target);

        switch (output) {
            case 0 -> panic();
            case 1 -> attack();
            case 2 -> hide();
            case 3 -> run();
        }
    }
}

package ie.gmit.sw.ai;

import javafx.application.Platform;

public class CharacterManager implements Command {
    private int playerLives = 10;
    private int energy = 1;
    private int target = 1;
    private int sword = 1;
    private int gun = 1;

    @Override
    public void execute() {
        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, sword, gun, target);

        System.out.println("Character Task: " + output);
        System.out.println("Energy Level: " + energy);
        System.out.println("Target: " + target);
        System.out.println("Sword: " + sword);
        System.out.println("Gun: " + gun);

        switch (output) {
            case 0 -> panic();
            case 1 -> attack();
            case 2 -> hide();
            case 3 -> run();
        }
    }

    private void panic() {
        System.out.println("Panic");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }

    private void attack() {
        System.out.println("Attack");
        target = (int) (2 * Math.random());
        hitPlayer();
    }

    private void hide() {
        System.out.println("Hide");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }

    private void run() {
        System.out.println("Run");
        target = (int) (2 * Math.random());
        energy = (int) (2 * Math.random());
        sword = (int) (2 * Math.random());
        gun = (int) (2 * Math.random());
    }

    private void hitPlayer() {
        // if Player is in range take off a life.
        if (CharacterTask.ghostLocation == GameWindow.playerLocation) {
            System.out.println("Attack");
            int hit = 1;
            playerLives = playerLives - hit;
            System.out.println("Player Lives: " + playerLives);
        }

        // Kill off Player and exit GUI.
        if (playerLives == 0) {
            System.out.println("Game Lost!\nYou Died!");
            Platform.exit();
        }
    }
}

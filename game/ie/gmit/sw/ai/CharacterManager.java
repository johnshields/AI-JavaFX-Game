package ie.gmit.sw.ai;

import javafx.application.Platform;

public class CharacterManager implements Command{
    private int playerLives = 10;
    private int energy = 2;
    private int target = 1;
    private int sword = 1;
    private int gun = 1;

    @Override
    public void execute() {
        int redBull = 1;
        int stats = 1;
        int hit = 1;

        NNCharacterTask nc = new NNCharacterTask();
        int output = nc.nnTask(energy, sword, gun, target);
        System.out.println("Character Task: " + output);

        // Panic
        if (output == 1) {
            System.out.println("Panic");
            target = target + stats;
            energy = energy - stats;
            System.out.println("Energy Level: " + energy);
        }
        // Attack
        if (output == 2) {
            System.out.println("Attack");
            target = target + stats;
            System.out.println("Target: " + target);

            // if Player is in range take off a life.
            if (CharacterTask.ghostLocation == GameWindow.playerLocation) {
                System.out.println("Attack");
                playerLives = playerLives - hit;
                System.out.println("Player Lives: " + playerLives);
            }
        }
        // Hide
        if (output == 3) {
            System.out.println("Hide");
            target = target - stats;
            energy = energy + redBull;
            sword = sword + stats;
            gun = gun + stats;
            System.out.println("Energy Level: " + energy);
        }
        // Run
        if (output == 4) {
            System.out.println("Run");
            sword = sword - stats;
            gun = gun - stats;
            target = target - stats;
            energy = energy - stats;
            System.out.println("Energy Level: " + energy);
        }

        // Kill off Player and exit GUI.
        if (playerLives == 0) {
            System.out.println("Game Lost!\nYou Died!");
            Platform.exit();
        }
    }
}

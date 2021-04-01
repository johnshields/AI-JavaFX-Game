package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import ie.gmit.sw.ai.neural.CharacterManager;
import javafx.concurrent.Task;

/*
 * CharacterTask represents a Runnable (Task is a JavaFX implementation
 * of Runnable) game character. The character wanders around the game
 * model randomly and can interact with other game characters using
 * implementations of the Command interface that it is composed with.
 */

public class CharacterTask extends Task<Void> {
    private static final int SLEEP_TIME = 300; //Sleep for 300 ms
    private static ThreadLocalRandom rand = ThreadLocalRandom.current();
    private boolean alive = true;
    private GameModel model;
    private char enemyID;
    private int row;
    private int col;
    private Command cmd;
    public static int ghostLocation;

    public CharacterTask(GameModel model, char enemyID, int row, int col, Command cmd) {
        this.model = model;
        this.enemyID = enemyID;
        this.row = row;
        this.col = col;
        this.cmd = cmd;
    }

    @Override
    public Void call() throws Exception {
        /*
         * This Task will remain alive until the call() method returns. This
         * cannot happen as long as the loop control variable "alive" is set to true.
         */
        while (alive) {
            Thread.sleep(SLEEP_TIME);
            synchronized (model) {
                //Randomly pick a direction up, down, left or right
                int temp_row = row, temp_col = col;
                if (rand.nextBoolean()) {
                    temp_row += rand.nextBoolean() ? 1 : -1;
                } else {
                    temp_col += rand.nextBoolean() ? 1 : -1;
                }

                if (model.isValidMove(row, col, temp_row, temp_col, enemyID)) {
                    // This fires if the character can move to a cell, i.e. if it is not already occupied.
                    model.set(temp_row, temp_col, enemyID);
                    model.set(row, col, '\u0020');

                    // Bring in action states from NN.
                    switch (CharacterManager.action) {
                        case "hide" -> {
                            // Hide the enemy.
                            model.set(temp_row, temp_col, (char) 0);
                            row = temp_row;
                            col = temp_col;
                        }
                        case "panic" -> {
                            // Turn enemies pink when in panic state.
                            model.set(temp_row, temp_col,'\u0033');
                            row = temp_row;
                            col = temp_col;
                        }
                        case "run" -> {
                            row = temp_row;
                            col = temp_col;
                        }
                    }

                    ghostLocation = row + col;
                    //System.out.println("GL: " + ghostLocation + " ID: " + enemyID);
                } else {
                    // This fires if a move is not valid, i.e. if someone or some thing is in the way.
                    cmd.execute();
                }
            }
        }
        return null;
    }
}
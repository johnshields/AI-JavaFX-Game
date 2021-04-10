package ie.gmit.sw.ai;

import java.util.concurrent.ThreadLocalRandom;

import ie.gmit.sw.ai.dfs.RecursiveDFS;
import ie.gmit.sw.ai.fuzzy.CharacterLogic;
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
    private boolean smart;
    private boolean alreadySaid = false;

    public CharacterTask(GameModel model, char enemyID, int row, int col, Command cmd) {
        this.model = model;
        this.enemyID = enemyID;
        this.row = row;
        this.col = col;
        this.cmd = cmd;
    }

    // Bring in NN action states from CharacterManager
    // to make player aware of their current stats.
    private void switchActions() {
        switch (CharacterManager.action) {
            // Turn enemies Pink when in panic state.
            case 0 -> enemyID = '\u0033';
            // Turn enemies Red & Green when in hostile mode.
            case 1 -> enemyID = '\u0035';
            // Make enemies disappear for a brief time.
            case 2 -> enemyID = (char) 0;
            // Turn enemies Orange when in run state.
            case 3 -> enemyID = '\u0036';
        }
    }

    // Bring in Fuzzy Logic to analyse character's NN actions.
    private void intelligenceIs() {
        // Set input for the Fuzzy Logic Controller.
        CharacterLogic cl = new CharacterLogic();
        int intelligence = cl.getIntelligence(CharacterManager.action);

        if (intelligence == 10) {
            smart = false;
        } else if (intelligence == 20) {
            smart = false;
        } else if (intelligence == 30) {
            smart = true;
        } else if (intelligence == 40) {
            smart = true;
        }
    }

    // Smart enemy (Ghost) gives the player a helping hand if they cross paths
    // with the help of RecursiveDFS.
    private void smartGhost() {
        if (GameWindow.playerLocation == ghostLocation && smart && !alreadySaid) {
            if (RecursiveDFS.goalNode >= 20) {
                System.out.println("Maze exit is center right");
            } else if (RecursiveDFS.goalNode >= 10) {
                System.out.println("Maze exit is top right");
            } else if (RecursiveDFS.goalNode >= 1) {
                System.out.println("Maze exit is very top right");
            }
            alreadySaid = true;
        }
    }

    @Override
    public Void call() throws Exception {
        /*
         * This Task will remain alive until the call() method returns.
         * This cannot happen as long as the loop control variable "alive" is set to true.
         */
        while (alive) {
            Thread.sleep(SLEEP_TIME);
            synchronized (model) {
                // Randomly pick a direction up, down, left or right.
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
                    row = temp_row;
                    col = temp_col;
                    ghostLocation = temp_row + temp_col;
                } else {
                    // This fires if a move is not valid, i.e. if someone or some thing is in the way.
                    switchActions();
                    intelligenceIs();
                    smartGhost();
                    cmd.execute();
                }
            }
        }
        return null;
    }
}
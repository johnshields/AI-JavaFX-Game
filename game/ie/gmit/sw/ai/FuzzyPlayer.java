package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyPlayer implements Command {

    public static int currentRow;
    public static int currentCol;
    char fuzzyPlayer = GameWindow.PLAYER_ID;
    Variable playerLocation;

    public double getPlayerLocation(double currRow, double currCol) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/fuzzy_player.fcl", true);
        // 'FUNCTION_BLOCK fuzzy_player' from fuzzy_player.fcl
        FunctionBlock fb = fis.getFunctionBlock("fuzzy_player");
        // Apply a value to a variable
        fis.setVariable("currentRow", currRow);
        fis.setVariable("currentCol", currCol);
        // Execute the fuzzy inference engine
        fis.evaluate();
        playerLocation = fb.getVariable("playerLocation");
        return playerLocation.getValue();
    }

    @Override
    public void execute() {
        // Fuzzy Logic implementation
        FuzzyPlayer fp = new FuzzyPlayer();
        System.out.println("[INFO] Fuzzy Logic is analysing Player's Location");
        fuzzyPlayer = (char) fp.getPlayerLocation(currentRow, currentCol);
    }
}

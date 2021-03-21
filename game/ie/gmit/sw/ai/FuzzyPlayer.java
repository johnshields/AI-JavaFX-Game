package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyPlayer {
    // FCL file is loaded in Runner
    private final FIS fis = Runner.fis;
    int tempRadius = GameWindow.playerLocation;

    public int getTempRadius(int maze_exit) {
        // 'FUNCTION_BLOCK fuzzy_chars' from fuzzy_chars.fcl
        FunctionBlock fb = fis.getFunctionBlock("fuzzy_chars");
        // Apply a value to variable
        fis.setVariable("maze_exit", maze_exit);
        // Execute the fuzzy inference engine
        fis.evaluate();
        Variable radius = fb.getVariable("player");
        tempRadius = (int) radius.getValue();
        return tempRadius;
    }
}

package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class TempRadius {
    // FCL file is loaded in Runner.
    private final FIS fis = Runner.fis;
    int tempRadius = GameWindow.playerLocation;

    public int getTempRadius(int playerLocation) {
        // 'FUNCTION_BLOCK tempRadius' from tempRadius.fcl
        FunctionBlock fb = fis.getFunctionBlock("tempRadius");
        // Apply a value to variable
        fis.setVariable("playerLocation", playerLocation);
        // Execute the fuzzy inference engine
        fis.evaluate();
        Variable radius = fb.getVariable("player");
        tempRadius = (int) radius.getValue();
        return tempRadius;
    }
}

package ie.gmit.sw.ai.fuzzy;

import ie.gmit.sw.ai.Runner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * Class TempRadius - Fuzzy Logic Controller
 * Sets up Variables from tempRadius.fcl (loaded in Runner) and
 * returns an int value for the Player's Temperature Radius for mazeExit in GameWindow.
 *
 * @author John Shields - G00348436
 */

public class TempRadius {
    private final FIS fis = Runner.fis;

    /**
     * getTempRadius
     *
     * @return tempRadius
     */
    public int getTempRadius(int playerLocation, int mazeExit) {
        // 'FUNCTION_BLOCK tempRadius' from tempRadius.fcl
        FunctionBlock fb = fis.getFunctionBlock("tempRadius");
        // Apply a value to variable
        fis.setVariable("playerLocation", playerLocation);
        fis.setVariable("mazeExit", mazeExit);
        // Execute the fuzzy inference engine
        fis.evaluate();
        Variable tempRadius = fb.getVariable("player");
        return (int) tempRadius.getValue();
    }
}

package ie.gmit.sw.ai.fuzzy;

import ie.gmit.sw.ai.Runner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * Class CharacterLogic - Fuzzy Logic Controller
 * Sets up Variables from intelligence.fcl (loaded in Runner) and
 * returns an int value for the actions from the NN classification of output Data.
 *
 * @author John Shields - G00348436
 */

public class CharacterLogic {
    private final FIS fis = Runner.intelligenceFCL;

    /**
     * getIntelligence
     *
     * @return intelligence
     */
    public int getIntelligence(int action) {
        // 'FUNCTION_BLOCK character' from intelligence.fcl
        FunctionBlock fb = fis.getFunctionBlock("character");
        // Apply value to variable.
        fis.setVariable("action", action);
        // Execute the fuzzy inference engine.
        fis.evaluate();
        Variable intelligence = fb.getVariable("intelligence");
        return (int) intelligence.getValue();
    }
}

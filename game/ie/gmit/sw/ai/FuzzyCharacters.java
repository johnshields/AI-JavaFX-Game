package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyCharacters implements Command{

    public static int gRow;
    public static int gCol;
    public static char fuzzyEnemy;

    @Override
    public int execute() {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/fuzzy_chars.fcl", true);
        if(fis == null ) {
            System.err.println("Can't load file: " + "fuzzy_chars.fcl");
        }
        assert fis != null;
        // 'FUNCTION_BLOCK fuzzy_chars' from fuzzy_chars.fcl
        FunctionBlock fb = fis.getFunctionBlock("fuzzy_chars");
        // Apply a value to a variable
        fis.setVariable("currentRow", gRow);
        fis.setVariable("currentCol", gCol);
        // Execute the fuzzy inference engine
        fis.evaluate();
        Variable location = fb.getVariable("location");
        // Use defuzzification method
        fuzzyEnemy = (char) location.getValue();
        return (int) location.getValue();
    }
}

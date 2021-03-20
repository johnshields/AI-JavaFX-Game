package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyCharacters implements Command{

    public static int gRow;
    public static int gCol;
    public static char fuzzyChars;
    Variable location;

    public double getLocation(double currRow, double currCol) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/fuzzy_chars.fcl", true);
        // 'FUNCTION_BLOCK fuzzy_chars' from fuzzy_chars.fcl
        FunctionBlock fb = fis.getFunctionBlock("fuzzy_chars");

        // Apply a value to a variable
        fis.setVariable("currentRow", currRow);
        fis.setVariable("currentCol", currCol);
        // Execute the fuzzy inference engine
        fis.evaluate();
        location = fb.getVariable("location");
        // Use defuzzification method
        return location.getValue();
    }

    @Override
    public void execute() {
        // Fuzzy Logic implementation
        FuzzyCharacters fg = new FuzzyCharacters();
        fuzzyChars = (char) fg.getLocation(gRow, gCol);
    }
}

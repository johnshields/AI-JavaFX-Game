package ie.gmit.sw.ai;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class FuzzyPlayer implements Command {

    // variables from CharacterTask
    char gameGhost = CharacterTask.enemyID;
    int topOfMaze = CharacterTask.mazeTop;

    public double getFuzzyPlayer(double mazeTop, double ghost) {
        // Load and parse the FCL
        FIS fis = FIS.load("fcl/fuzzy_player.fcl", true);
        // 'FUNCTION_BLOCK fuzzy_player' from fuzzy_player.fcl
        FunctionBlock fb = fis.getFunctionBlock("fuzzy_player");
        // Apply a value to a variable
        fis.setVariable("maze_top", mazeTop);
        fis.setVariable("ghost", ghost);
        // Execute the fuzzy inference engine
        fis.evaluate();
        Variable player = fb.getVariable("player");
        return player.getValue();
    }

    @Override
    public void execute() {
        // Fuzzy Logic implementation
        FuzzyPlayer fp = new FuzzyPlayer();
        double fuzzyPlayer = fp.getFuzzyPlayer(topOfMaze, gameGhost);
        System.out.println(fuzzyPlayer);
    }
}

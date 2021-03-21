package ie.gmit.sw.ai;

import javafx.application.Application;
import net.sourceforge.jFuzzyLogic.FIS;

public class Runner {
	public static FIS fis;

	public static void main(String[] args) {
		// Load and Parse FCL file
		fis = FIS.load("resources/fuzzy/tempRadius.fcl", true);
		if(fis == null ) {
			System.err.println("Not able to load file: tempRadius.fcl");
			return;
		}

		// train, configure and load a neural network HERE

		Application.launch(GameWindow.class, args);
	}
}
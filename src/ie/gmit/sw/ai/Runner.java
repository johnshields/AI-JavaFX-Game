package ie.gmit.sw.ai;

import ie.gmit.sw.ai.neural.NNCharacterTask;
import javafx.application.Application;
import net.sourceforge.jFuzzyLogic.FIS;

public class Runner {
	public static FIS fclPlayer;
	public static FIS fclCharacter;

	public static void main(String[] args) {
		// Load and Parse FCL files.
		fclPlayer = FIS.load("./resources/fuzzy/tempRadius.fcl", true);
		if(fclPlayer == null) {
			System.err.println("Not able to load file: tempRadius.fcl");
			return;
		}
		System.out.println("[INFO] tempRadius.fcl loaded.");
		fclCharacter = FIS.load("./resources/fuzzy/intelligence.fcl", true);
		if(fclCharacter == null) {
			System.err.println("Not able to load file: intelligence.fcl");
			return;
		}
		System.out.println("[INFO] intelligence.fcl loaded.");

		// Setup the NN.
		NNCharacterTask nc = new NNCharacterTask();
		nc.neuralNetwork();

		// Necessary try catch to get .jar file to run from CLI.
		try {
			System.out.println("[INFO] Launching GUI...");
			Application.launch(GameWindow.class, args);
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
		}
	}
}
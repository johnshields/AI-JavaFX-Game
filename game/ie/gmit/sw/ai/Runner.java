package ie.gmit.sw.ai;

import ie.gmit.sw.ai.neural.NNCharacterTask;
import javafx.application.Application;
import net.sourceforge.jFuzzyLogic.FIS;

public class Runner {
	public static FIS fis;

	public static void main(String[] args) {
		// Load and Parse FCL file.
		fis = FIS.load("resources/fuzzy/tempRadius.fcl", true);
		if(fis == null) {
			System.err.println("Not able to load file: tempRadius.fcl");
			return;
		}
		System.out.println("[INFO] tempRadius.fcl loaded.");

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
package ie.gmit.sw.ai;

import ie.gmit.sw.ai.neural.NNCharacterTask;
import javafx.application.Application;
import net.sourceforge.jFuzzyLogic.FIS;

public class Runner {
	public static FIS tempRadiusFCL;
	public static FIS intelligenceFCL;

	public static void main(String[] args) {
		// Load and Parse FCL files.
		tempRadiusFCL = FIS.load("./resources/fuzzy/tempRadius.fcl", true);
		if(tempRadiusFCL == null) {
			System.err.println("Not able to load file: tempRadius.fcl");
			return;
		}
		System.out.println("[INFO] tempRadius.fcl loaded.");
		intelligenceFCL = FIS.load("./resources/fuzzy/intelligence.fcl", true);
		if(intelligenceFCL == null) {
			System.err.println("Not able to load file: intelligence.fcl");
			return;
		}
		System.out.println("[INFO] intelligence.fcl loaded.");

		/*
		 * Setup the NN.
		 * Declare Network Topology
		 * Create the training data set
		 * Train the NN
		 * Test the NN
		 * All done in NNCharacterTask.
		 */
		NNCharacterTask nn = new NNCharacterTask();
		nn.neuralNetwork();

		// Necessary try catch to get .jar file to run from CLI.
		try {
			System.out.println("[INFO] Launching GUI...");
			Application.launch(GameWindow.class, args);
		} catch (NoClassDefFoundError e) {
			e.printStackTrace();
		}
	}
}
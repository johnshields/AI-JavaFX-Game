package ie.gmit.sw.ai;

import ie.gmit.sw.ai.neural.NNCharacterTask;
import javafx.application.Application;
import net.sourceforge.jFuzzyLogic.FIS;

public class Runner {
	private static FIS fclPlayer;
	private static FIS fclCharacter;

	public static FIS getFclPlayer() {
		return fclPlayer;
	}

	public static void setFclPlayer(FIS fclPlayer) {
		Runner.fclPlayer = fclPlayer;
	}

	public static FIS getFclCharacter() {
		return fclCharacter;
	}

	public static void setFclCharacter(FIS fclCharacter) {
		Runner.fclCharacter = fclCharacter;
	}

	public static void main(String[] args) {
		// Load and Parse FCL files.
		setFclPlayer(FIS.load("./resources/fuzzy/tempRadius.fcl", true));
		if(getFclPlayer() == null) {
			System.err.println("Not able to load file: tempRadius.fcl");
			return;
		}
		System.out.println("[INFO] tempRadius.fcl loaded.");
		setFclCharacter(FIS.load("./resources/fuzzy/intelligence.fcl", true));
		if(getFclCharacter() == null) {
			System.err.println("Not able to load file: intelligence.fcl");
			return;
		}
		System.out.println("[INFO] intelligence.fcl loaded.");

		// Setup the NN.
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
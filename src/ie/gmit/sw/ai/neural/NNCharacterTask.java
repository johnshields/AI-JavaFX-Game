package ie.gmit.sw.ai.neural;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.simple.EncogUtility;

/**
 * Class NNCharacterTask
 * Neural Network for the Characters.
 * Handles the construction and testing of the NN.
 *
 * @author John Shields - G00348436
 */

public class NNCharacterTask {
    private static BasicNetwork basicNetwork;

    // Input Dataset - Energy, RedBull, Gun, Targets.
    private final double[][] data = {
            {2, 0, 0, 0}, // Energized = Hide
            {2, 0, 0, 1}, // Energized and has a target = Hide
            {2, 0, 1, 1}, // Energized, has a gun and has a target = Panic
            {2, 0, 1, 2}, // Energized, has a gun and two targets = Panic
            {2, 1, 0, 2}, // Energized, has a redBull and two targets = Run
            {2, 1, 0, 1}, // Energized, has a redBull and a target = Hide
            {1, 0, 0, 0}, // Tired = Hide
            {1, 0, 0, 1}, // Tired and has a target = Run
            {1, 0, 1, 1}, // Tired, has a gun and a target = Panic
            {0, 0, 1, 2}, // Has a gun and two targets = Attack
            {0, 1, 0, 2}, // Has a redBull and two targets = Attack
            {1, 1, 0, 1}, // Tired, has a redBull = Run
            {0, 0, 0, 0}, // Nothing = Hide
            {0, 0, 0, 1}, // A target = Run
            {0, 0, 1, 1}, // Has a gun and target = Run
            {0, 0, 1, 2}, // Has a gun and two targets = Attack
            {0, 1, 0, 2}, // Has a redBull and two targets = Attack
            {0, 1, 0, 1}  // Has redBull and target = Run
    };

    // Expected Output from Dataset - Panic, Attack, Hide, Run.
    private final double[][] expected = {
            {0.0, 0.0, 1.0, 0.0}, // Hide
            {0.0, 0.0, 1.0, 0.0}, // Hide
            {1.0, 0.0, 0.0, 0.0}, // Panic
            {1.0, 0.0, 0.0, 0.0}, // Panic
            {0.0, 0.0, 0.0, 1.0}, // Run
            {1.0, 0.0, 0.0, 0.0}, // Hide
            {0.0, 0.0, 1.0, 0.0}, // Hide
            {0.0, 0.0, 0.0, 1.0}, // Run
            {1.0, 0.0, 0.0, 0.0}, // Panic
            {0.0, 1.0, 0.0, 0.0}, // Attack
            {0.0, 1.0, 0.0, 0.0}, // Attack
            {0.0, 0.0, 0.0, 1.0}, // Run
            {0.0, 0.0, 1.0, 0.0}, // Hide
            {0.0, 0.0, 0.0, 1.0}, // Run
            {0.0, 0.0, 0.0, 1.0}, // Run
            {0.0, 1.0, 0.0, 0.0}, // Attack
            {0.0, 1.0, 0.0, 0.0}, // Attack
            {0.0, 0.0, 0.0, 1.0} // Run
    };

    /**
     * declareNetworkTopology
     * Build the Neural Network.
     *
     * Layer 1: Input Layer, no Activation function, Bias = True, Neurons = 4
     * Layer 2: Hidden Layer, Sigmoidal Activation function, Bias = True, Neurons = 2
     * Layer 3: Output Layer, Sigmoidal Activation function, Bias = False, Neurons = 4
     *
     * @return basicNetwork
     */
    private BasicNetwork declareNetworkTopology() {
        basicNetwork = new BasicNetwork();
        basicNetwork.addLayer(new BasicLayer(null, true, 4));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(), true, 2));
        basicNetwork.addLayer(new BasicLayer(new ActivationSigmoid(), false, 4));
        basicNetwork.getStructure().finalizeStructure();
        basicNetwork.reset();
        System.out.println("[INFO] Network Created.");
        return basicNetwork;
    }

    /**
     * neuralNetwork - Called in Runner.
     * Construct, train and test the NN.
     *
     * Step 1: Declare Network Topology.
     * Step 2: Create the training data set.
     * Step 3: Training the NN.
     * Step 4: Testing the NN.
     */
    public void neuralNetwork() {
        // Declare Network Topology.
        System.out.println("[INFO] Creating neural network...");
        basicNetwork = declareNetworkTopology();

        // Create the training data set.
        System.out.println("[INFO] Creating training set...");
        MLDataSet trainingSet = new BasicMLDataSet(data, expected);

        // Training the NN.
        System.out.println("[INFO] Training the network...");
        // Set up Resilient Back Propagation for training the NN with the Data Set.
        ResilientPropagation train = new ResilientPropagation(basicNetwork, trainingSet);
        // Train with an error rate of 0.09
        EncogUtility.trainToError(train, 0.09);
        train.finishTraining();
        System.out.println("[INFO] Training complete.");

        // Testing the NN.
        // Compares the MLData pair with the actual data from the Dataset and calculates the accuracy.
        System.out.println("[INFO] Testing the network...");
        double correct = 0;
        double total = 0;
        for (MLDataPair pair : trainingSet) {
            total++;
            MLData output = basicNetwork.compute(pair.getInput());
            int y = (int) Math.round(output.getData(0));
            int yd = (int) pair.getIdeal().getData(0);
            if (y == yd) {
                correct++;
            }
        }
        System.out.println("[INFO] Testing Complete: Accuracy = " + ((correct / total) * 100));
        Encog.getInstance().shutdown(); // Shut down NN.
    }

    /**
     * nnTask - Used in CharacterManager.
     * Returns NN output for a Character Task.
     *
     * @return NN classification of Data.
     */
    public int nnTask(int energy, int redBull, int gun, int targets) {
        double[] input = {energy, redBull, gun, targets};
        MLData data = new BasicMLData(input);
        return basicNetwork.classify(data);
    }
}

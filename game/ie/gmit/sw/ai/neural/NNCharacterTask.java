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

/**
 * Class NNCharacterTask
 * Neural Network for the Characters.
 *
 * @author John Shields - G00348436
 *
 * Data set for the Neural Network Controlled Characters.
 *
 * Inputs:
 * 1) Energy (2 = Energized, 1 = Tired, 0 = Out of breath)
 * 2) Has a RedBull (1 = yes, 0 = no)
 * 3) Has a Gun (1 = yes, 0 = no)
 * 4) Number of Targets
 *
 * Outputs:
 * 0) Panic, 1) Attack, 2) Hide, 3) Run
 */

public class NNCharacterTask {

    private static BasicNetwork network;
    private final double[][] data = {
            // Energy, RedBull, Gun, Targets
            {2, 0, 0, 0}, // Hide
            {2, 0, 0, 1}, // Hide
            {2, 0, 1, 1}, // Panic
            {2, 0, 1, 2}, // Panic
            {2, 1, 0, 2}, // Run
            {2, 1, 0, 1}, // Hide
            {1, 0, 0, 0}, // Hide
            {1, 0, 0, 1}, // Run
            {1, 0, 1, 1}, // Panic
            {0, 0, 1, 2}, // Attack
            {0, 1, 0, 2}, // Attack
            {1, 1, 0, 1}, // Run
            {0, 0, 0, 0}, // Hide
            {0, 0, 0, 1}, // Run
            {0, 0, 1, 1}, // Run
            {0, 0, 1, 2}, // Attack
            {0, 1, 0, 2}, // Attack
            {0, 1, 0, 1} // Run
    };

    private final double[][] expected = {
            // Panic, Attack, Hide, Run
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

    // Create the NN.
    BasicNetwork createNetwork() {
        BasicNetwork network = new BasicNetwork();
        // add layers
        network.addLayer(new BasicLayer(null, true, 4));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 2));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 4));
        network.getStructure().finalizeStructure();
        network.reset();
        System.out.println("[INFO] Network Created.");
        return network;
    }

    public void neuralNetwork() {
        // Step 1: Declare Network Topology.
        System.out.println("[INFO] Creating neural network...");
        network = createNetwork();

        // Step 2: Create the training data set.
        System.out.println("[INFO] Creating training set...");
        MLDataSet trainingSet = new BasicMLDataSet(data, expected);

        // Step 3: Training the NN.
        System.out.println("[INFO] Training the network...");
        ResilientPropagation train = new ResilientPropagation(network, trainingSet);
        double minError = 0.09;
        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while (train.getError() > minError);
        train.finishTraining();
        System.out.println("[INFO] Training complete in " + epoch + " epochs with error=" + train.getError());

        // Step 4: Test the NN.
        System.out.println("[INFO] Testing the network...");
        double correct = 0;
        double total = 0;
        for (MLDataPair pair : trainingSet) {
            total++;
            MLData output = network.compute(pair.getInput());
            int y = (int) Math.round(output.getData(0));
            int yd = (int) pair.getIdeal().getData(0);
            if (y == yd) {
                correct++;
            }
        }
        // Shut down NN.
        System.out.println("[INFO] Testing Complete: Accuracy = " + ((correct / total) * 100));
        Encog.getInstance().shutdown();
    }

    /**
     * nnTask
     * Returns NN output for a Character Task.
     *
     * @return NN classification of Data.
     */
    public int nnTask(int energy, int redBull, int gun, int targets) {
        double[] input = {energy, redBull, gun, targets};
        MLData data = new BasicMLData(input);
        return network.classify(data);
    }
}

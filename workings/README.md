<p align="center">
<img src="https://user-images.githubusercontent.com/26766163/109871610-466bab80-7c63-11eb-8e96-b410c246c6c8.png" alt="college">
</p>

<h3 align="center">John Shields - G00348436</h3>

## Design & Implementation Rationale
Beginning the AI implementation, first, the class TempRadius, and tempRadius.fcl was created to use Fuzzy Logic to guide the player to the Maze Exit. The input variables are playerLocation and mazeExit. These two terms work with the inference rules set to output the player's temperature radius with the Maze Exit. The number values were worked out by declaring a variable playerLocation in GameWindow. This variable is used in the keyPressed method and is initialized to equal the sum of currentRow and currentCol, which tracks the player's index coordinates whenever a key is pressed. The variable mazeExit in GameWindow is randomly picked between 1 and 30 when the game starts. With this setup, a brute force approach was used to cover all possible maze exits for the inference rules. In MazeExitLocator, TempRadius is used to get Defuzzify's output to notify the player how close they are to the exit.

An Encog Neural Network (NN) was constructed in the new class NNCharacterTask to control the Ghost Characters. This NN is built with three layers, an input layer, a hidden layer, and an output layer. It takes in a dataset of stats and an expected outcome for the ghosts. Then gets trained with the dataset by using Resilient Back Propagation and an error rate of 0.09. In CharacterManager, the NN's output switches the ghosts between actions depending on the changing stats. These actions are Panic, Hide, Run, and Attack and are accessed in CharacterTask. Panic makes the ghosts turn Pink, Hide turns the ghosts invisible, and Attack puts the ghosts into a hostile mode. At the start of the game, the player has five lives. If the player is in range when the ghosts are in this mode, the player loses a life.

With the AI implemented, the player has two options: escape the maze or suffer defeat by the ghosts.

## References
* [JFuzzyLogic Documentation](http://jfuzzylogic.sourceforge.net/html/manual.html)
* [Encog 3.3: Quick Start Guide](https://s3.amazonaws.com/heatonresearch-books/free/encog-3_3-quickstart.pdf)

<h4 align="center">END OF DESIGN & IMPLEMENTATION RATIONALE</h3>

<p align="center">
<img src="https://user-images.githubusercontent.com/26766163/109871610-466bab80-7c63-11eb-8e96-b410c246c6c8.png" alt="college">
</p>

<h2 align="center">John Shields - G00348436</h2>

## Design & Implementation Rationale
Beginning the AI implementation, first, the class TempRadius, and tempRadius.fcl was made to use Fuzzy Logic to guide the player to the Maze Exit. 
The terms used are input variables are playerLocation and mazeExit. 
These two terms work with the inference rules set to output the player's temperature radius with the Maze Exit. 
The number values were worked out by declaring the new variable playerLocation in GameWindow. 
This variable is used in the keyPressed method and is initialized to equal the sum of currentRow and currentCol, which means that it tracks the player's index coordinates whenever a key is pressed. 
The new variable mazeExit in GameWindow is randomly picked between 1 and 30 when the game starts. 
With this setup, a brute force approach was used to cover all possible maze exits for the inference rules.

## References
[JFuzzyLogic Documentation](http://jfuzzylogic.sourceforge.net/html/manual.html)


<h4 align="center">END OF DESIGN & IMPLEMENTATION RATIONALE</h3>

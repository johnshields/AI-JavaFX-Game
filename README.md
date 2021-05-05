<h1 align="center">Artificial Intelligence</h1>


<a href="https://www.gmit.ie/" >
<p align="center"><img src="https://i.ibb.co/f1ZQSkt/logo-gmit.png"
alt="GMIT Logo" width="500" height="200"/>
</p></a>

#### John Shields - G00348436

***

# Project Details
| **Project Title** | Autonomous Computer-controlled Game Characters |
| :------------- |:-------------|
| **Course**              | BSc (Hons) in Software Development |
| **Module**              | Artificial Intelligence |
| **Institute**           | [Galway-Mayo Institute of Technology](https://www.gmit.ie/) |
| **Student**             | [John Shields](https://github.com/johnshields) |
| **Module Lecturer**     | Dr. John Healy |
| **Grade Achieved**      | 87% |

***

# About the Game
JavaFX Game that uses the AI technologies; Fuzzy Logic and Neural Networks to enhance the experience and to control a set of characters moving randomly through a game model.

View the [***Design & Implementation Rationale***](https://github.com/johnshields/AI-JavaFX-Game/blob/main/workings/README.pdf) for more detail.
# Run the Game
### Requirements
* [Git](https://git-scm.com/downloads)
* [Java SDK 15](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)
* [JavaFX 15](https://gluonhq.com/products/javafx/)
* Encog and JFuzzyLogic are supplied in `workings/jars`

#### Open a directory of your choice in Command-Line and enter:
```bash
$ git clone https://github.com/johnshields/AI-JavaFX-Game.git
```

#### Open the repository directory in Command-Line and enter:
* Note update the JavaFX, Encog and JFuzzyLogic paths to yours
```bash
$ cd workings/game
```
### Windows
```bash
$ java --module-path .;/your_javaFX/lib/;/your_encog/;/your_jfuzzylogic/ --module gmit.software/ie.gmit.sw.ai.Runner
```

### Linux & Mac
```bash
$ java --module-path .:/your_javaFX/lib/:/your_encog/:/your_jfuzzylogic/ --module gmit.software/ie.gmit.sw.ai.Runner
```

***
###### END OF README

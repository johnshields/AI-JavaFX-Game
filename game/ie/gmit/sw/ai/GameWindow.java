package ie.gmit.sw.ai;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Main UI for the game. You should not have to alter anything in this class.
 * 
 */
public class GameWindow extends Application{
	private static final char PLAYER_ID = '1';
	private static final int DEFAULT_SIZE = 60;
	private static final int IMAGE_COUNT = 6;
	private GameView view;
	private GameModel model;
	private int currentRow;
	private int currentCol;

	@Override
    public void start(Stage stage) throws Exception {
		model = new GameModel(DEFAULT_SIZE); //Create a model
    	view = new GameView(model); //Create a view of the model

    	stage.setTitle("GMIT - B.Sc. in Computing (Software Development) - AI Assignment 2021");
		stage.setWidth(600);
		stage.setHeight(630);
		stage.setOnCloseRequest((e) -> model.tearDown()); //Shut down the executor service
    	
		VBox box = new VBox();
		Scene scene = new Scene(box);
		scene.setOnKeyPressed(e -> keyPressed(e)); //Add a key listener
		stage.setScene(scene);
		
    	Sprite[] sprites = getSprites(); //Load the sprites from the res directory
    	view.setSprites(sprites); //Add the sprites to the view
    	placePlayer(); //Add the player
    	box.getChildren().add(view);
		
    	view.draw(); //Paint the view
    	
		//Display the window
		stage.show();
		stage.centerOnScreen();
	}
	
    public void keyPressed(KeyEvent e) { //Handle key events
    	KeyCode key = e.getCode(); 
        if (key == KeyCode.RIGHT && currentCol < DEFAULT_SIZE - 1) {
        	if (model.isValidMove(currentRow, currentCol, currentRow, currentCol + 1, PLAYER_ID)) currentCol++;   		
        }else if (key == KeyCode.LEFT && currentCol > 0) {
        	if (model.isValidMove(currentRow, currentCol, currentRow, currentCol - 1, PLAYER_ID)) currentCol--;	
        }else if (key == KeyCode.UP && currentRow > 0) {
        	if (model.isValidMove(currentRow, currentCol, currentRow - 1, currentCol, PLAYER_ID)) currentRow--;
        }else if (key == KeyCode.DOWN && currentRow < DEFAULT_SIZE - 1) {
        	if (model.isValidMove(currentRow, currentCol, currentRow + 1, currentCol, PLAYER_ID)) currentRow++;        	  	
        }else if (key == KeyCode.Z){
        	view.toggleZoom();
        }else{
        	return;
        }
        
        updateView();       
    }
	
	private void placePlayer(){  //Place the main player character	
    	currentRow = (int) (DEFAULT_SIZE * Math.random());
    	currentCol = (int) (DEFAULT_SIZE * Math.random());
    	model.set(currentRow, currentCol, PLAYER_ID); //Player is at index 1
    	updateView(); 		
	}
	
	private void updateView(){ 
		view.setCurrentRow(currentRow);
		view.setCurrentCol(currentCol);
	}
	
	private Sprite[] getSprites() throws Exception{
		/*
		 * Read in the images from the resources directory as sprites. Each sprite is
		 * referenced by its index in the array, e.g. a 3 implies a Pink Enemy... Ideally, 
		 * the array should dynamically created from the images... 
		 */
		Sprite[] sprites = new Sprite[IMAGE_COUNT];
		sprites[0] = new Sprite("Player", "/resources/images/player-0.png", "/resources/images/player-1.png", "/resources/images/player-2.png", "/resources/images/player-3.png", "/resources/images/player-4.png", "/resources/images/player-5.png", "/resources/images/player-6.png", "/resources/images/player-7.png");
		sprites[1] = new Sprite("Red Enemy", "/resources/images/red-0.png", "/resources/images/red-1.png", "/resources/images/red-2.png", "/resources/images/red-3.png", "/resources/images/red-4.png", "/resources/images/red-5.png", "/resources/images/red-6.png", "/resources/images/red-7.png");
		sprites[2] = new Sprite("Pink Enemy", "/resources/images/pink-0.png", "/resources/images/pink-1.png", "/resources/images/pink-2.png", "/resources/images/pink-3.png", "/resources/images/pink-4.png", "/resources/images/pink-5.png", "/resources/images/pink-6.png", "/resources/images/pink-7.png");
		sprites[3] = new Sprite("Blue Enemy", "/resources/images/blue-0.png", "/resources/images/blue-1.png", "/resources/images/blue-2.png", "/resources/images/blue-3.png", "/resources/images/blue-4.png", "/resources/images/blue-5.png", "/resources/images/blue-6.png", "/resources/images/blue-7.png");
		sprites[4] = new Sprite("Red Green Enemy", "/resources/images/gred-0.png", "/resources/images/gred-1.png", "/resources/images/gred-2.png", "/resources/images/gred-3.png", "/resources/images/gred-4.png", "/resources/images/gred-5.png", "/resources/images/gred-6.png", "/resources/images/gred-7.png");
		sprites[5] = new Sprite("Orange Enemy", "/resources/images/orange-0.png", "/resources/images/orange-1.png", "/resources/images/orange-2.png", "/resources/images/orange-3.png", "/resources/images/orange-4.png", "/resources/images/orange-5.png", "/resources/images/orange-6.png", "/resources/images/orange-7.png");
		return sprites;
	}
}
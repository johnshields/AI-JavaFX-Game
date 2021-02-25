package ie.gmit.sw.ai;

import javafx.scene.image.Image;


/*
 * Sprite container for the game. You should not have to alter anything in this class.
 */
public class Sprite { 
	private String name; //The name of this sprite
	private Image[] frames; //The set of image frames to animate
 	private int index = 0; //Initial starting index in array
 	
	public Sprite(String name, String... images) throws Exception{
		this.name = name;
		this.index = 0; //Initialise the starting index to zero
		this.frames = new Image[images.length]; //Initialise the image frames
		
		for (int i = 0; i < images.length; i++){
			frames[i] = new Image(getClass().getResource(images[i]).toString()); //Read in each image as a BufferedImage
		}
	}
	
	public Image getNext(){ //Returns the next image frame
		int idx = index;
		if (index < frames.length - 1){
			index++;
		}else{
			index = 0; //Circle back to the start of the array
		}
		return frames[idx]; 
	}
	
	public String getName(){
		return this.name;
	}
}
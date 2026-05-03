package model;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Exit {
	
	private int startX; //vertical position
	private int startY; //horizontal position 
	BufferedImage sprite; 
	
	
	public Exit(int startX, int startY) {
		
		this.startX = startX;
		this.startY = startY; 
		
		try {
			sprite = ImageIO.read(Coin.class.getResource("Coin.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public int get_StartX() {
		return startX; 
	}
	
	public int get_StartY() {
		return startY; 
	}

}

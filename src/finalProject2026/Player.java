package finalProject2026;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	
	private int row; //vertical position
	private int col; //horizontal position 
	BufferedImage sprite; 
	
	public Player(int row, int col) {
		this.row = row;
		this.col = col; 
		
		try {
			sprite = ImageIO.read(Player.class.getResource("Steve.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public int get_row(){
		return row; 
	}
	
	public int get_col() {
		return col;
	}
	
	
	public void moveBy(int dRow, int dCol){
		row = row + dRow;
		col = col + dCol; 
	}
	
	

}

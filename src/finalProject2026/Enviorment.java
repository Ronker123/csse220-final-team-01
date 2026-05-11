package finalProject2026;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Enviorment {
	
	private int x_position;
	private int y_position;
	private int tileSize;
	private int id;
	protected boolean canBeWalkedOn;
	
	public Type type;
	private BufferedImage displayImage;
	
	public Enviorment(Type type, int x_position, int y_position, int tileSize, int id) {
		setDisplayImage(type);
		this.type = type;
		this.x_position = x_position;
		this.y_position = y_position;
		this.tileSize = tileSize;
		this.id = id;
	}
	
	private void setDisplayImage(Type type) {
		try {
		switch (type) {
		case GRASS:
			displayImage = ImageIO.read(getClass().getResource("DirtWall.png"));
			break;
		case GRASSTILE:
			displayImage = ImageIO.read(getClass().getResource("DirtWallTile.png"));
			break;
		case GRASSGROUND:
			displayImage = ImageIO.read(getClass().getResource("GrassGround.png"));
		}
		} catch(Exception e) {}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(displayImage, x_position, y_position, tileSize, (int)(tileSize*1.5), null);
	}
	
	public boolean getCanBeWalkedOn() {
		return canBeWalkedOn;
	}
	
	public int getId() {
		return id;
	}
}

package finalProject2026;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Wall extends Enviorment{
	
	private Type type;
	
	public Wall(Type type, int x_position, int y_position, int tileSize) {
		super(type, x_position, y_position, tileSize);
		this.type=type;
	}
	
	public Type getType() {
		return type;
	}
}

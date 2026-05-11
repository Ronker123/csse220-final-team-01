package finalProject2026;

import java.awt.Rectangle;

public class Floor extends Enviorment{
	
	private Rectangle border;
	
	public Floor(Type type, int x_position, int y_position, int tileSize, int id) {
		super(type, x_position, y_position, tileSize, id);
		super.canBeWalkedOn = true;
		border = new Rectangle(x_position, y_position, tileSize, tileSize);
	}
	
	public Rectangle getBorder() {
		return border;
	}
}

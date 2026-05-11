package finalProject2026;

import java.awt.Graphics2D;

public class Level {
	
	private Enviorment[] tiles;
	private State state;
	
	public Level(Enviorment[] tiles, State state) {
		
		this.tiles = tiles;
		this.state = state;
		
	}
	
	public State getState() {
		return state;
	}
	
	public void draw(Graphics2D g2) {	
		for(Enviorment tile : tiles) {
			tile.draw(g2);
		}
	}
	
	public void drawFGTiles(Graphics2D g2, int layer) {
		for(Enviorment tile : tiles) {
			if(tile.getId() >= layer*20 && tile.getId() < layer*40+20 && !tile.getCanBeWalkedOn()) {
				tile.draw(g2);
			}
		}
	}
	
	public Enviorment getTile(int i) {
		return tiles[i];
	}
	
	public int getEnviormentSize() {
		return tiles.length;
	}
}

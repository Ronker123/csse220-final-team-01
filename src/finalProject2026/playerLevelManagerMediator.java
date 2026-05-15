package finalProject2026;

import java.awt.Graphics2D;

public class playerLevelManagerMediator {
	
	private static Level level;
//	private int playerX;
//	private int playerY=4;
	private static Enviorment[] tiles = new Enviorment[4];
	private boolean drawPlayer = false;
	
	public static void setLevel(Level level) {
		playerLevelManagerMediator.level = level;
	}
	
	public static void drawForeGroundTiles(Graphics2D g2, int playerY, int playerX) {
		
		if (level == null) {
	        return; 
	    }
		
	    if (level != null) {
	        level.drawFGTiles(g2, playerY, playerX);
	    }
	}
	
	public static void setPlayerPosition(int x, int y) {
		
		// 40 in this context is width and height of the tiles
		
		x-=880;
		y-=120;
		adjacentTiles(x, y);
	}
	
	private static void adjacentTiles(int playerX, int playerY) {
		
		if(level == null) return;
	
		
		// 23 in this context is how many tiles wide the screen is
		int rowLength = 23;
		
		// Each of these evaluate where the adjacent tile is based on the fact that each level has a long list of the tiles
		int up =
				playerX+(playerY-1)*rowLength < 0 ? -1 :
				playerX+(playerY-1)*rowLength;
		
		int right =
				playerX+1+playerY*rowLength >= (playerY+1)*rowLength ? -1 :
				playerX+1+playerY*rowLength;
				
		int down =
				playerX+(playerY+1)*rowLength >= level.getEnviormentSize() ? -1 :
				playerX+(playerY+1)*rowLength;
				
		int left =
				playerX-1+playerY*rowLength < (playerY)*rowLength ? -1 :
				playerX-1+playerY*rowLength;
		
		
		// Goes through the list of tiles to identify the correct one based on the id of the tile and assigns it a designated
		// Enviorment tile from the possible tiles.
		for(int i = 0; i<level.getEnviormentSize(); i++) {
			if(level.getTile(i).getId() == up) {
				tiles[0] = level.getTile(i);
				continue;
			}
			
			if(level.getTile(i).getId() == right) {
				tiles[1] = level.getTile(i);
				continue;
			}
			
			if(level.getTile(i).getId() == down) {
				tiles[2] = level.getTile(i);
				continue;
			}
			
			if(level.getTile(i).getId() == left) {
				tiles[3] = level.getTile(i);
				continue;
			}
		}
		
		// When evaluating the adjacent tiles, if any were out of bounds, values got set to -1
		// this allowed me to here make the statement that if this is -1, then the tile is actually null
		if(up == -1) tiles[0] = null;
		if(right == -1) tiles[1] = null;
		if(down == -1) tiles[2] = null;
		if(left == -1) tiles[3] = null;
		
	}
	
	// Returns walkability for each of the four adjacent tiles.
	public static boolean canMoveTo(String direction) {
		switch(direction) {
		case "UP":
			if(tiles[0]!=null) return tiles[0].getCanBeWalkedOn();
			return false;
		case "DOWN":
			if(tiles[2]!=null) return tiles[2].getCanBeWalkedOn();
			return false;
		case "RIGHT":
			if(tiles[1]!=null) return tiles[1].getCanBeWalkedOn();
			return false;
		case "LEFT":
			if(tiles[3]!=null) return tiles[3].getCanBeWalkedOn();
			return false;
		}
		return false;
	}
	
	
	public static boolean isTileWalkable(int x, int y) {
	    if (level == null) return false;
	    
	    // Snap the requested coordinates to grid
	    int gridX = x / 40;
	    int gridY = y / 40;
	    int targetId = gridX + (gridY * 20); 

	    // Scan level tiles to see if this ID is walkable
	    for (int i = 0; i < level.getEnviormentSize(); i++) {
	        if (level.getTile(i).getId() == targetId) {
	            return level.getTile(i).getCanBeWalkedOn();
	        }
	    }
	    return false;
	}
	
	
}
	
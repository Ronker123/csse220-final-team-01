package finalProject2026;

public class playerLevelManagerMediator {
	
	private Level level;
	private int playerX;
	private int playerY=4;
	private Enviorment[] tiles = new Enviorment[4];
	private boolean drawPlayer = false;
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setPlayerPosition(int x, int y) {
		playerX=x-x%40;
		playerY=y-y%40;
		
		playerX/=40;
		playerY/=40;
		
		adjacentTiles();
	}
	
	private void adjacentTiles() {
		try{tiles[0]=level.getTile(playerX+(playerY-1)*15);} catch(Exception e) {tiles[0]=null;}
		try{tiles[1]=level.getTile(playerX+1+playerY*15);} catch(Exception e) {tiles[1]=null;}
		try{tiles[2]=level.getTile(playerX+(playerY+1)*15);} catch(Exception e) {tiles[2]=null;}
		try{tiles[3]=level.getTile(playerX-1+playerY*15);} catch(Exception e) {tiles[3]=null;}
	}
	
	public boolean canGoLeft() {
		if(tiles[2]!=null) return tiles[2].getCanBeWalkedOn();
		return false;
	}
	
	public boolean canGoRight() {
		if(tiles[1]!=null) return tiles[1].getCanBeWalkedOn();
		return false;
	}
	
	public boolean canGoUP() {
		if(tiles[0]!=null) return tiles[0].getCanBeWalkedOn();
		return false;
	}
	
	public boolean canGoDown() {
		if(tiles[3]!=null) return tiles[3].getCanBeWalkedOn();
		return false;
	}
	
	public int getRow() {
		return playerY;
	}
}

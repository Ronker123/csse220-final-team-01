package finalProject2026;

import java.awt.Graphics2D;

public class EntityManager {
    
    private State state;
    private KeyHandler KH; 
    
    private Player player;
	private Zombie zombie;
	private Coin coin;
	private Exit exit;
    
	public EntityManager(State state, KeyHandler KH) {
	    this.state = state;
	    this.KH = KH; 
	    
	    this.player = new Player(920, 160, KH); 
	    
//	    int startX, startY;
//	    boolean isSafe = false;
//	    java.util.Random rand = new java.util.Random();
//
//	    do {
//	        startX = rand.nextInt(20) * 40; // Pick a random column (0-19)
//	        startY = rand.nextInt(15) * 40; // Pick a random row 
//	        
//	        isSafe = plmm.isTileWalkable(startX, startY);
//	    } while (!isSafe); // Keep looking until we find an empty tile

	    this.zombie = new Zombie(920, 920, this.plmm);
	    this.coin = new Coin(200, 100, this.plmm);
//	    this.exit = new Exit(300, 300, this.plmm);
	}

	public void update(State state) {
	    if (player != null) {
	        player.update(state); 
	    }
	    
	    if (zombie != null) { 
	        zombie.update(); 
	    }
	    
	    if (coin != null) {
            coin.update(player.getX(), player.getY());
            
            //Remove it from memory entirely once collected
            if (coin.isCollected()) {
                coin = null; 
            }
	    }
	}
    
    public void draw(Graphics2D g2) {
        if (player != null) {
            player.draw(g2);
        }
        if (zombie != null) {
            zombie.draw(g2); 
        }
        if (coin != null) {
            coin.draw(g2);
        }
        if (exit != null) {
            exit.draw(g2); 
        }
        
    }
}

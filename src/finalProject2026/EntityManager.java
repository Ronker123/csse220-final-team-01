
package finalProject2026;

import java.awt.Graphics2D;

public class EntityManager {
    
    private State state;
    private playerLevelManagerMediator plmm;
    private KeyHandler KH; 
    
    private Player player;
	private Zombie zombie;
	private Coin coin;
	private Exit exit;
    
	public EntityManager(State state, playerLevelManagerMediator plmm, KeyHandler KH) {
	    this.state = state;
	    this.plmm = plmm;
	    this.KH = KH; 
	    
	    this.player = new Player(200, 200, this.plmm, KH); 
	    this.zombie = new Zombie(50, 20, this.plmm);
	    this.coin = new Coin(100, 100, this.plmm);
	    this.exit = new Exit(300, 300, this.plmm);
	}

	public void update(State state) {
	    if (player != null) {
	        player.update(); 
	    }
	    
	    if (zombie != null) { 
	        zombie.update(); 
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

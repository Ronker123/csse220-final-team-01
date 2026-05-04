package finalProject2026;

import java.awt.Graphics2D;

public class EntityManager {
	
	private State state;
	private playerLevelManagerMediator plmm;
	
	public EntityManager(State state, playerLevelManagerMediator plmm) {
		this.state = state;
		this.plmm = plmm;
		// Pass the plmm through the player and only the player.
		// plmm does not tell, it will only respond and can be told to.
		/* For now just hard code some sample entities to test
		Player player = new Player(whatever it needs);
		Zombie zombie = new Zombie(whatever it needs);
		Coin coin = new Coin(whatever it needs);
		Exit exit = new Exit(whatever it needs);
		*/
	}
	
	public void update(State state) {
		/*
		 * update player, zombie, coin, exit
		 */
	}
	
	public void draw(Graphics2D g2) {
		/*
		 * draw player, zombie, coin, exit
		 */
	}
}

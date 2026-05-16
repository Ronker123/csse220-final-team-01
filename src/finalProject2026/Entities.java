
package finalProject2026;
import java.awt.Graphics2D;

public class Entities {
    private GameState gameState;
    private EntityManager entityManager;
    private State state; 
    
    public Entities(GameState gs, KeyHandler kh) {
        this.gameState = gs;
        entityManager = new EntityManager(kh);
    }
	
	public void update() {
		entityManager.update(gameState.getState());
	}
	
	public void draw(Graphics2D g2) {
		entityManager.draw(g2);
	}

	public State getState() {
		// TODO Auto-generated method stub
		return state;
	}
	
}

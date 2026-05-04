package finalProject2026;

import java.awt.Graphics2D;

public class Entities {
	
	private GameState gameState;
	private EntityManager entityManager;
	
	public Entities(GameState gs) {
		this.gameState = gs;
		entityManager = new EntityManager(gameState.getState(), gameState.getPLMM());
	}
	
	public void update() {
		entityManager.update(gameState.getState());
	}
	
	public void draw(Graphics2D g2) {
		entityManager.draw(g2);
	}
	
}

package finalProject2026;

import java.awt.Graphics2D;

public class GameState {
	
	private State state;
	private LevelManager level;
	
	public GameState() {
//		this.state = State.MAINMENU;
		this.state = State.LEVELONE;
		this.level = new LevelManager();
	}
	
	public void update() {
		level.update(state);
	}
	
	public void draw(Graphics2D g2) {
		level.draw(g2, state);
	}
	
	public State getState() {
		return state;
	}
}

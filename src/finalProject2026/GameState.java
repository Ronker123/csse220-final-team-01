package finalProject2026;

import java.awt.Graphics2D;

public class GameState {
	
	private State state;
	private LevelManager level;
	private playerLevelManagerMediator plmm;
	
	public GameState() {
//		this.state = State.MAINMENU;
		plmm = new playerLevelManagerMediator();
		
		this.state = State.LEVELONE;
		this.level = new LevelManager(plmm);
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
	
	public playerLevelManagerMediator getPLMM() {
		return plmm;
	}
}

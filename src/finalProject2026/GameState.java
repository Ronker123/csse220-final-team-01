package finalProject2026;

import java.awt.Graphics2D;

public class GameState {
	
	private State state;
	private LevelManager levelManager;
	private playerLevelManagerMediator plmm;
	
	public GameState(MouseHandler mb) {
		plmm = new playerLevelManagerMediator();
		
		this.state = State.MAINMENU;
		this.levelManager = new LevelManager(plmm, mb);
	}
	
	public void update() {
		levelManager.update(state);
		setState();
	}
	
	public void draw(Graphics2D g2) {
		levelManager.draw(g2, state);
	}
	
	public State getState() {
		return state;
	}
	
	public void setState() {
		if(levelManager.getNewState() != null) state = levelManager.getNewState();
	}
	
	public playerLevelManagerMediator getPLMM() {
		return plmm;
	}
}

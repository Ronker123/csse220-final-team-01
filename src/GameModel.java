package model;

public class GameModel {
	
	private Player Player;
	private Coin Coin; 
	private Zombie Zombie;
	private Exit Exit; 
	
	public GameModel() {
		this.Player = new Player(5, 5); 
		this.Coin = new Coin(20, 20); 
		this.Zombie = new Zombie(5, 20); 
		this.Exit = new Exit(30, 30); 
		
	}
	
	// the following movePlayer methods move the player by 1
	// in the respective direction 
	public void movePlayerUp(){
		Player.moveBy(0, -1);
	}
	
	public void movePlayerDown(){
		Player.moveBy(0, 1);
	}
	
	public void movePlayerLeft(){
		Player.moveBy(-1, 0);
	}
	
	public void movePlayerRight(){
		Player.moveBy(1, 0);
	}

}

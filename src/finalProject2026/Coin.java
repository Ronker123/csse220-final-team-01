package finalProject2026;

import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Coin {
    private int x, y;
//    private int tarX, tarY;
//    private int speed = 5;
//    private int frameCount = 0;
    private boolean hitPlayer;
    
    private ArrayList<State> theNoNos = new ArrayList<>(Arrays.asList(State.MAINMENU, State.PAUSED));

    private State state;
    
//    private Enviorment[] adjTiles = new Enviorment[4];
    
//    private Random random = new Random();
    
    private BufferedImage sprite;
    private Rectangle hitbox;
    private final int SPRITE_SIZE = 40;

    private Player player;
    
    public Coin(int startX, int startY, Player player) {
        this.x = startX;
        this.y = startY;
        
        this.player = player;
        
        this.hitPlayer = false;
        
        hitbox = new Rectangle(this.x, this.y, SPRITE_SIZE, SPRITE_SIZE);
        
        loadSprite();
//        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Coin.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find coin sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
    	
    	if(state == State.MAINMENU || hitPlayer == true) return;
    	
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
            g2.draw(hitbox);
        } else {
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
        
        playerLevelManagerMediator.drawForeGroundTiles(g2, x, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(State state) {
        // Update the mediator with the intended destination for collision
    	
    	if (this.state!= state) hitPlayer = false;
    	
    	this.state = state;
    	
    	if (!theNoNos.contains(state)) {
	        if(player.getHitbox().intersects(hitbox) && !hitPlayer) {
	        	player.collidesCoin();
	        	this.hitPlayer = true;
	        }
    	}
    }
    
}
    
    

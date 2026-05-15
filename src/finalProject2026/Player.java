package finalProject2026;

import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private int x, y;
    private int tarX, tarY;
    private int speed = 5;
    private int frameCount = 0;
    private ArrayList<State> theNoNos = new ArrayList<>(Arrays.asList(State.MAINMENU, State.PAUSED));
    
    private State state;
    
    private playerLevelManagerMediator mediator;
    private KeyHandler keyH; 
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Player(int startX, int startY, playerLevelManagerMediator mediator, KeyHandler keyH) {
        this.x = startX;
        this.y = startY;
        
        this.tarX = startX;
        this.tarY = startY;
        
        this.mediator = mediator;
        this.keyH = keyH; 
        
        loadSprite();
        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
        	sprite = ImageIO.read(getClass().getResourceAsStream("/Steve.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find player sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
    	
    	if(state == State.MAINMENU) return;
    	
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
        } else {
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
        
        mediator.drawForeGroundTiles(g2);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(State state) {
    	if (keyH == null) return;
    	
    	this.state = state;
    	
    	if (!theNoNos.contains(state)) {
    	mediator.setPlayerPosition(this.tarX, this.tarY);
    	
    	moveToTarget();
    	setTargetPos();
    	}
    }
    
    private void moveToTarget() {
		x += x < tarX ? 5 : 0;
		x -= x > tarX ? 5 : 0;
		y -= y > tarY ? 5 : 0;
		y += y < tarY ? 5 : 0;
	}
    
    private void setTargetPos() {
		frameCount++;
		
		if(frameCount != 1 && frameCount < 15) return;
		if(frameCount%8 != 1) return;
		
		if(keyH.keys[KeyEvent.VK_W] && mediator.canMoveTo("UP")) {
			tarY-=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_D] && mediator.canMoveTo("RIGHT")) {
			tarX+=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_S] && mediator.canMoveTo("DOWN")) {
			tarY+=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_A] && mediator.canMoveTo("LEFT")) {
			tarX-=40;
			return;
		}
		
		frameCount = 0;
	}
    
    public boolean hasKeys() {
        return keyH != null;
    }
}
package finalProject2026;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private int x, y;
    private int tarX, tarY;
    private int startX, startY;
    private Enviorment[] adjTiles;
    private int frameCount = 0;
    
    private int score;
    private int life;
    
    private ArrayList<State> theNoNos = new ArrayList<>(Arrays.asList(State.MAINMENU, State.PAUSED));
    
    private State state;
    
    private KeyHandler keyH; 
    
    private BufferedImage sprite;
    private Rectangle hitbox;
    private final int SPRITE_SIZE = 40; 

    public Player(int startX, int startY, KeyHandler keyH) {
        this.x = startX;
        this.y = startY;
        
        this.tarX = startX;
        this.tarY = startY;
        
        this.startX = startX;
        this.startY = startY;
        
        this.keyH = keyH; 
        
        this.life = 3;        
        hitbox = new Rectangle(this.x, this.y, SPRITE_SIZE, SPRITE_SIZE);
        
        loadSprite();
        adjTiles = playerLevelManagerMediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
        	sprite = ImageIO.read(getClass().getResourceAsStream("/Steve.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find player sprite file.");
            e.printStackTrace();
        }
    }
    
    public void collidesZombie() {
    	score-=50;
    	life--;
    	x = startX;
    	y = startY;
    	
    	tarX = startX;
    	tarY = startY;
    	
    	hitbox.setLocation(startX, startY);
    }
    
    public void collidesCoin() {
    	score+=100;	
    }
    
    public Rectangle getHitbox() {
    	return this.hitbox;
    }

    public void draw(Graphics2D g2) {
    	Color oc = g2.getColor();
    	Font of = g2.getFont();
    	
    	if(state == State.MAINMENU) return;
    	
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
            g2.draw(hitbox);
        } else {
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
        
        g2.setColor(Color.red);
        
        for(int i=0; i<this.life; i++) {
        	int x, y;
        	
        	x = 400; y = 150;
        	
        	g2.fillOval(x+100*i, y, 50, 50);
        	g2.fillOval(x+36+100*i, y, 50, 50);
        	
        	g2.rotate(Math.toRadians(45.0), (x+18+100*i)+25, (y+18)+25);
        	
        	g2.fillRect(x+18+100*i, y+18, 50, 50);
        	
        	g2.rotate(Math.toRadians(-45.0), (x+18+100*i)+25, (y+18)+25);
        }
        
        g2.setColor(Color.black);
        
        for(int i=0; i > this.life - 3; i--) {
        	int x, y;
        	
        	x = 586; y = 150;
        	
        	g2.fillOval(x+100*i, y, 50, 50);
        	g2.fillOval(x+36+100*i, y, 50, 50);
        	
        	g2.rotate(Math.toRadians(45.0), (x+18+100*i)+25, (y+18)+25);
        	
        	g2.fillRect(x+18+100*i, y+18, 50, 50);
        	
        	g2.rotate(Math.toRadians(-45.0), (x+18+100*i)+25, (y+18)+25);
        }
        
        g2.setFont(new Font("Arial", Font.PLAIN, 64));
        g2.drawString(Integer.toString(score), 700, 190);
        
        g2.setFont(of);
        g2.setColor(oc);
        
        playerLevelManagerMediator.drawForeGroundTiles(g2, x, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(State state) {
    	if (keyH == null) return;
    	
    	this.state = state;
    	
    	if (!theNoNos.contains(state) && life > 0) {
    		hitbox.setLocation(this.tarX, this.tarY);
    		
	    	adjTiles = playerLevelManagerMediator.setPlayerPosition(this.tarX, this.tarY);
	    	
	    	moveToTarget();
	    	setTargetPos();
    	}
    }
    
    private void moveToTarget() {
		x += x < tarX ? 3 : 0;
		x -= x > tarX ? 3 : 0;
		y -= y > tarY ? 3 : 0;
		y += y < tarY ? 3 : 0;
	}
    
    private void setTargetPos() {
		frameCount++;
		
//		if(frameCount != 1 && frameCount < 15) return;
		if(frameCount%13 != 1) return;
		
		if(keyH.keys[KeyEvent.VK_W] && playerLevelManagerMediator.canMoveTo("UP", adjTiles)) {
			tarY-=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_D] && playerLevelManagerMediator.canMoveTo("RIGHT", adjTiles)) {
			tarX+=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_S] && playerLevelManagerMediator.canMoveTo("DOWN", adjTiles)) {
			tarY+=40;
			return;
		}
		if(keyH.keys[KeyEvent.VK_A] && playerLevelManagerMediator.canMoveTo("LEFT", adjTiles)) {
			tarX-=40;
			return;
		}
		
		frameCount = 0;
	}
    
    public boolean hasKeys() {
        return keyH != null;
    }
}
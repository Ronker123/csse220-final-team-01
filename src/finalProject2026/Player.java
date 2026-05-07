package finalProject2026;

import java.awt.event.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Player {
    private int x, y;
    private int speed = 5;
    private playerLevelManagerMediator mediator;
    private KeyHandler keyH; 
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Player(int startX, int startY, playerLevelManagerMediator mediator, KeyHandler keyH) {
        this.x = startX;
        this.y = startY;
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

    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
        } else {
            g.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
    }

    public void move(String direction) {
        
        switch (direction.toUpperCase()) {
            case "UP":
                if (mediator.canGoUP()) y -= speed;
                break;
            case "DOWN":
                if (mediator.canGoDown()) y += speed;
                break;
            case "LEFT":
                if (mediator.canGoLeft()) x -= speed;
                break;
            case "RIGHT":
                if (mediator.canGoRight()) x += speed;
                break;
        }

        mediator.setPlayerPosition(this.x, this.y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update() {
    	if (keyH == null) return;
    	
    	if (keyH.keys[KeyEvent.VK_W] && mediator.canGoUP()) {
            this.y -= speed;
        }
    	if (keyH.keys[KeyEvent.VK_S] && mediator.canGoDown()) {
            this.y += speed;
        }if (keyH.keys[KeyEvent.VK_A] && mediator.canGoLeft()) {
            this.x -= speed;
        }
        if (keyH.keys[KeyEvent.VK_D] && mediator.canGoRight()) {
            this.x += speed;
        }
    	

        mediator.setPlayerPosition(this.x, this.y);
    }
    
    public boolean hasKeys() {
        return keyH != null;
    }
}
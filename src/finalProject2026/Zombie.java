package finalProject2026;

import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Zombie {
    private int x, y;
    private int tarX, tarY;
    private int speed = 5;
    private int frameCount = 0;
    
    private playerLevelManagerMediator mediator;
    private Random random = new Random();
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Zombie(int startX, int startY, playerLevelManagerMediator mediator) {
        this.x = startX;
        this.y = startY;
        
        this.tarX = startX;
        this.tarY = startY;
        
        this.mediator = mediator;
        
        loadSprite();
        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
        	sprite = ImageIO.read(getClass().getResourceAsStream("/Zombie.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find player sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
        } else {
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
        
        mediator.drawForeGroundTiles(g2);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update() {
        // Update the mediator with the intended destination for collision
        mediator.setPlayerPosition(this.tarX, this.tarY);
        
        moveToTarget();
        setTargetPos();
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
        if(frameCount % 8 != 1) return;
        
        if (x == tarX && y == tarY) {
            int direction = random.nextInt(4); 
            
            switch (direction) {
                case 0: // UP
                    if (mediator.canMoveTo("UP")) tarY -= 40;
                    break;
                case 1: // RIGHT
                    if (mediator.canMoveTo("RIGHT")) tarX += 40;
                    break;
                case 2: // DOWN
                    if (mediator.canMoveTo("DOWN")) tarY += 40;
                    break;
                case 3: // LEFT
                    if (mediator.canMoveTo("LEFT")) tarX -= 40;
                    break;
            }
        }
        
        // Reset frameCount if a move was attempted or to keep the loop going
        if (frameCount > 30) frameCount = 0; 
    }
    
   }
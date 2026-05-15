package finalProject2026;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Coin {
    private int x, y;
    private playerLevelManagerMediator mediator;
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 
    private boolean isCollected = false;

    public Coin(int startX, int startY, playerLevelManagerMediator mediator) {
        this.x = startX;
        this.y = startY;
        this.mediator = mediator;
        
        loadSprite();
        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
        	sprite = ImageIO.read(getClass().getResourceAsStream("/Coin.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find player sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        // Only draw the object if it hasn't been collected yet
        if (!isCollected) {
            if (sprite != null) {
                g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
            } else {
                // Fallback if image fails to load
                g2.setColor(java.awt.Color.YELLOW);
                g2.fillOval(x, y, SPRITE_SIZE, SPRITE_SIZE);
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(int playerX, int playerY) {
        if (isCollected) return;

        // Checks if the player's coordinates overlap this object
        if (playerX < x + SPRITE_SIZE && playerX + SPRITE_SIZE > x && 
            playerY < y + SPRITE_SIZE && playerY + SPRITE_SIZE > y) {
            
            this.isCollected = true;
            handlePickup();
        }
    }

    private void handlePickup() {
        // Logic for when the item is grabbed (e.g., play sound, increment score)
        
    }

    public boolean isCollected() { return isCollected; }
}

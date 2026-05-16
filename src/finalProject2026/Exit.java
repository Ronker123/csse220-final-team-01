package finalProject2026;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Exit {
    private int x, y;
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40;
    private boolean isCompleted = false;

    public Exit(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        loadSprite();
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Exit.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find exit sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics g) {
        if (!isCompleted) {
            if (sprite != null) {
                g.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
            } else {
                g.setColor(java.awt.Color.RED);
                g.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(Player player) {
        if (isCompleted || player == null) return;
        
        // Check collision with player
        int playerX = player.getX();
        int playerY = player.getY();
        
        if (playerX < x + SPRITE_SIZE && playerX + SPRITE_SIZE > x && 
            playerY < y + SPRITE_SIZE && playerY + SPRITE_SIZE > y) {
            
            this.isCompleted = true;
            handleLevelCompletion();
        }
    }
    
    private void handleLevelCompletion() {
        // Logic for when the level is completed
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
}
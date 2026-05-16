package finalProject2026;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Zombie {
    private int x, y;
    private int tarX, tarY;
    private Enviorment[] adjTiles;
    private int frameCount = 0;
    
    private Random random = new Random();
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Zombie(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        
        this.tarX = startX;
        this.tarY = startY;
        
        loadSprite();
        // Initialize adjacency tiles
        adjTiles = playerLevelManagerMediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Zombie.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find zombie sprite file.");
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
        } else {
            g2.setColor(java.awt.Color.GREEN);
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update() {
        // Update adjacency tiles based on current target position
        adjTiles = playerLevelManagerMediator.setPlayerPosition(this.tarX, this.tarY);
        
        moveToTarget();
        setTargetPos();
    }
    
    private void moveToTarget() {
        if (x < tarX) x += 5;
        if (x > tarX) x -= 5;
        if (y < tarY) y += 5;
        if (y > tarY) y -= 5;
    }
    
    private void setTargetPos() {
        frameCount++;
        
        // Only attempt to choose new direction occasionally (every 30 frames)
        if (frameCount < 30) return;
        
        // Check if we've reached the target
        if (Math.abs(x - tarX) <= 5 && Math.abs(y - tarY) <= 5) {
            int direction = random.nextInt(4);
            int newTarX = tarX;
            int newTarY = tarY;
            
            switch (direction) {
                case 0: // UP
                    if (playerLevelManagerMediator.canMoveTo("UP", adjTiles)) 
                        newTarY -= 40;
                    break;
                case 1: // RIGHT
                    if (playerLevelManagerMediator.canMoveTo("RIGHT", adjTiles)) 
                        newTarX += 40;
                    break;
                case 2: // DOWN
                    if (playerLevelManagerMediator.canMoveTo("DOWN", adjTiles)) 
                        newTarY += 40;
                    break;
                case 3: // LEFT
                    if (playerLevelManagerMediator.canMoveTo("LEFT", adjTiles)) 
                        newTarX -= 40;
                    break;
            }
            
            // Only update if the move is valid (coordinates changed)
            if (newTarX != tarX || newTarY != tarY) {
                tarX = newTarX;
                tarY = newTarY;
            }
        }
        
        frameCount = 0;
    }
}
package finalProject2026;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Zombie {
    private int x, y;
    private int tarX, tarY;
    private Enviorment[] adjTiles;
    private int frameCount = 0;
    private boolean hitPlayer;
    
    private ArrayList<State> theNoNos = new ArrayList<>(Arrays.asList(State.MAINMENU, State.PAUSED));

    private State state;
    
    private Enviorment[] adjTiles = new Enviorment[4];
    
    private Random random = new Random();
    
    private BufferedImage sprite;
    private Rectangle hitbox;
    private final int SPRITE_SIZE = 40; 

    private Player player;
    
    public Zombie(int startX, int startY, Player player) {
        this.x = startX;
        this.y = startY;
        
        this.tarX = startX;
        this.tarY = startY;
        
        this.player = player;
        
        this.hitPlayer = false;
        
        hitbox = new Rectangle(this.x, this.y, SPRITE_SIZE, SPRITE_SIZE);
        
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
    	
    	if(state == State.MAINMENU) return;
    	
        if (sprite != null) {
            g2.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
            g2.draw(hitbox);
        } else {
            g2.setColor(java.awt.Color.GREEN);
            g2.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
        
        playerLevelManagerMediator.drawForeGroundTiles(g2, x, y);
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void update(State state) {
        // Update the mediator with the intended destination for collision
    	
    	this.state = state;
    	
    	if (!theNoNos.contains(state)) {
	    	hitbox.setLocation(this.tarX, this.tarY);
	    	
	    	adjTiles = playerLevelManagerMediator.setPlayerPosition(this.tarX, this.tarY);
	        
	        moveToTarget();
	        setTargetPos();
	        
	        if(player.getHitbox().intersects(hitbox) && !hitPlayer) {
	        	player.collidesZombie();
	        	this.hitPlayer = true;
	        }
    	}
    }
    
    private void moveToTarget() {
		x += x < tarX ? 1 : 0;
		x -= x > tarX ? 1 : 0;
		y -= y > tarY ? 1 : 0;
		y += y < tarY ? 1 : 0;
	}
    
    private void setTargetPos() {
        frameCount++;
        
        // Only attempt to choose new direction occasionally (every 30 frames)
        if (frameCount < 30) return;
        
        int range = random.nextInt(30);
        int offSet = random.nextInt(30);
        
        if (x == tarX && y == tarY && ((frameCount > offSet && frameCount < offSet+range) || hitPlayer)) {
            int direction = random.nextInt(4); 
            
            switch (direction) {
                case 0: // UP
                    if (playerLevelManagerMediator.canMoveTo("UP", adjTiles)) tarY -= 40;
                    break;
                case 1: // RIGHT
                    if (playerLevelManagerMediator.canMoveTo("RIGHT", adjTiles)) tarX += 40;
                    break;
                case 2: // DOWN
                    if (playerLevelManagerMediator.canMoveTo("DOWN", adjTiles)) tarY += 40;
                    break;
                case 3: // LEFT
                    if (playerLevelManagerMediator.canMoveTo("LEFT", adjTiles)) tarX -= 40;
                    break;
            }
            
            if (hitPlayer) {
            	hitPlayer = false;
            	
            	switch (direction) {
                case 2: // UP
                    if (playerLevelManagerMediator.canMoveTo("UP", adjTiles)) tarY -= 40;
                    break;
                case 3: // RIGHT
                    if (playerLevelManagerMediator.canMoveTo("RIGHT", adjTiles)) tarX += 40;
                    break;
                case 0: // DOWN
                    if (playerLevelManagerMediator.canMoveTo("DOWN", adjTiles)) tarY += 40;
                    break;
                case 1: // LEFT
                    if (playerLevelManagerMediator.canMoveTo("LEFT", adjTiles)) tarX -= 40;
                    break;
            	}
            }
            
            // Only update if the move is valid (coordinates changed)
            if (newTarX != tarX || newTarY != tarY) {
                tarX = newTarX;
                tarY = newTarY;
            }
        }
        
        frameCount%=30;
    }
}
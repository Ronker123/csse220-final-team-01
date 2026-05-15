package finalProject2026;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Zombie {
    private int x, y;
    private int speed = 2; 
    private playerLevelManagerMediator mediator;
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    // Logic variables for random movement
    private String currentDirection = "DOWN";
    private int moveTimer = 0;
    private Random rand = new Random();

    public Zombie(int startX, int startY, playerLevelManagerMediator mediator) {
        this.x = startX;
        this.y = startY;
        this.mediator = mediator;
        
        loadSprite();
        // Initialize the mediator's tile awareness for the starting position
        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
            sprite = ImageIO.read(getClass().getResourceAsStream("/Zombie.png"));
        } catch (IOException e) {
            System.out.println("Error: Could not find zombie sprite file.");
        }
    }

    public void draw(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, x, y, SPRITE_SIZE, SPRITE_SIZE, null);
        } else {
            g.fillRect(x, y, SPRITE_SIZE, SPRITE_SIZE);
        }
    }

    public void update() {
        // Synchronize the mediator with the zombie's current position
        mediator.setPlayerPosition(this.x, this.y);

        // Decide a new direction if the timer is up OR if we hit a wall
        if (moveTimer <= 0 || !mediator.canMoveTo(currentDirection)) {
            currentDirection = pickRandomDirection();
            moveTimer = 30 + rand.nextInt(60); // Stay on this path for 0.5 to 1.5 seconds
        }

        // Perform the move
        move(currentDirection);

        moveTimer--;
    }

    public void move(String direction) {
        // Only update coordinates if the mediator says the adjacent tile is walkable
        switch (direction.toUpperCase()) {
            case "UP":
                if (mediator.canMoveTo("UP")) y -= speed;
                break;
            case "DOWN":
                if (mediator.canMoveTo("DOWN")) y += speed;
                break;
            case "LEFT":
                if (mediator.canMoveTo("LEFT")) x -= speed;
                break;
            case "RIGHT":
                if (mediator.canMoveTo("RIGHT")) x += speed;
                break;
        }
    }

    private String pickRandomDirection() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        return directions[rand.nextInt(4)];
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
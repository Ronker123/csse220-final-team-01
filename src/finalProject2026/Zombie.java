package finalProject2026;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Zombie {
    private int x, y;
    private int speed = 5;
    private playerLevelManagerMediator mediator;
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Zombie(int startX, int startY, playerLevelManagerMediator mediator) {
        this.x = startX;
        this.y = startY;
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

//        if (mediator.canGoUP()) {
//            this.y -= speed;
//        } 
//        else if (mediator.canGoDown()) {
//            this.y += speed;
//        }
//        
//        if (mediator.canGoLeft()) {
//            this.x -= speed;
//        } 
//        else if (mediator.canGoRight()) {
//            this.x += speed;
//        }

        mediator.setPlayerPosition(this.x, this.y);
    }
}
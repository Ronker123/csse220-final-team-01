package finalProject2026;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Exit {
    private int x, y;
    private playerLevelManagerMediator mediator;
    
    private BufferedImage sprite;
    private final int SPRITE_SIZE = 40; 

    public Exit(int startX, int startY, playerLevelManagerMediator mediator) {
        this.x = startX;
        this.y = startY;
        this.mediator = mediator;
        
        loadSprite();
        this.mediator.setPlayerPosition(this.x, this.y);
    }

    private void loadSprite() {
        try {
        	sprite = ImageIO.read(getClass().getResourceAsStream("/Exit.png"));
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

    public int getX() { return x; }
    public int getY() { return y; }

    public void update() {
        mediator.setPlayerPosition(this.x, this.y);
    }
}
package finalProject2026;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;


public class Display extends JPanel implements Runnable {
    
    private KeyHandler kb = new KeyHandler(); 
    private Thread gameThread; 
    private GameState gameState;
    private Entities entities;
    
    public Display() {
        this.addKeyListener(kb); 
        this.setFocusable(true);
        this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
//      this.setPreferredSize(new Dimension(800, 600));
//		this.setBackground(new Color(74, 100, 74));
		
        this.gameState = new GameState();
        this.entities = new Entities(this.gameState, kb); 
    }
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	//Draws everything that needs be drawn, whether it is a level, entities, or menus
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		this.gameState.draw(g2);
		this.entities.draw(g2);
		
	}

	@Override
	public void run() {
		//Converts 60 fps to 60 fpns
		double timeInterval = 1000000000/60;
		double nextDrawTime = System.nanoTime() + timeInterval;
		
		while(gameThread != null) {
			//Updates and draws everything necessary taking any amount of 
			//time hopefully less than a 60th of a second.
			update();
			repaint();
			
			try {
				/*Finds the time that wasn't used to update and draw
				to tell the thread how long it needs to wait so that updating and drawing
				occurs at predictable and consistent times.*/
				double remainingTime = nextDrawTime-System.nanoTime();
				remainingTime = remainingTime<0 ? 0 : remainingTime;
				
				//Makes the thread take a break for the time left in the frame of time given.
				Thread.sleep((long)(remainingTime/1000000000));
				
				//Queues the next frame to occur for a 60th of a second.
				nextDrawTime+=timeInterval;
			}
			catch(Exception e) {System.out.println(e);}
		}
		
	}
	
	//Handles everything that needs be update, whether it is a level, entities, or menus
	private void update() {
		
		this.gameState.update();
		this.entities.update();
		
	}

}

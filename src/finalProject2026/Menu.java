package finalProject2026;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Menu {
	
	private BufferedImage menuDisplay;
	private int decisionInt = 1;
	private Button[] buttons;
	private MouseHandler mb;
	private State state;
	
	private Button exit;
	private Button l1;
	private Button menu;
	private Button pause;
	private Button play;
	private Button nextLevel;
	private Button mute;
	private Button muteGame;
	private Button settings;
	private Button inventory;
	private Button statistics;
	private Button credits;
	
	private final Action exitAction = () -> {
		boolean clicked = mb.buttons[0];
		boolean depricated = mb.depricated[0];
		if(clicked && !depricated) System.exit(0);
	};
	
	private final Action l1Action = () -> {
		boolean clicked = mb.buttons[0];
		boolean depricated = mb.depricated[0];
		if(clicked && !depricated) {
			setNewState(State.LEVELONE);
			mb.depricated[0] = true;
		}
		else setNewState(null);
	};
	
	private final Action menuAction = () -> {
		boolean clicked = mb.buttons[0];
		boolean depricated = mb.depricated[0];
		if(clicked && !depricated) {
			setNewState(State.MAINMENU);
			mb.depricated[0] = true;
		}
		else setNewState(null);
	};
	
	private final Action nextLevelAction = () -> {
		boolean clicked = mb.buttons[0];
		boolean depricated = mb.depricated[0];
		if(clicked && !depricated) {
			decisionInt+= decisionInt != 10 ? 1 : -9;
			setNewState(State.values()[decisionInt]);
			mb.depricated[0] = true;
		}
		else setNewState(null);
	};
	
	private final Action pauseAction = () -> {
		boolean clicked = mb.buttons[0];
		boolean depricated = mb.depricated[0];
		if(clicked && !depricated) {
			if(state != State.PAUSED) {setNewState(State.PAUSED);}
			else {setNewState(State.values()[decisionInt]);}
			mb.depricated[0] = true;
		}
	};
	
	public Menu(MouseHandler mb) {
		
		this.mb = mb;
		
		try {
			if(new Dimension(1920, 1080).equals(Toolkit.getDefaultToolkit().getScreenSize())) {
				BufferedImage base = ImageIO.read(getClass().getResource("menu1920x1080.png"));
				menuDisplay = base.getSubimage(192*3, 0, 192, 108);
			}
		}
		catch(Exception e) {System.out.println(e);}
		
		buttons = new Button[20];
		
		this.exit = new Button("Quit", 370, 882, 5, 64, 183162122, exitAction);
		this.l1 = new Button("1", 50, 200, 5, 64, 183162122, l1Action);
		this.menu  = new Button("Menu", 50, 300, 5, 64, 183162122, menuAction);
		this.pause = new Button("Pause Game", 50, 400, 5, 64, 183162122, pauseAction);
		this.play = new Button("Play", 370, 354, 5, 64, 183162122, l1Action);
		this.nextLevel = new Button("Next Level", 50, 500, 5, 64, 183162122, nextLevelAction);
		this.mute = new Button("Mute Music", 300, 100, 5, 64, 183162122, nextLevelAction);
		this.muteGame = new Button("Mute Music", 300, 200, 5, 64, 183162122, nextLevelAction);
		this.settings = new Button("Settings", 313, 486, 5, 64, 183162122, nextLevelAction);
		this.inventory = new Button("Inventory", 303, 618, 5, 64, 183162122, nextLevelAction);
		this.statistics = new Button("Statistics", 303, 618, 5, 64, 183162122, nextLevelAction);
		this.credits = new Button("Credits", 329, 750, 5, 64, 183162122, nextLevelAction);
		
		}
		
	public void update(State state) {
		
		switch (state){
		case MAINMENU:
			
			exit.setSelectable(true);
			play.setSelectable(true);
			settings.setSelectable(true);
			statistics.setSelectable(true);
			credits.setSelectable(true);
			
			pause.setSelectable(false);
			menu.setSelectable(false);
			nextLevel.setSelectable(false);
			
			buttons[0] = exit;
//			buttons[1] = l1;
//			buttons[2] = menu;
			buttons[3] = play;
//			buttons[4] = nextLevel;
//			buttons[5] = mute;
//			buttons[6] = muteGame;
			buttons[7] = settings;
//			buttons[8] = inventory;
			buttons[9] = statistics;
			buttons[10] = credits;
			
			break;
		default:
			
			menu.setSelectable(true);
			nextLevel.setSelectable(true);
			pause.setSelectable(true);
			
			exit.setSelectable(false);
			play.setSelectable(false);
			settings.setSelectable(false);
			statistics.setSelectable(false);
			credits.setSelectable(false);
			
			buttons[0] = menu;
			buttons[1] = nextLevel;
			buttons[2] = pause;
			buttons[3] = null;
			break;
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(menuDisplay, 0, 0, 1920, 1080, null);
		Color oc = g2.getColor();
		
		for(Button button : buttons) {
			if(button == null) continue;
			button.draw(g2);
			button.update();
		}
	}
	
	public State getNewState() {
		return this.state;
	}
	
	private void setNewState(State state) {
		this.state = state;
	}
	
	
}

interface Action{
	void action();
}

class Button {
	
	private String name;
	private int x, y, width, height;
	private boolean selectable = false;
	private BasicStroke stroke;
	private Font font;
	private Color color1;
	private Color color2;
	private Rectangle rectangle;
	private Rectangle position = null;
	private int[] point;
	private Action action;
	
	public Button(String name, int x, int y, int stroke, int height, int color, Action action) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.height = height;
		
		this.action = action;
		
		this.stroke = new BasicStroke(stroke);
		this.font = new Font("Arial", Font.PLAIN, height);
		
		int r = (int)(color/1000000);
		int g = (int)(color/1000)%1000%256;
		int b = (int)(color)%1000%256;
		
		this.color1 = new Color(r, g, b);
		
		r = r-17 < 0 ? 0 : r-17;
		g = g-17 < 0 ? 0 : g-17;
		b = b-17 < 0 ? 0 : b-17;
		
		this.color2 = new Color(r, g, b);
	}
	
	public void update() {
		if(selected() && selectable) action.action();
	}
	
	public void draw(Graphics2D g2) {
		Font oldFont = g2.getFont();
		Stroke oldStroke = g2.getStroke();
		Color oldColor = g2.getColor();
		
		g2.setStroke(stroke);
		g2.setFont(font);
		
		if(rectangle == null) {
			this.width = (int)g2.getFontMetrics().getStringBounds(name, g2).getWidth();
			this.height = (int)g2.getFontMetrics().getStringBounds(name, g2).getHeight();
			rectangle = new Rectangle(x-10, y-height/2-20, width+20, height);
		}
		if(point == null) point = getCenter(rectangle);
		
		boolean selected = selected();
		
		if(selected && selectable) {g2.rotate(Math.toRadians(3), point[0], point[1]);}
		g2.setColor(color1);
		g2.fillRect(x-10, y-height/2-20, width+20, height);
		
		g2.setColor(color2);
		g2.drawRect(x-10, y-height/2-20, width+20, height);
		
		g2.drawString(name, x, y);
		
		g2.setFont(oldFont);
		g2.setStroke(oldStroke);
		g2.setColor(oldColor);
		if(selected && selectable) {g2.rotate(Math.toRadians(-3), point[0], point[1]);}
	}
	
	private boolean selected() {
		if(position==null) position = new Rectangle(1, 1);
		position.setLocation(MouseInfo.getPointerInfo().getLocation());
		return rectangle.intersects(position);
	}
	
	private int[] getCenter(Rectangle r) {
		double w, h, x, y;
		w = r.getWidth();
		h = r.getHeight();
		x = r.getX();
		y = r.getY();
		
		int[] arr = {(int)(x+w/2), (int)(y+h/2)};
		
		return arr;
	}
	
	public void setSelectable(boolean b) {
		selectable = b;
	}
	
}
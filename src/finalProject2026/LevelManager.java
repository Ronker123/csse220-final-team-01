package finalProject2026;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import java.util.Scanner;

import javax.imageio.ImageIO;

public class LevelManager {
	
	private ArrayList<Level> levels = new ArrayList<>();
	private Level currentDisplayLevel;
	private State currentState;
	private Menu menu;
	
	public LevelManager(MouseHandler mb) {
		File levelStorage = new File("LevelStorage.txt");		
		try (Scanner lvlScanner = new Scanner(levelStorage)){
			while (lvlScanner.hasNextLine()) {
		        String data = lvlScanner.nextLine();
		        String[] dataArray = data.splitWithDelimiters("/s",-1);
		        State levelState = toState(dataArray[2]);
		        System.out.println(levelState);
		        levels.add(new Level(parseData(data), levelState));
		      }
		} catch(Exception e) {System.out.println(e);}
		
		menu = new Menu(mb);
	}
	
	public void draw(Graphics2D g2, State state) {
		if(currentDisplayLevel != null) currentDisplayLevel.draw(g2);
		menu.draw(g2);
	}
	
	public void update(State state) {
		if(state!=currentState) {
			menu.update(state);
			levels.forEach(n -> {if(n.getState()==state){
				currentDisplayLevel=n;
				playerLevelManagerMediator.setLevel(currentDisplayLevel);
			}
			else if(state == State.MAINMENU){
				currentDisplayLevel = null;
				playerLevelManagerMediator.setLevel(currentDisplayLevel);
			}});
			currentState=state;
		}
	}
	
	public State getNewState() {
		return menu.getNewState();
	}
	
	private Type toType(String data) {
		switch (data){
		case "GRASS":
			return Type.GRASS;
		case "GRASSTILE":
			return Type.GRASSTILE;
		case "GRASSGROUND":
			return Type.GRASSGROUND;
		case "STONE":
			return Type.STONE;
		case "STONETILE":
			return Type.STONETILE;
		case "STONEGROUND":
			return Type.STONEGROUND;
		case "SANDSTONE":
			return Type.SANDSTONE;
		case "SANDSTONETILE":
			return Type.SANDSTONETILE;
		case "SANDSTONEGROUND":
			return Type.SANDSTONEGROUND;
		case "MAGMA":
			return Type.MAGMA;
		case "MAGMATILE":
			return Type.MAGMATILE;
		case "MAGMAGROUND":
			return Type.MAGMAGROUND;
		}
	System.out.println("Type: "+data+" does not exist. Thrown from LevelManager.java ln 54");
	System.exit(0);
	return null;
	}
	
	private State toState(String data) {
		switch (data){
		case "LEVELONE":
			return State.LEVELONE;
		case "LEVELTWO":
			return State.LEVELTWO;
		case "LEVELTHREE":
			return State.LEVELTHREE;
		case "LEVELFOUR":
			return State.LEVELFOUR;
		case "LEVELFIVE":
			return State.LEVELFIVE;
		case "LEVELSIX":
			return State.LEVELSIX;
		case "LEVELSEVEN":
			return State.LEVELSEVEN;
		case "LEVELEIGHT":
			return State.LEVELEIGHT;
		case "LEVELNINE":
			return State.LEVELNINE;
		case "LEVELTEN":
			return State.LEVELTEN;
		}
	System.out.println("State: "+data+" does not exist. Thrown from LevelManager.java ln 66");
	System.exit(0);
	return null;
	}
	
	private Enviorment[] parseData(String data) {
		String regex = "(?:/lw)|(?:/lf)";
		String[] dataArray = data.splitWithDelimiters(regex, -1);
		
		String wallData = dataArray[4];
		String floorData = dataArray[2];
		regex = "/ti";
		String[] wallDataArray = wallData.splitWithDelimiters(regex, -1);
		String[] floorDataArray = floorData.splitWithDelimiters(regex, -1);

		int tileCount = wallDataArray.length/2+floorDataArray.length/2;
		
		Enviorment[] tiles= new Enviorment[tileCount];
		
		regex = "~";
		for(int i=1; i<floorDataArray.length; i+=2) {
			data=floorDataArray[i+1];
			String[] tileInformation = data.split(regex);
			Type type = toType(tileInformation[0]);
			int x = Integer.parseInt(tileInformation[1]);
			int y = Integer.parseInt(tileInformation[2]);
			int tileSize = Integer.parseInt(tileInformation[3]);
			int id = Integer.parseInt(tileInformation[4]);
			
			tiles[(int)((i-1)/2)] = new Floor(type, x, y, tileSize, id);
		}
		
		for(int i=1; i<wallDataArray.length; i+=2) {
			data=wallDataArray[i+1];
			String[] tileInformation = data.split(regex);
			Type type = toType(tileInformation[0]);
			int x = Integer.parseInt(tileInformation[1]);
			int y = Integer.parseInt(tileInformation[2]);
			int tileSize = Integer.parseInt(tileInformation[3]);
			int id = Integer.parseInt(tileInformation[4]);
			
			tiles[(int)((i-1)/2)+floorDataArray.length/2] = new Wall(type, x, y, tileSize, id);
		}
		
		return tiles;
	}
}
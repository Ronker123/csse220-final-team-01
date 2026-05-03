package finalProject2026;

import java.awt.Graphics2D;
import java.io.File;
import java.util.ArrayList;

import java.util.Scanner;

public class LevelManager {
	
	private ArrayList<Level> levels = new ArrayList<>();
	private Level currentDisplayLevel;
	private State currentState;
	
	public LevelManager() {
		File levelStorage = new File("LevelStorage.txt");		
		try (Scanner lvlScanner = new Scanner(levelStorage)){
			while (lvlScanner.hasNextLine()) {
		        String data = lvlScanner.nextLine();
		        String[] dataArray = data.splitWithDelimiters("/s",-1);
		        State levelState = toState(dataArray[2]);
		        levels.add(new Level(parseData(data), levelState));
		      }
		} catch(Exception e) {System.out.println(e);}
	}
	
	public void draw(Graphics2D g2, State state) {
		if(currentState == state) currentDisplayLevel.draw(g2);
	}
	
	public void update(State state) {
		if(state!=currentState) {
			levels.forEach(n -> {if(n.getState()==state){
				currentState=state;
				currentDisplayLevel=n;
			}});
		}
	}
	
	private Type toType(String data) {
		switch (data){
		case "GRASS":
			return Type.GRASS;
		case "GRASSTILE":
			return Type.GRASSTILE;
		case "GRASSGROUND":
			return Type.GRASSGROUND;
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
		}
	System.out.println("State: "+data+" does not exist. Thrown from LevelManager.java ln 66");
	System.exit(0);
	return null;
	}
	
	private Enviorment[] parseData(String data) {
		String regex = "(?:/lw)|(?:/lf)";
		String[] dataArray = data.splitWithDelimiters(regex, -1);
		
		String wallData = dataArray[2];
		String floorData = dataArray[4];
		regex = "/ti";
		String[] wallDataArray = wallData.splitWithDelimiters(regex, -1);
		String[] floorDataArray = floorData.splitWithDelimiters(regex, -1);

		int tileCount = wallDataArray.length/2+floorDataArray.length/2;
		
		Enviorment[] tiles= new Enviorment[tileCount];
		
		regex = "~";
		for(int i=1; i<wallDataArray.length; i+=2) {
			data=wallDataArray[i+1];
			String[] tileInformation = data.split(regex);
			Type type = toType(tileInformation[0]);
			int x = Integer.parseInt(tileInformation[1]);
			int y = Integer.parseInt(tileInformation[2]);
			int tileSize = Integer.parseInt(tileInformation[3]);
			
			tiles[(int)((i-1)/2)] = new Wall(type, x, y, tileSize);
		}
		
		int as = tileCount-wallDataArray.length/2;
		System.out.println(as);
		
		for(int i=1; i<floorDataArray.length; i+=2) {
			data=floorDataArray[i+1];
			String[] tileInformation = data.split(regex);
			Type type = toType(tileInformation[0]);
			int x = Integer.parseInt(tileInformation[1]);
			int y = Integer.parseInt(tileInformation[2]);
			int tileSize = Integer.parseInt(tileInformation[3]);
			
			tiles[(int)((i-1)/2)+wallDataArray.length/2] = new Floor(type, x, y, tileSize);
		}

		return tiles;
	}
}

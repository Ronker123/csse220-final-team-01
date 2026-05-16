package finalProject2026;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Graphics2D;

public class EntityManager {
    
    private ArrayList<EntityGroup> entityGroups = new ArrayList<>();
    private EntityGroup currentDisplayEntities;
    private State currentState;
    private KeyHandler keyH;
    private Player player;

    public EntityManager(KeyHandler keyH) {
        this.keyH = keyH;
        loadEntities();
    }

    private void loadEntities() {
        File levelStorage = new File("EntityStorage.txt");		
		try (Scanner entScanner = new Scanner(levelStorage)){
			while (entScanner.hasNextLine()) {
		        String data = entScanner.nextLine();

                if (data.contains("] ")) {
                    data = data.split("] ")[1];
                }

                String[] stateParts = data.split("/s");
                if (stateParts.length > 1) {
                    State levelState = toState(stateParts[1]);
                    entityGroups.add(new EntityGroup(parseEntities(data), levelState));
                }
		      }
		} catch(Exception e) {System.out.println(e);}
    }

    public void update(State state) {
        if (state != currentState) {
            currentDisplayEntities = null;
            for (EntityGroup group : entityGroups) {
                if (group.getState() == state) {
                    currentDisplayEntities = group;
                    break;
                }
            }
            currentState = state;
        }
        
        if (currentDisplayEntities != null) {
            for (Object e : currentDisplayEntities.getEntities()) {
                if (e instanceof Player) {
                    player = (Player) e;
                    ((Player) e).update(state);
                }
                if (e instanceof Zombie) {
                    ((Zombie) e).update();
                }
                if (e instanceof Coin && player != null) {
                    ((Coin) e).update(player.getX(), player.getY());
                }
                if (e instanceof Exit && player != null) {
                    ((Exit) e).update(player);  // Pass player to exit for collision detection
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (currentDisplayEntities != null) {
            for (Object e : currentDisplayEntities.getEntities()) {
                if (e instanceof Player) ((Player) e).draw(g2);
                if (e instanceof Zombie) ((Zombie) e).draw(g2);
                if (e instanceof Coin) ((Coin) e).draw(g2);
                if (e instanceof Exit) ((Exit) e).draw(g2);
            }
        }
    }

    private Object[] parseEntities(String data) {
        String[] parts = data.split("/");
        ArrayList<Object> entities = new ArrayList<>();

        for (String part : parts) {
            if (part.isEmpty() || part.startsWith("s") || part.equals("lf")) {
                continue;
            }

            char typeCode = part.charAt(0);
            String[] details = part.split("~");

            if (details.length >= 3) {
                try {
                    int x = Integer.parseInt(details[1]);
                    int y = Integer.parseInt(details[2]);
                    
                    Object e = createEntity(typeCode, x, y);
                    if (e != null) entities.add(e);
                } catch (NumberFormatException nfe) {
                }
            }
        }
        return entities.toArray();
    }

    private Object createEntity(char code, int x, int y) {
        return switch (code) {
            case 'p' -> new Player(x, y, keyH);
            case 'z' -> new Zombie(x, y);
            case 'c' -> new Coin(x, y);
            case 'e' -> new Exit(x, y);
            default -> null;
        };
    }

    private State toState(String data) {
        try {
            return State.valueOf(data.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
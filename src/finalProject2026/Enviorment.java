package finalProject2026;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Enviorment {
	
	private int x_position;
	private int y_position;
	private int xTileSize;
	private int yTileSize;
	private int id;

	protected boolean canBeWalkedOn;
	
	public Type type;
	private BufferedImage displayImage;
	
	public Enviorment(Type type, int x_position, int y_position, int tileSize, int id) {
		setDisplayImage(type);
		this.type = type;
		this.x_position = x_position;
		this.y_position = y_position;
		this.xTileSize = tileSize;
		this.yTileSize = tileSize;
		this.id = id;
			}
	
	private BufferedImage darken(BufferedImage img, int amount) {
		int r=0, g=0, b=0, rgb=0, p=0;
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int y=0; y<img.getHeight(); y++) {
			for(int x=0; x<img.getWidth(); x++) {
				rgb = img.getRGB(x, y);
				
				r=((rgb >> 16) & 0xFF) - amount < 0 ? 0 : ((rgb >> 16) & 0xFF) - amount;
				g=((rgb >> 8) & 0xFF) - amount < 0 ? 0 : ((rgb >> 8) & 0xFF) - amount;
				b=(rgb & 0xFF) - amount < 0 ? 0 : (rgb & 0xFF) - amount;
				
				p = (255 << 24) | (r << 16) | (g << 8) | b;
				newImage.setRGB(x, y, p);
			}
		}
		
		return newImage;
	}
	
	private BufferedImage tiled(BufferedImage img, int left, int top, int width, int height) {
		int r=0, g=0, b=0, rgb=0, p=0;
		BufferedImage newImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for(int y=top; y<height; y++) {
			for(int x=left; x<width; x++) {
				rgb = img.getRGB(x, y);
				
				r=((rgb >> 16) & 0xFF);
				g=((rgb >> 8) & 0xFF);
				b=(rgb & 0xFF);
				
				p = (255 << 24) | (r << 16) | (g << 8) | b;
				newImage.setRGB(x, y, p);
			}
		}
		return newImage;
	}
	
	private void setDisplayImage(Type type) {
		try {
			
			final BufferedImage GRASS = ImageIO.read(getClass().getResource("grass.png"));
			final BufferedImage STONE = ImageIO.read(getClass().getResource("stone.png"));
			final BufferedImage MAGMA = ImageIO.read(getClass().getResource("magma.png"));
			final BufferedImage SANDSTONE = ImageIO.read(getClass().getResource("sandstone.png"));
			
		switch (type) {
		case GRASS:
			displayImage = GRASS;
			break;
		case GRASSTILE:
			displayImage = tiled(GRASS, 0, 0, 16, 16);
			break;
		case GRASSGROUND:
			displayImage = darken(GRASS, 64);
			break;
		case STONE:
			displayImage = STONE;
			break;
		case STONETILE:
			displayImage = tiled(STONE, 0, 0, 16, 16);
			break;
		case STONEGROUND:
			displayImage = darken(STONE, 24);
			break;
		case SANDSTONE:
			displayImage = SANDSTONE;
			break;
		case SANDSTONETILE:
			displayImage = tiled(SANDSTONE, 0, 0, 16, 16);
			break;
		case SANDSTONEGROUND:
			displayImage = darken(SANDSTONE, 24);
			break;
		case MAGMA:
			displayImage = MAGMA;
			break;
		case MAGMATILE:
			displayImage = tiled(MAGMA, 0, 0, 16, 16);
			break;
		case MAGMAGROUND:
			displayImage = darken(MAGMA, 48);
			break;
		}
		} catch(Exception e) {}
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(displayImage, x_position, y_position, xTileSize, (int)(yTileSize*1.5), null);
	}
	
	public boolean getCanBeWalkedOn() {
		return canBeWalkedOn;
	}
	
	public int getId() {
		return id;
	}
}

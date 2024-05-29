package zhrfrd.terranova.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path;
	protected final int SIZE;
	public int[] pixels;
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	
	/**
	 * The SpriteSheet class represent the spritesheet that contains the textures of the game.
	 * @param path The current path where the spritesheet file is contained.
	 * @param size The size of the spritesheet file in pixels.
	 */
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		
		load();
	}
	
	/**
	 * Load the SpriteSheet from the image path and assign each pixel of the spritesheet to the pixels array.
	 */
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

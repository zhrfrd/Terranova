package zhrfrd.terranova.graphics;

import java.util.Random;

public class Screen {
	public final int TILE_SIZE_MASK = 4;   // 16 pixels  
	public final int MAP_SIZE = 8;   // 8 tiles x 8 tiles.
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;   // Used for bitwise operation
	public int width;
	public int height;
	public int lastOffsetX = 0;
	public int lastOffsetY = 0;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	private Random random = new Random();
	
	/**
	 * Create a new Screen object responsible for the rendering of the graphics.
	 * @param width The width of the screen in pixels.
	 * @param height The height of the screen in pixels.
	 */
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i ++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	/**
	 * Iterate through each pixel of the screen and render it with it color value.
	 * @param offsetX Difference between the original x position and the new current x position originated by the input.
	 * @param offsetY Difference between the original y position and the new current y position originated by the input.
	 */
	public void render(int offsetX, int offsetY) {
		lastOffsetX = offsetX;
		lastOffsetY = offsetY;
		
		for (int y = 0; y < height; y ++) {
			int yPixel = y + offsetY;
			
			if (yPixel < 0 || yPixel >= height) {
				continue;
			}
			
			for (int x = 0; x < width; x ++) {
				int xPixel = x + offsetX;
				
				if (xPixel < 0 || xPixel >= width) {
					continue;
				}
				
				pixels[xPixel + (yPixel * width)] = Sprite.grass.pixels[(x & 15) + ((y & 15) * Sprite.grass.SIZE)];
			}
		}
	}
	
	/**
	 * Remove all the graphics from the screen (pixel by pixel) at each game cycle;
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i ++) {
			pixels[i] = 0;
		}
	}
}


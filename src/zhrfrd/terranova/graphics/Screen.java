package zhrfrd.terranova.graphics;

import java.util.Random;

public class Screen {
	public final int TILE_SIZE = 16;
	public final int MAP_SIZE = 64 * 64;   // 64 tiles x 64 tiles.
	private int width;
	private int height;
	public int[] pixels;
	public int[] tiles = new int[MAP_SIZE];
	private Random random = new Random();
	
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		for (int i = 0; i < MAP_SIZE; i ++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	/**
	 * Render the graphics on the screen pixel by pixel at each game cycle.
	 */
	public void render() {
		for (int y = 0; y < height; y ++) {
			if (y < 0 || y >= height) {
				break;
			}
			
			for (int x = 0; x < width; x ++) {
				if (x < 0 || x >= width) {
					break;
				}
				
				int tileIndex = (x / TILE_SIZE) + (y / TILE_SIZE) * 64;
				
				pixels[x + (y * width)] = tiles[tileIndex];
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


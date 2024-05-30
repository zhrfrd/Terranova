package zhrfrd.terranova.graphics;

import java.util.Random;

import zhrfrd.terranova.level.tile.Tile;

public class Screen {
	public final int TILE_SIZE_MASK = 4;   // 16 pixels  
	public final int MAP_SIZE = 8;   // 8 tiles x 8 tiles.
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;   // Used for bitwise operation
	public int width;
	public int height;
	public int xOffset;
	public int yOffset;
	public int xTotalOffset;
	public int yTotalOffset;
	public int lastXoffset = 0;
	public int lastYoffset = 0;
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
	
//	/**
//	 * Iterate through each pixel of the screen and render it with its color value.
//	 * @param offsetX Difference between the original x position and the new current x position originated by the input.
//	 * @param offsetY Difference between the original y position and the new current y position originated by the input.
//	 */
//	public void render(int xOffset, int yOffset) {
//		lastXoffset = xOffset;
//		lastYoffset = yOffset;
//		
//		for (int y = 0; y < height; y ++) {
//			int pixelY = y + yOffset;
//			
//			// Prevent IndexOutOfBound exception when moving the map.
//			if (pixelY < 0 || pixelY >= height) {
//				continue;
//			}
//			
//			for (int x = 0; x < width; x ++) {
//				int pixelX = x + xOffset;
//			
//				// Prevent IndexOutOfBound exception when moving the map.
//				if (pixelX < 0 || pixelX >= width) {
//					continue;
//				}
//				
//				pixels[pixelX + (pixelY * width)] = Sprite.grass.pixels[(x & 15) + ((y & 15) * Sprite.grass.SIZE)];
//			}
//		}
//	}
	
	// Absolute position: position relative to the entire world.
	// Relative position: position relative to an object.
	public void renderTile(int xp, int yp, Tile tile) {
		lastXoffset = xTotalOffset;
		lastYoffset = yTotalOffset;
		xp -= xOffset;
		yp -= yOffset;
		
		for (int y = 0; y < tile.sprite.SIZE; y ++) {
			int yAbsoulte = y + yp;
			
			for (int x = 0; x < tile.sprite.SIZE; x ++) {
				int xAbsolute = x + xp;
				
				if (xAbsolute < -tile.sprite.SIZE || xAbsolute >= width || yAbsoulte < 0 || yAbsoulte >= height) {
					break;
				}
				
				if (xAbsolute < 0) {
					xAbsolute = 0;
				}
				
				pixels[xAbsolute + (yAbsoulte * width)] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	/**
	 * Remove all the graphics from the screen (pixel by pixel) at the end of each game cycle.
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i ++) {
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void setTotalOffset(int xTotalOffset, int yTotalOffset) {
		this.xTotalOffset = xTotalOffset;
		this.yTotalOffset = yTotalOffset;
	}
}


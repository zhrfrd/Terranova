package zhrfrd.terranova.graphics;

import zhrfrd.terranova.world.tile.Tile;

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
	
	/**
	 * Create a new Screen object responsible for the rendering of the graphics.
	 * @param width The width of the screen in pixels.
	 * @param height The height of the screen in pixels.
	 */
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	// Absolute position: position relative to the entire world.
	// Relative position: position relative to an object.
	/**
	 * Iterate through each pixel of the screen and render it with the relative sprite.
	 * @param xPosition
	 * @param yPosition
	 * @param tile The current tile to be rendered.
	 */
	public void renderTile(int xPosition, int yPosition, Tile tile) {
		lastXoffset = xTotalOffset;
		lastYoffset = yTotalOffset;
		xPosition -= xOffset;
		yPosition -= yOffset;
		
		for (int y = 0; y < tile.sprite.SIZE; y ++) {
			int yAbsoulte = y + yPosition;
			
			for (int x = 0; x < tile.sprite.SIZE; x ++) {
				int xAbsolute = x + xPosition;
				
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
	
	/**
	 * Set the current offset values from the last completed movement. 
	 * @param xOffset The x offset.
	 * @param yOffset The y offset.
	 */
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	/**
	 * Set the total offset value from the first completed movement.
	 * @param xTotalOffset The total x offset.
	 * @param yTotalOffset The total y offset.
	 */
	public void setTotalOffset(int xTotalOffset, int yTotalOffset) {
		this.xTotalOffset = xTotalOffset;
		this.yTotalOffset = yTotalOffset;
	}
}


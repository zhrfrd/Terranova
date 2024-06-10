package zhrfrd.terranova.level;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.level.tile.Tile;

public class Level {
	protected int width;
	protected int height;
	protected int[] tiles;
	
	/**
	 * Create a new level by simply specifying the width and height desired.
	 * @param width The width of the level in tiles.
	 * @param height The height of the level in tiles.
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		
		generateLevel();
	}
	
	/**
	 * Create a new level by passing the path of the level you want to load.
	 * @param path The path where the level lives.
	 */
	public Level(String path) {
		loadLevel(path);
	}
	
	/**
	 * Proceed with the current level generation
	 */
	protected void generateLevel() {
	}
	
	/**
	 * Load the level from a specific path.
	 * @param path The path where the level lives
	 */
	private void loadLevel(String path) {
	}
	
	
	public void update() {
	}
	

	public int xTotalOffset = 0;
	public int yTotalOffset = 0;
	
	/**
	 * Render ONLY the graphics visible within the corner pins of the screen.
	 * @param xOffset The x offset caused by the mouse drag.
	 * @param yOffset The y offset caused by the mouse drag.
	 * @param screen Screen object that is going to render the current level.
	 * @param xTotalOffset The total x offset from the first movement of the map.
	 * @param yTotalOffset The total y offset from the first movement of the map.
	 */
	public void render(int xOffset, int yOffset, int xTotalOffset, int yTotalOffset, Screen screen) {
		screen.setOffset(xOffset, yOffset);
		screen.setTotalOffset(xTotalOffset, yTotalOffset);
		
		this.xTotalOffset = xTotalOffset;
		this.yTotalOffset = yTotalOffset;
		
		// Corner pins: define which are of the map needs to be rendered.
		int x0 = (xOffset >> 4);
		int x1 = (xOffset + screen.width + 16) >> 4;
		int y0 = (yOffset >> 4);
		int y1 = (yOffset + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y ++) {
			for (int x = x0; x < x1; x ++) {
				getTile(x, y).render(x, y, screen);
			}
		}
	}
	
	/**
	 * Get the tile
	 * @param x The x position of the tile in the map (tile precision, not pixel precision).
	 * @param y The y position of the tile in the map (tile precision, not pixel precision).
	 * @return The tile.
	 */
	public Tile getTile(int x, int y) {
		// Handle IndexOutOfBound exception by rendering a void tile
		if (x < 0 || y < 0 || x > width || y > height) {
			return Tile.voidTile;
		}
		
		if (tiles[x + (y * width)] == 0) {
			return Tile.grass;
		}
		
		return Tile.voidTile;
	}
}

package zhrfrd.terranova.world;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.world.tile.Tile;

public class World {
	protected int width;
	protected int height;
	protected int[] tiles;
	
	/**
	 * Create a new world by simply specifying the width and height desired.
	 * @param width The width of the world in tiles.
	 * @param height The height of the world in tiles.
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		
		generateWorld();
	}
	
	/**
	 * Create a new world by passing the path of the world you want to load.
	 * @param path The path where the world lives.
	 */
	public World(String path) {
		loadWorld(path);
	}
	
	/**
	 * Proceed with the current world generation
	 */
	protected void generateWorld() {
	}
	
	/**
	 * Load the world from a specific path.
	 * @param path The path where the world lives
	 */
	private void loadWorld(String path) {
	}
	
	
	public void update() {
	}
	

	public int xTotalOffset = 0;
	public int yTotalOffset = 0;
	
	/**
	 * Render ONLY the graphics visible within the corner pins of the screen.
	 * @param xOffset The x offset caused by the mouse drag.
	 * @param yOffset The y offset caused by the mouse drag.
	 * @param xTotalOffset The total x offset from the first movement of the map.
	 * @param yTotalOffset The total y offset from the first movement of the map.
	 * @param screen Screen object that is going to render the current level.
	 */
	public void render(int xOffset, int yOffset, int xTotalOffset, int yTotalOffset, Screen screen) {
		screen.setOffset(xOffset, yOffset);
		screen.setTotalOffset(xTotalOffset, yTotalOffset);
		
		this.xTotalOffset = xTotalOffset;
		this.yTotalOffset = yTotalOffset;
		
		// Corner pins: define which area of the map needs to be rendered.
		int x0 = (xOffset >> 4);
		int x1 = (xOffset + screen.width + 16) >> 4;
		int y0 = (yOffset >> 4);
		int y1 = (yOffset + screen.height + 16) >> 4;
		
		for (int y = y0; y < y1; y ++) {
			for (int x = x0; x < x1; x ++) {
				Tile currentTile = getTile(x, y);
				
				currentTile.render(x, y, screen);
			}
		}
	}
	
	/**
	 * Get the tile.
	 * @param x The x position of the tile in the map (tile precision, not pixel precision).
	 * @param y The y position of the tile in the map (tile precision, not pixel precision).
	 * @return The tile.
	 */
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x > width || y > height) {
			return Tile.voidTile;
		}
		
		// Handle IndexOutOfBounds exception
		if (x + (y * width) >= width * height) {
			return Tile.voidTile;
		}
		
		switch (tiles[x + (y * width)]) {
			case 0:
				return Tile.grassland;
			case 1:
				return Tile.mountain;
			case 2:
				return Tile.lake;
		}
		
		return Tile.voidTile;
	}
}
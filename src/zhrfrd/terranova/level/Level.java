package zhrfrd.terranova.level;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.level.tile.Tile;

public class Level {
	protected int width;
	protected int height;
	protected int[] tiles;
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
	}
	
	protected void generateLevel() {
	}
	
	private void loadLevel(String path) {
	}
	
	public void update() {
	}
	
	/**
	 * Render ONLY the graphics visible within the corner pins of the screen.
	 * @param xOffset The x offset of cause by the mouse drag.
	 * @param yOffset The y offset of cause by the mouse drag.
	 * @param screen Screen object that is going to render the current level.
	 */
	
	public int xTotalOffset = 0;
	public int yTotalOffset = 0;
	
	public void render(int xOffset, int yOffset, Screen screen, int xTotalOffset, int yTotalOffset) {
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

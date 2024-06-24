package zhrfrd.terranova.world;

import java.util.Random;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.world.tile.Tile;

public class RandomWorld extends World {
	private static final Random RANDOM = new Random();

	public RandomWorld(int width, int height) {
	}

	protected void generateWorld() {
		for (int y = 0; y < height; y ++) {
			for (int x = 0 ; x < width; x ++) {
				tiles[x + (y * width)] = RANDOM.nextInt(4);
			}
		}
	}

	@Override
	public void render(int xOffset, int yOffset, int xTotalOffset, int yTotalOffset, Screen screen) {
	}

	@Override
	public Tile getTile(int x, int y) {
		return null;
	}

	@Override
	public Tile getSelectedTile() {
		return null;
	}

	@Override
	public void setSelectedTile(Tile selectedTile) {
	}

	@Override
	public void update() {
	}
}

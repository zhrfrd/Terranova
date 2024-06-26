package zhrfrd.terranova.world.tile;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.graphics.Sprite;

public class LakeTile extends Tile {
	public static final String NAME = "lake";
	
	/**
	 * A tile representing lake.
	 * @param sprite The sprite assigned to the tile.
	 */
	public LakeTile(Sprite sprite) {
		super(sprite, NAME);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}

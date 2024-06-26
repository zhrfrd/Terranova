package zhrfrd.terranova.world.tile;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.graphics.Sprite;

public class VoidTile extends Tile {
	public static final String NAME = "void";

	/**
	 * A tile representing void.
	 * @param sprite The sprite assigned to the tile.
	 */
	public VoidTile(Sprite sprite) {
		super(sprite, NAME);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}

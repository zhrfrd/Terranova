package zhrfrd.terranova.level.tile;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.graphics.Sprite;

public class GrassTile extends Tile {

	/**
	 * A tile representing grass.
	 * @param sprite The sprite assigned to the tile.
	 */
	public GrassTile(Sprite sprite) {
		super(sprite);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}

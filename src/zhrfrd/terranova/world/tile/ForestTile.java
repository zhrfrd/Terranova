package zhrfrd.terranova.world.tile;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.graphics.Sprite;

public class ForestTile extends Tile {
	public final static String NAME = "forest";
	private final int BONUS_DEFENCE = 5;
	private final int BONUS_ATTACK = 5;
	
	/**
	 * A tile representing forest.
	 * @param sprite The sprite assigned to the tile.
	 */
	public ForestTile(Sprite sprite) {
		super(sprite, NAME);
	}

	@Override
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	@Override
	public int getBonusDefence() {
		 return BONUS_DEFENCE;
	}
	
	@Override
	public int getBonusAttack() {
		 return BONUS_ATTACK;
	}
}

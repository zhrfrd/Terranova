package zhrfrd.terranova.world.tile;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.graphics.Sprite;

public abstract class Tile {
	public int x;
	public int y;
	public Sprite sprite;
	public String name;
	public static Tile grassland = new GrasslandTile(Sprite.grassland);
	public static Tile mountain = new MountainTile(Sprite.mountain);
	public static Tile lake = new LakeTile(Sprite.lake);
	public static Tile forest = new ForestTile(Sprite.forest);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public final int bonusDefence = 0;
	
	
	/**
	 * A blueprint of a tile object.
	 * @param sprite The sprite assigned to the tile.
	 */
	public Tile(Sprite sprite, String name) {
		this.sprite = sprite;
		this.name = name;
	}

	/**
	 * Call the {@link Screen#renderTile} method to proceed with the tile rendering.
	 * @param x The x position (not in pixels) where to render the tile. 
	 * 			The parameter will be then reconverted in pixels position.
	 * @param y The y position (not in pixels) where to render the tile. 
	 * 			The parameter will be then reconverted in pixels position.
	 * @param screen The screen responsible for the actual tile rendering process.
	 */
	public void render(int x, int y, Screen screen) {
	}
	
	/**
	 * Check if the current tile is solid. Non solid is the default value for all the tiles.
	 * @return {@code true}: if the tile is solid<br>
	 * 		   {@code false}: if the tile is not solid.
	 */
	public boolean isSolid() {
		return false;
	}
	
	public int getBonusDefence() {
		 return 0;
	}
	
	public int getBonusAttack() {
		 return 0;
	}
}

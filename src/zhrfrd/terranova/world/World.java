package zhrfrd.terranova.world;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.world.tile.Tile;

public abstract class World {
	protected int width;
	protected int height;
	protected int[] tiles;
	
	protected abstract void generateWorld();
	
	public abstract void update();
	
	public abstract void render(int xOffset, int yOffset, int xTotalOffset, int yTotalOffset, Screen screen);
	
	public abstract Tile getTile(int x, int y);
	
	public abstract Tile getSelectedTile();
    
    public abstract void setSelectedTile(Tile selectedTile) ;
}
package zhrfrd.terranova.world;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.util.PerlinNoise;
import zhrfrd.terranova.world.tile.Tile;

public class PerlinWorld extends World {
    protected int width;
    protected int height;
    protected Tile[] tiles;
    private Tile selectedTile;

    /**
	 * Create a new world by simply specifying the width and height desired.
	 * @param width The width of the world in tiles.
	 * @param height The height of the world in tiles.
	 */
    public PerlinWorld(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width * height];

        generateWorld();
    }

    @Override
    /**
	 * Proceed with the current level generation
	 */
    protected void generateWorld() {
        PerlinNoise perlin = new PerlinNoise(System.currentTimeMillis());
        double scale = 0.1; // Adjust scale for different terrain features

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                double value = perlin.noise(x * scale, y * scale);
                
                if (value < 0.3) {
                    tiles[x + y * width] = Tile.lake;
                } else if (value < 0.5) {
                    tiles[x + y * width] = Tile.mountain;
                } else if (value < 0.7) {
                    tiles[x + y * width] = Tile.grassland;
                } else {
                    tiles[x + y * width] = Tile.forest;
                }
            }
        }
    }
    
    @Override
    public void update() {
    }

    public int xTotalOffset = 0;
    public int yTotalOffset = 0;

    @Override
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

    @Override
    /**
	 * Extract the actual tile to be rendered based on the current {@link tiles} index.
	 * @param x The x position of the tile in the map (tile precision, not pixel precision).
	 * @param y The y position of the tile in the map (tile precision, not pixel precision).
	 * @return The tile.
	 */
    public Tile getTile(int x, int y) {
    	// Handle IndexOutOfBounds exception
    	if (x < 0 || y < 0 || x >= width || y >= height || x + (y * width) >= width * height) {
            return Tile.voidTile;
        }
 		
 		if (tiles[x + y * width] == Tile.lake) {
 			return Tile.lake;
    	} if (tiles[x + y * width] == Tile.mountain) {
 			return Tile.mountain;
 		} if (tiles[x + y * width] == Tile.grassland) {
 			return Tile.grassland;
 		} if (tiles[x + y * width] == Tile.forest) {
 			return Tile.forest;
 		}

        return Tile.voidTile;
    }
    
    @Override
    public Tile getSelectedTile() {
    	return selectedTile;
    }
    
    @Override
    public void setSelectedTile(Tile selectedTile) {
    	this.selectedTile = selectedTile;
    }
}

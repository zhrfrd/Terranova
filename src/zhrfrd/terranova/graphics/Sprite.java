package zhrfrd.terranova.graphics;

public class Sprite {
	protected final int SIZE;
	private int x;   // The "index" (position) in PIXELS of the sprite in the spritesheet.
	private int y;   //
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	
	/**
	 * The Sprite class represent the sprite of a particular graphic element of the game.
	 * @param size The sprite size in pixels.
	 * @param x The x index of the sprite in the spritesheet.
	 * @param y The y index of the sprite in the spritesheet.
	 * @param sheet The spritesheet from where to extract the sprite.
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		
		load();
	}
	
	/**
	 * Extract every single sprite out of the spritesheet by assigning each pixel of the sprite 
	 * to the corresponding pixel in the spritesheet.
	 */
	private void load() {
		for (int y = 0; y < SIZE; y ++) {
			for (int x = 0; x < SIZE; x ++) {
				pixels[x + (y * SIZE)] = sheet.pixels[(x + this.x) + ((y + this.y) * sheet.SIZE)];
			}
		}
	}
}

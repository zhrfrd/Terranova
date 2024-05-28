package zhrfrd.terranova.graphics;

public class Screen {
	private int width;
	private int height;
	public int[] pixels;
	
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	/**
	 * Render the graphics on the screen pixel by pixel at each game cycle.
	 */
	
	int counter = 0;
	int time = 0;
	
	public void render() {
		counter ++;
		
		if (counter % 100 == 0) {
			time ++;
		}
		for (int y = 0; y < height; y ++) {
			for (int x = 0; x < width; x ++) {
				pixels[time + (time * width)] = 0xff00ff;
			}
		}
	}
	
	/**
	 * Remove all the graphics from the screen (pixel by pixel) at each game cycle;
	 */
	public void clear() {
		for (int i = 0; i < pixels.length; i ++) {
			pixels[i] = 0;
		}
	}
}

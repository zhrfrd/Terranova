package zhrfrd.terranova.world;

import java.util.Random;

public class RandomWorld extends World {
	private static final Random RANDOM = new Random();

	public RandomWorld(int width, int height) {
		super(width, height);
	}
	
	@Override
	protected void generateWorld() {
		for (int y = 0; y < height; y ++) {
			for (int x = 0 ; x < width; x ++) {
				tiles[x + (y * width)] = RANDOM.nextInt(4);
			}
		}
	}
}

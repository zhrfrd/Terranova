package zhrfrd.terranova.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import zhrfrd.terranova.graphics.Screen;
import zhrfrd.terranova.input.Keyboard;
import zhrfrd.terranova.input.Mouse;
import zhrfrd.terranova.util.Tool;
import zhrfrd.terranova.world.PerlinWorld;
import zhrfrd.terranova.world.tile.Tile;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Terranova";
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 16 * 9;   // 16 : 9 resolution.
	public static final int SCALE = 3;
	private static final int LEVEL_SIZE = 100;
	private Thread thread;   // Main thread of the game.
	private JFrame frame;   // Main frame of the game window.
	private	 Keyboard key;
	private Mouse mouse;
	private PerlinWorld world;
	private Tool util;
	private boolean running = false;
	private Screen screen;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();   // Convert the BufferedImage to an array of pixels (Ruster).
	int ups = 0;
	int fps = 0;
	
	/**
	 * Create a new Game object which contains the main thread, frame, screen and game loop.
	 */
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		world = new PerlinWorld(LEVEL_SIZE, LEVEL_SIZE); //new World(LEVEL_SIZE, LEVEL_SIZE);// new RandomWorld(LEVEL_SIZE, LEVEL_SIZE);
		key = new Keyboard();
		addKeyListener(key);
		
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		util = new Tool(frame);
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double NANO_SEC = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;   // This should always be 60 ps.
		
		requestFocus();
		
		// Game loop.
		while (running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / NANO_SEC;
			lastTime = now;
			
			// Update 60 times per second.
			while (delta >= 1) {
				update();
				
				updates ++;
				delta --;
			}
			
			render();
			frames ++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				ups = updates;
				fps = frames;
				updates = 0;
				frames = 0;
			}
		}
		
		stopThreadGame();
	}
	
	/**
	 * Set the default layout of the game JFrame.
	 */
	protected void setFrameGameLayout() {
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Entry point to run the main thread of the game.
	 */
	public synchronized void startThreadGame() {
		running = true;
		thread = new Thread(this, "Display");
		
		thread.start();
	}
	
	/**
	 * Stop the main thread of the game.
	 */
	public synchronized void stopThreadGame() {
		running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Update the current game status and inputs.
	 */
	public void update() {
		updateMouseEvents();
		updateKeyboardEvents();
	}
	
	/**
	 * Call the {@link Screen#render} method (responsible to handle the rendering of each pixels on the screen) 
	 * and draw the actual result. This method is called at each game loop iteration. 
	 */
	public void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		
		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		world.render(-xTotalOffset, -yTotalOffset, xTotalOffset, yTotalOffset, screen);
		
		for (int i = 0; i < pixels.length; i ++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		// MOUSE TEST
		g.setColor(Color.WHITE);
		g.drawString("Button :" + Mouse.getButton() + ", X:" + Mouse.getX() / 3 + ", Y:" + Mouse.getY() / 3, 16, 16);
		// ----------
		
		g.dispose();
		bufferStrategy.show();
	}
	
	private int xTotalOffset = 0;
	private int yTotalOffset = 0;
	private int xInitialScaled = 0;
	private int yInitialScaled = 0;
	private int xLastOffset = 0;
	private int yLastOffset = 0;
	
	/**
	 * Get the current mouse event and update the game status based on the outcome.
	 */
	private void updateMouseEvents() {
		xInitialScaled = Mouse.getX() / SCALE;
		yInitialScaled = Mouse.getY() / SCALE;
		
		
		if (!Mouse.isDragged) {
			xLastOffset = screen.lastXoffset;
			yLastOffset = screen.lastYoffset; 
		} else {
			int xDragScaled = Mouse.getXmouseDrag() / SCALE;
			int yDragScaled = Mouse.getYmouseDrag() / SCALE;
			
			// TODO: Improve map drag when you reach the edge. The drag should stop immediately
			// as soon as you reach the edges of the map
			xTotalOffset = xLastOffset + xDragScaled - xInitialScaled;
			yTotalOffset = yLastOffset + yDragScaled - yInitialScaled;
		}
		
		if (Mouse.isPressed) {
			Tile selectedTile = world.getTile((Mouse.getX() / SCALE - xTotalOffset) / 16, (Mouse.getY() / SCALE - yTotalOffset )/ 16);
			System.out.println(selectedTile.name);
			
			world.selectedTile = selectedTile;
			
			Mouse.isPressed = false;
		}
	}
	
	/**
	 * Get the current keyboard event and update the game status based on the outcome.
	 */
	private void updateKeyboardEvents() {
		key.update();
		
		if (key.performance) {
			util.showPerformanceOnTitle(TITLE, ups, fps);
		} else {
			util.resetOriginalTitle(TITLE);
		}
	}
}
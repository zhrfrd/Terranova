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
import zhrfrd.terranova.level.Level;
import zhrfrd.terranova.level.RandomLevel;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	public static final String TITLE = "Terranova";
	public static final int WIDTH = 300;
	public static final int HEIGHT = WIDTH / 16 * 9;   // 16 : 9 resolution.
	public static final int SCALE = 3;
	private Thread thread;   // Main thread of the game.
	private JFrame frame;   // Main frame of the game window.
	private	 Keyboard key;
	private Mouse mouse;
	private Level level;
	private Util util;
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
		level = new RandomLevel(64, 64);
		key = new Keyboard();
		addKeyListener(key);
		
		mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		util = new Util(frame);
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
//		screen.render(x, y);
		level.render(-xTotalOffset, -yTotalOffset, xTotalOffset, yTotalOffset, screen);
		
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
	private int xInitial = 0;
	private int yInitial = 0;
	private int xLastOffset = 0;
	private int yLastOffset = 0;
	
	/**
	 * Get the current mouse event and update the game status based on the outcome.
	 */
	private void updateMouseEvents() {
		if (Mouse.isDoubleClicked) {
			xTotalOffset += (screen.width / 2) - (Mouse.getXmouseClick() / SCALE);			
			yTotalOffset += (screen.height / 2) - (Mouse.getYmouseClick() / SCALE);
			
			Mouse.isDoubleClicked = false; 
		}
		
		if (!Mouse.isDragged) {
			xInitial = Mouse.getX() / SCALE;
			yInitial = Mouse.getY() / SCALE;
			xLastOffset = screen.lastXoffset;
			yLastOffset = screen.lastYoffset; 
		} else {
			int xDrag = Mouse.getXmouseDrag() / SCALE;
			int yDrag = Mouse.getYmouseDrag() / SCALE;
			
			xTotalOffset = xLastOffset + xDrag - xInitial;
			yTotalOffset = yLastOffset + yDrag - yInitial;
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
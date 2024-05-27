package zhrfrd.terranova;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;   // 16 : 9 resolution
	public static int SCALE = 3;
	
	private Thread threadGame;
	protected JFrame frame;
	private boolean running = false;
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		
		frame = new JFrame();
	}

	@Override
	public void run() {
		while (running) {
			System.out.println("threadGame running");
		}
	}

	/**
	 * Entry point to run the main thread of the game.
	 */
	public synchronized void startThreadGame() {
		running = true;
		
		threadGame = new Thread(this, "Display");
		threadGame.start();
	}
	
	/**
	 * Close the main thread of the game.
	 */
	public synchronized void stopThreadGame() {
		running = false;
		
		try {
			threadGame.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

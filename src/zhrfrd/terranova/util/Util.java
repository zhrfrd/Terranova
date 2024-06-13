package zhrfrd.terranova.util;

import javax.swing.JFrame;

public class Util {
	JFrame frame;
	
	/**
	 * This class contains some methods useful for debugging or to check performances.
	 * @param frame The JFrame where to show any util info.
	 */
	public Util(JFrame frame) {
		this.frame = frame;
	}
	
	/**
	 * Show the current game performances on the JFrame title. It updates every seconds when pressing the relative key.
	 * @param title The current game title (excluded any extra information).
	 * @param updates The number of updates per seconds.
	 * @param frames The number of frames per seconds.
	 */
	public void showPerformanceOnTitle(String title, int updates, int frames) {
		frame.setTitle(title + " | " + updates + " ups, " + frames + " fps");
	}
	
	/**
	 * Reset the JFrame title of the game to the original form (which consists simply on the name of the game). 
	 * @param title The original JFrame title.
	 */
	public void resetOriginalTitle(String title) {
		frame.setTitle(title);
	}
}

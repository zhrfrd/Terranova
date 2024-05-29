package zhrfrd.terranova.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseClickX = -1;
	private static int mouseClickY = -1;
	private static int mouseButton = -1;
	public boolean isClicked = false;
	private static final int SCALE = 3;

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClickX = e.getX() / SCALE;
		mouseClickY = e.getY() / SCALE;
		isClicked = true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	/**
	 * Get the current x position of the mouse on the screen
	 * @return The mouse x coordinate.
	 */
	public static int getX() {
		return mouseX;
	}
	
	/**
	 * Get the current y position of the mouse on the screen
	 * @return The mouse y coordinate.
	 */
	public static int getY() {
		return mouseY;
	}
	
	/**
	 * Get the x position on the game screen of the mouse when clicked.
	 * @return The x position of the mouse when clicked.
	 * Note that value of x is divided by the {@code SCALE} when the mouse is clicked and 
	 * the method {@link #mouseClicked} is called.
	 */
	public int getMouseClickX() {
		return mouseClickX;
	}
	
	/**
	 * Get the y position on teh game screen of the mouse when clicked.
	 * @return The y position of the mouse when clicked.
	 * Note that value of y is divided by the {@code SCALE} when the mouse is clicked and 
	 * the method {@link #mouseClicked} is called.
	 */
	public int getMouseClickY() {
		return mouseClickY;
	}
	
	/**
	 * Get which mouse button has been pressed.
	 * @return The code of the mouse button pressed.
	 */
	public static int getButton() {
		return mouseButton;
	}
}

package zhrfrd.terranova.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseClickX = -1;
	private static int mouseClickY = -1;
	private static int mouseDragX = -1;
	private static int mouseDragY = -1;
	private static int mouseButton = -1;
	public static boolean isClicked = false;
	public static boolean isDoubleClicked = false;
	public static boolean isDragged = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClickX = e.getX();
		mouseClickY = e.getY();
		isClicked = true;
		
		if (e.getClickCount() == 2 && !e.isConsumed()) {
		     e.consume();
		     
		     isDoubleClicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
		isDragged = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseDragX = e.getX();
		mouseDragY = e.getY();
		isDragged = true;
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
	 * Get the x position of the mouse when clicked on the game screen.
	 * @return The x position of the mouse when clicked.
	 * @Note The value of x represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getMouseClickX() {
		return mouseClickX;
	}
	
	/**
	 * Get the y position of the mouse when clicked on the game screen.
	 * @return The y position of the mouse when clicked.
	 * @Note The value of y represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getMouseClickY() {
		return mouseClickY;
	}
	
	/**
	 * Get the x position of the mouse while dragging on the game screen.
	 * @return The x position of the mouse while dragging.
	 * @Note The value of x represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getMouseDragX() {
		return mouseDragX;
	}
	
	/**
	 * Get the y position of the mouse while dragging on the game screen.
	 * @return The y position of the mouse while dragging.
	 * @Note The value of y represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getMouseDragY() {
		return mouseDragY;
	}
	
	/**
	 * Get which mouse button has been pressed.
	 * @return The code of the mouse button pressed.
	 */
	public static int getButton() {
		return mouseButton;
	}
}

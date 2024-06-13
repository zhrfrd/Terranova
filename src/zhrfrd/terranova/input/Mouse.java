package zhrfrd.terranova.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {
	private static int xMouse = -1;
	private static int yMouse = -1;
	private static int xMouseClick = -1;
	private static int yMouseClick = -1;
	private static int xMouseDrag = -1;
	private static int yMouseDrag = -1;
	private static int mouseButton = -1;
	public static boolean isClicked = false;
	public static boolean isPressed = false;
	public static boolean isDoubleClicked = false;
	public static boolean isDragged = false;

	@Override
	public void mouseClicked(MouseEvent e) {
		xMouseClick = e.getX();
		yMouseClick = e.getY();
		isClicked = true;
		
		if (e.getClickCount() == 2 && !e.isConsumed()) {
		     e.consume();
		     
		     isDoubleClicked = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseButton = e.getButton();
		isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseButton = -1;
		isDragged = false;
		isPressed = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		xMouseDrag = e.getX();
		yMouseDrag = e.getY();
		isDragged = true;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		xMouse = e.getX();
		yMouse = e.getY();
	}
	
	/**
	 * Get the current x position of the mouse on the screen
	 * @return The mouse x coordinate.
	 */
	public static int getX() {
		return xMouse;
	}
	
	/**
	 * Get the current y position of the mouse on the screen
	 * @return The mouse y coordinate.
	 */
	public static int getY() {
		return yMouse;
	}
	
	/**
	 * Get the x position of the mouse when clicked on the game screen.
	 * @return The x position of the mouse when clicked.
	 * @Note The value of x represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getXmouseClick() {
		return xMouseClick;
	}
	
	/**
	 * Get the y position of the mouse when clicked on the game screen.
	 * @return The y position of the mouse when clicked.
	 * @Note The value of y represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getYmouseClick() {
		return yMouseClick;
	}
	
	/**
	 * Get the x position of the mouse while dragging on the game screen.
	 * @return The x position of the mouse while dragging.
	 * @Note The value of x represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getXmouseDrag() {
		return xMouseDrag;
	}
	
	/**
	 * Get the y position of the mouse while dragging on the game screen.
	 * @return The y position of the mouse while dragging.
	 * @Note The value of y represents the real value in pixels and it will be later in
	 * the game divided by the {@link Game#SCALE}.
	 */
	public static int getYmouseDrag() {
		return yMouseDrag;
	}
	
	/**
	 * Get which mouse button has been pressed.
	 * @return The code of the mouse button pressed.
	 */
	public static int getButton() {
		return mouseButton;
	}
}

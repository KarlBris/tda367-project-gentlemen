package components;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controllers.IController;

/**
 * Provides mouse input functionality by reading the mouse state with each frame
 * 
 */
public class MouseComponent implements IComponent {

	// An array of booleans representing current state of the three normally
	// occuring mouse buttons
	private final boolean[] buttonDownArray = new boolean[3];

	// An array of booleans representing which of mouse buttons were down before
	// the last call of update()
	private boolean[] prevButtonDownArray = new boolean[3];

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the mouse and saves current mouse state
	 * 
	 * @see components.IComponent#update()
	 */
	@Override
	public void update() {

		Mouse.poll();

		// Copies the current mouse state to the previous
		prevButtonDownArray = buttonDownArray.clone();

		for (int i = 0; i < buttonDownArray.length; i++) {
			buttonDownArray[i] = Mouse.isButtonDown(i);
		}

	}

	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRemoved(final IController controller) {
		// TODO Auto-generated method stub

	}

	/**
	 * Gives the mouse pointer's position on the screen as a Vector2f
	 * 
	 * @return the Vector2f representing the mouse pointer's position
	 */
	public Vector2f getScreenPosition() {
		// Returns a Vector2f representing the mouse pointer's position on the
		// screen
		return new Vector2f(Mouse.getX(), Tools.getScreenHeight()
				- Mouse.getY());
	}

	/**
	 * Gives the mouse pointer's position in the viewport as a Vector2f
	 * 
	 * @return the Vector2f representing the mouse pointer's position
	 */
	public Vector2f getViewportPosition() {
		// Returns a Vector2f representing the mouse pointer's position in the
		// viewport
		return Tools.screenToViewport(Mouse.getX(), Tools.getScreenHeight()
				- Mouse.getY());

	}

	/**
	 * Checks whether a specific mouse button is pressed
	 * 
	 * @param button
	 *            the button which state is to be checked
	 * @return the state of the given button
	 */
	public boolean getButton(final int button) {
		// Returns the boolean value representing the state of the given button
		return buttonDownArray[button];
	}

	/**
	 * Checks whether a specific mouse button was pressed during the previous
	 * call of update()
	 * 
	 * @param button
	 *            the button which state is to be checked
	 * @return the state of the given button
	 */
	public boolean getButtonDown(final int button) {
		// If a button is currently pressed down and was not pressed during the
		// previous call of update(), return true
		if (buttonDownArray[button] && !prevButtonDownArray[button]) {
			return true;
		}
		// In all other cases, return false
		else {
			return false;
		}

	}

}

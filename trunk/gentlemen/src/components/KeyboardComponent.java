package components;

import org.lwjgl.input.Keyboard;

import controllers.IController;

/**
 * Provides functionality related to keyboard input by reading the state of the
 * keyboard each frame
 */
public class KeyboardComponent implements IComponent {

	// An array of booleans representing the current state of the keyboard
	private final boolean[] keyDownArray = new boolean[Keyboard.KEYBOARD_SIZE];

	// An array of booleans representing the state of the keyboard before
	// update() was called last time
	private boolean[] prevKeyDownArray = new boolean[Keyboard.KEYBOARD_SIZE];

	/**
	 * Returns true or false depending on whether the given key is pressed or
	 * not
	 * 
	 * @param key
	 *            an int representing a key on the keyboard as defined in
	 *            org.lwjgl.input.Keyboard
	 * @return the boolean representing if the button is pressed or not
	 */
	public boolean getKey(final int key) {

		// Returns the boolean value, from the current state of the keyboard,
		// associated with the given key
		return keyDownArray[key];

	}

	/**
	 * Returns true only the same frame the given key is pressed down. If the
	 * key is not released, this method will subsequently return false.
	 * 
	 * @param key
	 *            an int representing a key on the keyboard as defined in
	 *            org.lwjgl.input.Keyboard
	 * @return the boolean representing if the button is pressed this frame or
	 *         not
	 */
	public boolean getKeyDown(final int key) {

		// If the given key is down right now, but wasn't down last update, the
		// key just got pressed down, returning true
		if (keyDownArray[key] && !prevKeyDownArray[key]) {
			return true;
		}
		// In all other cases, return false
		else {
			return false;
		}
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the current and last keyboard states in order for the getKey()
	 * and getKeyDown() logic to function correctly
	 */
	@Override
	public void update() {

		Keyboard.poll();

		// Copy the current keyboard state to the previous
		prevKeyDownArray = keyDownArray.clone();

		// Loop through the keys of the keyboard and update the current keyboard
		// state accordingly
		for (int i = 0; i < keyDownArray.length; i++) {
			keyDownArray[i] = Keyboard.isKeyDown(i);
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

}

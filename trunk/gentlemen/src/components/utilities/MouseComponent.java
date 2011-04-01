package components.utilities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import core.Component;
import core.Constants;
import core.Entity;

public class MouseComponent implements Component {
	
	// An array of booleans representing current state of the three normally occuring mouse buttons
	public static boolean[] buttonDownArray = new boolean[3];


	// An array of booleans representing which of mouse buttons were down before the last call of update()
	public static boolean[] prevButtonDownArray = new boolean[3];
	
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

	@Override
	public void update() {
		
		//
		prevButtonDownArray = buttonDownArray.clone();
		
		for(int i = 0; i < buttonDownArray.length; i++){
			buttonDownArray[i] = Mouse.isButtonDown(i);
		}

	}

	@Override
	public void entityAdded(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void entityRemoved(Entity entity) {
		// TODO Auto-generated method stub

	}
	
	public Vector2f getScreenPosition() {
		// Returns a Vector2f representing the mouse pointer's position on the screen
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
	
	public Vector2f getViewportPosition() {
		// Returns a Vector2f representing the mouse pointer's position in the viewport
		return Constants.screenToViewport(Mouse.getX(), Mouse.getY());
		
	}
	
	public boolean getButton(int button) {
		// Returns the boolean value representing the state of the given button
		return buttonDownArray[button];
	}
	
	public boolean getButtonDown(int button) {
		// If a button is currently pressed down and was not pressed during the last call of update(), return true
		if(buttonDownArray[button] && !prevButtonDownArray[button]) {
			return true;
		}
		// In all other cases, return false
		else {
			return false;
		}
		
	}
	
	
}

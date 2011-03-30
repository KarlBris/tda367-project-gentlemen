package components.utilities;

import org.lwjgl.input.Keyboard;

import core.Component;
import core.Entity;

public class KeyboardComponent implements Component {

	// An array of booleans representing the current state of the keyboard
	public static boolean[] keyDownArray = new boolean[Keyboard.KEYBOARD_SIZE];
	
	// An array of booleans representing the state of the keyboard before update() was called last time
	public static boolean[] prevKeyDownArray = new boolean[Keyboard.KEYBOARD_SIZE];  
	
	
	public static boolean getKey(int key) {
		
		// Returns the boolean value, from the current state of the keyboard, associated with the given key		
		return keyDownArray[key];
		
	}
	
	public static boolean getKeyDown(int key) {		
			
		// If the given key is down right now, but wasn't down last update, the key just got pressed down, returning true
		if(keyDownArray[key] && !prevKeyDownArray[key]) {
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
	public void instantiatePermanentEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		
		// Copy the current keyboard state to the previous
		prevKeyDownArray = keyDownArray.clone();
		
		// Loop through the keys of the keyboard and update the current keyboard state accordingly 
		for(int i = 0; i < keyDownArray.length; i++){
			keyDownArray[i] = Keyboard.isKeyDown(i);
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

}

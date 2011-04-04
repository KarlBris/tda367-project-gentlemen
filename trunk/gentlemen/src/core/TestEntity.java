package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

import components.utilities.KeyboardComponent;

public class TestEntity extends Entity {
	
	public TestEntity() {
		super(new RectangleGeometry(new Color(Color.WHITE)));
	}
	
	@Override
	public void update() {
		KeyboardComponent keyboard = Manager.getKeyboard();
		
		Vector2f newPosition = getGeometry().getPosition();
		
		if (keyboard.getKey(Keyboard.KEY_SPACE)) {
			Vector2f.add(newPosition, new Vector2f(1.0f * Constants.DELTA_TIME, 2.0f * Constants.DELTA_TIME), newPosition);
		}
		
		getGeometry().setPosition(newPosition);
	}
}

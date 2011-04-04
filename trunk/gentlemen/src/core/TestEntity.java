package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

import components.utilities.KeyboardComponent;

public class TestEntity extends Entity {
	
	public TestEntity() {
		super(new RectangleGeometry(new Color(Color.WHITE), 2.0f, 1.0f));
	}
	
	@Override
	public void update() {
		KeyboardComponent keyboard = Manager.getKeyboard();
		
		if (keyboard.getKey(Keyboard.KEY_SPACE)) {
			getGeometry().moveTowards(Manager.getMouse().getViewportPosition(), Constants.PI * 0.9f);
		}
		else {
			getGeometry().moveTowards(Manager.getMouse().getViewportPosition(), 0.0f);
		}
	}
}

package components.utilities;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

import core.Component;

public class MouseComponent implements Component {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void entityAdded() {
		// TODO Auto-generated method stub

	}

	@Override
	public void entityRemoved() {
		// TODO Auto-generated method stub

	}
	
	public Vector2f getScreenPosition() {
		return new Vector2f(Mouse.getX(), Mouse.getY());
	}
}

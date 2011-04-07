package core;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class TestEntity extends Entity {
	
	private Vector2f velocity = new Vector2f();
	
	public TestEntity() {
		super(new RectangleGeometry(new Color(Color.WHITE), 1.0f, 0.2f));
	}
	
	public void setPosition(Vector2f position) {
		getGeometry().setPosition(position);
	}
	
	public void setAngle(float angle) {
		velocity.x = (float)Math.cos(angle) * 20.0f;
		velocity.y = -(float)Math.sin(angle) * 20.0f;
		
		getGeometry().setAngle(angle);
	}
	
	@Override
	public void update() {
		Vector2f newPosition = new Vector2f();
		
		Vector2f movement = new Vector2f(velocity);
		movement.scale(Constants.DELTA_TIME);
		
		Vector2f.add(getGeometry().getPosition(), movement, newPosition);
		
		getGeometry().setPosition(newPosition);
		
		// Remove the entity when it is far away
		if (newPosition.length() > 50.0f) {
			Manager.remove(this);
		}
	}
}

package core;

import org.lwjgl.util.vector.Vector2f;

/**
 * PlayerModel represents a player in the game world
 */
public class PlayerModel implements IModel {

	private final RectangleGeometry geometry = new RectangleGeometry(null,
			1.0f, 1.0f);
	private Vector2f targetPosition = geometry.getPosition();
	private float angle = 0.0f;

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * Returns the position of the current PlayerModel as a Vector2f
	 * 
	 * @return the position of the PlayerModel
	 */
	public Vector2f getPosition() {
		return geometry.getPosition();
	}

	public void move(final Vector2f targetPosition) {
		this.targetPosition = targetPosition;
	}

	public void faceTowards(final float angle) {
		this.angle = angle;

	}

	public void update() {
		geometry.moveTowards(targetPosition, angle);
	}
}

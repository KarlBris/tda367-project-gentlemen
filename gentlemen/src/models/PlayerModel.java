package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Constants;
import core.Geometry;
import core.RectangleGeometry;

/**
 * PlayerModel represents a player in the game world
 */
public class PlayerModel implements IModel {

	private final RectangleGeometry geometry = new RectangleGeometry(
			Color.WHITE, 1.0f, 1.0f);
	private final Vector2f targetPosition = geometry.getPosition();
	private float targetAngle = 0.0f;

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

	/**
	 * Sets the current PlayerModel's target position to the parameter
	 * targetPosition
	 * 
	 * @param targetPosition
	 */
	public void move(final Vector2f velocity) {
		final Vector2f movement = new Vector2f(velocity);

		movement.scale(Constants.DELTA_TIME);

		Vector2f.add(targetPosition, movement, targetPosition);
	}

	/**
	 * Sets the current PlayerModel's target angle to the parameter angle
	 * 
	 * @param angle
	 */
	public void faceTowards(final float angle) {
		this.targetAngle = angle;

	}

	/**
	 * Updates the current PlayerModel
	 * 
	 */
	public void update() {
		geometry.moveTowards(targetPosition, targetAngle);
	}

	/**
	 * Throws a ball
	 * 
	 * @return success of the throw operation
	 */
	public boolean throwBall() {

		return true;
	}
}

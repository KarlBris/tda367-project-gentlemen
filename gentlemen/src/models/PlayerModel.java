package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Body;
import core.BoxBodyShape;
import core.Geometry;
import core.RectangleGeometry;

/**
 * PlayerModel represents a player in the game world
 */
public class PlayerModel implements IModel {

	private final Geometry geometry = new RectangleGeometry(Color.WHITE, 1.0f,
			1.0f);

	private final Body body = new Body(new BoxBodyShape(1.0f, 1.0f), 10.0f);

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
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
	 * Moves the player in any direction
	 * 
	 * @param movement
	 *            the direction to move in
	 */
	public void move(final Vector2f movement) {
		Vector2f force = new Vector2f(movement);

		force.scale(5.0f);

		force.scale(body.getMass());

		body.applyForce(force);
	}

	/**
	 * Tells the player to face towards a specific angle
	 * 
	 * @param angle
	 *            the angle to face towards
	 */
	public void faceTowards(final float angle) {
		body.setAngle(angle);
	}

	/**
	 * Updates the current PlayerModel
	 * 
	 */
	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
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

package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import core.Body;
import core.CircleBodyShape;
import core.CircleGeometry;
import core.Geometry;

public class BallModel implements IModel {

	private final Geometry geometry = new CircleGeometry(
			utilities.Color.randomColor(), 1.0f, 0.2f, 8);

	private final Body body = new Body(new CircleBodyShape(0.2f), 1.0f, 1.0f);

	private boolean isPickedUp = false;

	/**
	 * @param referenceVelocity
	 *            the velocity of an object that should be considered static
	 * @return true if the speed of the ball is lethal, otherwise false
	 */
	public boolean isLethal(final Vector2f referenceVelocity) {
		Vector2f relativeVelocity = new Vector2f(0.0f, 0.0f);

		Vector2f.sub(body.getVelocity(), referenceVelocity, relativeVelocity);

		return relativeVelocity.length() >= Constants.BALL_LETHAL_SPEED;
	}

	/**
	 * @param referenceVelocity
	 *            the velocity of an object that should be considered static
	 * @return true if the ball can be picked up, otherwise false
	 */
	public boolean isPickUpAble(final Vector2f referenceVelocity) {
		return !isPickedUp && !isLethal(referenceVelocity);
	}

	/**
	 * @return the position of the ball
	 */
	public Vector2f getPosition() {
		return body.getPosition();
	}

	/**
	 * Makes the ball unable to be picked up by other players
	 */
	public void pickUp() {
		isPickedUp = true;
	}

	/**
	 * Throw the ball in the force direction and speed
	 * 
	 * @param force
	 *            contains the speed an angle of the ball
	 */
	public void throwBall(final Vector2f velocity) {
		body.applyVelocityChange(velocity);

		releaseBall();
	}

	/**
	 * Makes the ball able to be picked up by other players
	 */
	public void releaseBall() {
		isPickedUp = false;
	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
	}

	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
	}

	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);

		body.setPosition(position);
		body.clearVelocity();
		body.clearAngularVelocity();
	}

}
package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;

import core.BallGeometry;
import core.Body;
import core.CircleBodyShape;
import core.Geometry;

public class BallModel implements IModel {

	private final Geometry geometry = new BallGeometry(
			utilities.Color.randomColor(), 0.2f, 8);

	private final Body body = new Body(new CircleBodyShape(0.2f), 1.0f);

	private boolean isPickedUp = false;

	/**
	 * @return return true if the speed of the ball is lethal, otherwise false
	 */
	public boolean isLethal() {
		final Vector2f v = body.getVelocity();
		return v.length() >= Constants.LETHAL_BALL_SPEED;
	}

	/**
	 * @return true if the ball can be picked up, otherwise false
	 */
	public boolean isPickUpAble() {
		return !isPickedUp && !isLethal();
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
	public void throwBall(final Vector2f force) {
		body.applyForce(force);
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
		body.setPosition(position);
		geometry.setPosition(position);

	}

}
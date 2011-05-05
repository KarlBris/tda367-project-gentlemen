package models;

import org.lwjgl.util.vector.Vector2f;

import core.BallGeometry;
import core.Body;
import core.CircleBodyShape;
import core.Geometry;

public class BallModel implements IModel {

	private final Geometry geometry = new BallGeometry(
			utilities.Color.randomColor(), 0.1f, 8);

	private final Body body = new Body(new CircleBodyShape(0.1f), 1.0f);

	private boolean isPickedUp = false;

	public void isDeadly() {

	}

	public boolean isPickUpAble() {
		return isPickedUp;
	}

	/**
	 * Makes the ball unable to be picked up by other players
	 */
	public void pickUp() {
		isPickedUp = true;
	}

	public void throwBall(Vector2f force) {
		body.applyForce(force);
	}

	/**
	 * Makes the ball able to be picked up by other players
	 */
	public void release() {
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

}
package models;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controllers.BallController;
import core.BallGeometry;
import core.Body;
import core.CircleBodyShape;
import core.Geometry;
import core.Manager;

/**
 * PlayerModel represents a player in the game world
 */
public class PlayerModel implements IModel {

	private final Geometry geometry = new BallGeometry(Color.WHITE, 0.5f, 5);

	private final Body body = new Body(new CircleBodyShape(0.5f), 5.0f);

	private BallController ballController = null;

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * @return the ball controller of the ball the player carries, return null
	 *         if the player has no ball
	 */
	public BallController getBallController() {
		return ballController;
	}

	/**
	 * @return true if the player is carrying a ball, otherwise false
	 */
	public boolean isCarryingBall() {
		return ballController != null;
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
		final Vector2f force = new Vector2f(movement);

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
		geometry.moveTowards(body.getPosition(), body.getAngle(),
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION, 0.1f);
	}

	/**
	 * Try to throw a ball
	 * 
	 * @return true if a ball was thrown, otherwise false
	 */
	public boolean throwBall() {
		if (isCarryingBall()) {

			final Vector2f direction = Tools.angleToVector(body.getAngle());

			final Vector2f force = new Vector2f(direction.x
					* Constants.BALL_THROW_SPEED, direction.y
					* Constants.BALL_THROW_SPEED);

			ballController.throwBall(force);

			ballController.releaseBall();

			ballController = null;

			return true;
		}

		return false;
	}

	/**
	 * Try to pick up a ball near the player
	 * 
	 * @return true if a ball was picked up, otherwise false
	 */
	public boolean pickUpBall() {

		if (!isCarryingBall()) {
			final List<BallController> listOfBalls = Manager
					.find(BallController.class);

			for (final BallController bc : listOfBalls) {
				if (Tools.distanceBetween(body.getPosition(), bc.getModel()
						.getBody().getPosition()) <= Constants.BALL_PICK_UP_DISTANCE) {
					if (bc.isPickUpAble()) {
						bc.pickUpBall();
						ballController = bc;
						return true;
					}
				}

			}
		}
		return false;
	}

	public void setPosition(final Vector2f position) {
		body.setPosition(position);

	}

	public float getAngle() {
		return geometry.getAngle();
	}
}

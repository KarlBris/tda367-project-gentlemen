package controller.entities;

import model.entities.BallModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;

import common.body.IBody;
import common.body.IBodyCollisionCallback;

import controller.MainControllerFactory;
import controller.common.IController;
import factories.entities.ShockwaveFactory;

/**
 * This class controls a ball model
 */
public final class BallController implements IController<BallModel>,
		IBodyCollisionCallback {

	private final BallModel model;

	public BallController(final BallModel model) {
		this.model = model;
	}

	/**
	 * Makes the ball unable to be picked up by other players
	 */
	public void pickUpBall() {
		model.pickUp();
	}

	/**
	 * Makes the ball able to be picked up by other players
	 */
	public void releaseBall() {
		model.releaseBall();
	}

	/**
	 * @param referenceVelocity
	 *            the velocity of an object that should be considered static
	 * @return true if the ball can be picked up, otherwise false
	 */
	public boolean isPickUpAble(final Vector2f referenceVelocity) {
		return model.isPickUpAble(referenceVelocity);
	}

	/**
	 * Throw the ball in the force direction and speed
	 * 
	 * @param velocity
	 *            contains the speed an angle of the ball
	 */
	public void throwBall(final Vector2f velocity) {
		model.throwBall(velocity);
	}

	/**
	 * @return the position of the ball
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

	@Override
	public BallModel getModel() {
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);

	}

	@Override
	public void start() {
		// Subscribe to collision events for the model's body
		model.setCollisionCallback(this);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		model.update();

	}

	@Override
	public void collisionOccured(final IBody otherBody,
			final Vector2f collisionPoint) {
		// Instantiate a shockwave if the ball travels fast enough
		if (model.getVelocity().length() >= Constants.BALL_SHOCKWAVE_SPEED) {
			MainControllerFactory.get().instantiate(new ShockwaveFactory(),
					collisionPoint);
		}
	}
}

package controllers;

import models.BallModel;
import models.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;

import components.IBodyCollisionCallback;

import core.Body;
import core.Manager;
import factories.ShockwaveFactory;

/**
 * This class controls a ball model
 */
public class BallController implements IController, IBodyCollisionCallback {

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

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
		return model;
	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);

	}

	/**
	 * @see controllers.IController#start()
	 */
	@Override
	public void start() {
		// Subscribe to collision events for the model's body
		model.getBody().setCollisionCallback(this);
	}

	/**
	 * @see controllers.IController#end()
	 */
	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {

		model.update();

	}

	/**
	 * @see controllers.IController#networkDataSend()
	 */
	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see controllers.IController#networkDataReceive(java.lang.Object[])
	 */
	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IBodyCollisionCallback#collisionOccured(core.Body,
	 *      org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void collisionOccured(final Body otherBody,
			final Vector2f collisionPoint) {
		// Instantiate a shockwave if the ball travels fast enough
		if (model.getBody().getVelocity().length() >= Constants.BALL_SHOCKWAVE_SPEED) {
			Manager.instantiate(new ShockwaveFactory(), collisionPoint);
		}
	}
}

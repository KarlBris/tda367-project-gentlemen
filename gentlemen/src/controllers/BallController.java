package controllers;

import models.BallModel;
import models.IModel;

import org.lwjgl.util.vector.Vector2f;

public class BallController implements IController {

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
	 * @return true if the ball can be picked up, otherwise false
	 */
	public boolean isPickUpAble() {
		return model.isPickUpAble();
	}

	/**
	 * Throw the ball in the force direction and speed
	 * 
	 * @param force
	 *            contains the speed an angle of the ball
	 */
	public void throwBall(Vector2f force) {
		model.throwBall(force);
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
		model.getBody().setPosition(position);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

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
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}

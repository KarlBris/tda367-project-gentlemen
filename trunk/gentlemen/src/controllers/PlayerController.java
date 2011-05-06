package controllers;

import models.IModel;
import models.PlayerModel;
import models.ReticleModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import components.KeyboardComponent;

import core.Manager;
import factories.BallFactory;
import factories.KeyboardReticleFactory;

public class PlayerController implements IController {

	private final PlayerModel model;
	private ReticleModel reticleModel;

	private final int moveRightKey;
	private final int moveLeftKey;
	private final int moveUpKey;
	private final int moveDownKey;
	private final int throwBallKey;
	private final int pickUpBallKey;

	/**
	 * 
	 * @param model
	 *            , the controllers model
	 * @param moveRightKey
	 *            , the key to move right
	 * @param moveLeftKey
	 *            , the key to move left
	 * @param moveUpKey
	 *            , the key to move up
	 * @param moveDownKey
	 *            , the key to move down
	 * @param throwBallKey
	 *            , the key to throw ball
	 * @param pickUpBallKey
	 *            , the key to pick up a ball
	 */
	public PlayerController(final PlayerModel model, final int moveRightKey,
			final int moveLeftKey, final int moveUpKey, final int moveDownKey,
			final int throwBallKey, final int pickUpBallKey) {
		this.model = model;
		this.moveRightKey = moveRightKey;
		this.moveLeftKey = moveLeftKey;
		this.moveUpKey = moveUpKey;
		this.moveDownKey = moveDownKey;
		this.throwBallKey = throwBallKey;
		this.pickUpBallKey = pickUpBallKey;
	}

	@Override
	public void update() {

		// spawn ball
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			final BallController ballController = (BallController) Manager
					.instantiate(new BallFactory(), new Vector2f(
							Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
		}

		// Update player position
		final Vector2f movement = new Vector2f(getKeyDirection());

		model.move(movement);

		// Update player aim direction
		model.faceTowards(getFacingDirection());

		setReticlePosition();

		// Throw ball if correct key is pressed
		if (Manager.getKeyboard().getKeyDown(throwBallKey)) {
			model.throwBall();
		}
		// Pick up ball if correct key is pressed
		if (Manager.getKeyboard().getKeyDown(pickUpBallKey)) {
			model.pickUpBall();
		}

		// Set the position of the ball being carried by the player
		if (model.isCarryingBall()) {

			final Vector2f newBallPosition = new Vector2f();
			Vector2f.add(model.getPosition(),
					Tools.angleToVector(model.getAngle()), newBallPosition);
			model.getBallController().setPosition(newBallPosition);

		}

		model.update();
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void start() {
		// Instantiate the reticle and save the model reference
		reticleModel = (ReticleModel) Manager.instantiate(
				new KeyboardReticleFactory()).getModel();
	}

	@Override
	public void end() {
	}

	/**
	 * @return the angle player facing calculated through the position of the
	 *         player and the mouse position
	 */
	private float getFacingDirection() {
		final Vector2f dirVect = new Vector2f();
		final KeyboardComponent keyboard = Manager.getKeyboard();
		boolean keyPressed = false;

		if (keyboard.getKey(moveUpKey)) {
			model.getBody().clearAngularVelocity();
			dirVect.y -= 1.0f;
			keyPressed = true;
		}
		if (keyboard.getKey(moveDownKey)) {
			model.getBody().clearAngularVelocity();
			dirVect.y += 1.0f;
			keyPressed = true;
		}

		if (keyboard.getKey(moveLeftKey)) {
			model.getBody().clearAngularVelocity();
			dirVect.x -= 1.0f;
			keyPressed = true;
		}
		if (keyboard.getKey(moveRightKey)) {
			model.getBody().clearAngularVelocity();
			dirVect.x += 1.0f;
			keyPressed = true;
		}

		// Normalize the direction vector to always keep the same distance
		if (dirVect.length() > 0.0f) {
			dirVect.normalise();
		}
		if (keyPressed) {
			return Tools.vectorToAngle(dirVect);
		}

		return model.getBody().getAngle();

	}

	/**
	 * Calculate the direction of pushed down keys
	 * 
	 * @return the pushed down keys direction
	 */
	private Vector2f getKeyDirection() {

		final KeyboardComponent keyboard = Manager.getKeyboard();
		final Vector2f dirVect = new Vector2f();

		if (keyboard.getKey(moveUpKey)) {
			dirVect.y -= 1.0f;
		}
		if (keyboard.getKey(moveDownKey)) {
			dirVect.y += 1.0f;
		}

		if (keyboard.getKey(moveLeftKey)) {
			dirVect.x -= 1.0f;
		}
		if (keyboard.getKey(moveRightKey)) {
			dirVect.x += 1.0f;
		}

		// Normalize the direction vector to always keep the same distance
		if (dirVect.length() > 0.0f) {
			dirVect.normalise();
		}

		return dirVect;
	}

	public void setReticlePosition() {
		Vector2f newReticlePosition = new Vector2f();
		Vector2f.add(model.getPosition(),
				Tools.angleToVector(model.getAngle()), newReticlePosition);
		reticleModel.setPosition(newReticlePosition);
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

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);

	}

}

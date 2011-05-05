package controllers;

import models.IModel;
import models.PlayerModel;
import models.ReticleModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;

import components.KeyboardComponent;

import core.Manager;
import factories.BallFactory;
import factories.MouseReticleFactory;

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
	 *            , the key to up ball
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
	public IModel getModel() {
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// Instantiate the reticle and save the model reference
		reticleModel = (ReticleModel) Manager.instantiate(
				new MouseReticleFactory()).getModel();
	}

	@Override
	public void end() {
	}

	@Override
	public void update() {
		// Temporary code to instantiate a new ball entity at the player's
		// position
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			final BallController ballController = (BallController) Manager
					.instantiate(new BallFactory());

			ballController.getModel().getBody()
					.setPosition(model.getPosition());
		}

		// Temporary code to instantiate a new ball entity at the player's
		// location, as well as launch it towards the reticle
		if (Manager.getMouse().getButton(0)) {
			final BallController ballController = (BallController) Manager
					.instantiate(new BallFactory());

			// this is a bit awkward, since getFacingDirection() basically
			// converts the vector to an angle, then the reverse is done by
			// angleToVector()
			// TODO fix this anomaly
			final Vector2f faceVect = Tools.angleToVector(getFacingDirection());

			ballController
					.getModel()
					.getBody()
					.setPosition(
							new Vector2f(model.getPosition().getX()
									+ faceVect.x, model.getPosition().getY()
									+ faceVect.y));

			ballController.getModel().getBody().setAngle(getFacingDirection());

			ballController
					.getModel()
					.getBody()
					.applyForce(
							new Vector2f(faceVect.x * 500, faceVect.y * 500));
		}

		// Update player position
		final Vector2f movement = new Vector2f(getKeyDirection());

		model.move(movement);

		// Update player aim direction
		model.faceTowards(getFacingDirection());

		// Throw ball if space is pressed
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			model.throwBall();
		}

		model.update();
	}

	/**
	 * @return the angle player facing calculated through the position of the
	 *         player and the mouse position
	 */
	private float getFacingDirection() {

		final Vector2f playerToReticle = new Vector2f();

		// Calculate the position of the reticle relative to the player
		Vector2f.sub(reticleModel.getPosition(), model.getPosition(),
				playerToReticle);

		return Tools.vectorToAngle(playerToReticle);
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

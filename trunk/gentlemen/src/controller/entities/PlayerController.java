package controller.entities;

import model.entities.IModel;
import model.entities.PlayerModel;
import model.entities.ReticleModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import controller.components.IBodyCollisionCallback;
import controller.components.KeyboardComponent;

import core.Body;
import core.Manager;
import factories.entities.BallFactory;
import factories.entities.KeyboardReticleFactory;

/**
 * This class controls a player model
 */
public class PlayerController implements IController, IBodyCollisionCallback {

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

	/**
	 * @see controller.entities.IController#update()
	 */
	@Override
	public void update() {

		// spawn ball
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			Manager.instantiate(new BallFactory(),
					new Vector2f(Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
		}

		handleMovement();

		setReticlePosition();

		handleBall();

		handleFlag();

		model.update();
	}

	/**
	 * Handles the flag logic
	 */
	private void handleFlag() {

		// Try to return team flag
		model.returnTeamFlag();

		if (model.isCarryingFlag()) {
			// Change the flag position
			model.updateFlagPosition();

			// Try to pick up enemy flag
			if (model.captureEnemyFlag()) {
				// Add score there
			}
		} else {
			// Try to capture enemy flag
			model.pickUpFlag();
		}
	}

	/**
	 * Handles the ball logic
	 */
	private void handleBall() {

		if (model.isCarryingBall()) {

			// Set the position of the ball being carried by the player
			setBallPosition();

			// Throw ball if correct key is pressed
			if (Manager.getKeyboard().getKeyDown(throwBallKey)) {
				model.throwBall();
			}

		} else {

			// Pick up ball if correct key is pressed
			if (Manager.getKeyboard().getKeyDown(pickUpBallKey)) {
				model.pickUpBall();
			}
		}
	}

	/**
	 * Drops the flag, if carried
	 * 
	 * @return true if the flag was dropped, false if no flag was carried at all
	 */
	public boolean dropFlag() {
		return model.dropFlag();
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void start() {
		// Subscribe to collision events for the model's body
		model.getBody().setCollisionCallback(this);

		// Instantiate the reticle and save the model reference
		reticleModel = (ReticleModel) Manager.instantiate(
				new KeyboardReticleFactory()).getModel();
	}

	@Override
	public void end() {
	}

	/**
	 * Handles the movement of the player
	 */
	private void handleMovement() {
		final Vector2f dirVect = new Vector2f();
		final KeyboardComponent keyboard = Manager.getKeyboard();
		boolean keyPressed = false;

		if (keyboard.getKey(moveUpKey)) {
			dirVect.y -= 1.0f;
			keyPressed = true;
		}

		if (keyboard.getKey(moveDownKey)) {
			dirVect.y += 1.0f;
			keyPressed = true;
		}

		if (keyboard.getKey(moveLeftKey)) {
			dirVect.x -= 1.0f;
			keyPressed = true;
		}

		if (keyboard.getKey(moveRightKey)) {
			dirVect.x += 1.0f;
			keyPressed = true;
		}

		// Normalize the direction vector to always keep the same distance
		if (dirVect.length() > 0.0f) {
			dirVect.normalise();
		}

		if (keyPressed) {
			model.getBody().clearAngularVelocity();

			model.move(dirVect);
			model.faceTowards(Tools.vectorToAngle(dirVect));
		}
	}

	/**
	 * Sets the position of the reticle
	 */
	private void setReticlePosition() {
		final Vector2f newReticlePosition = Tools.angleToVector(model
				.getAngle());

		newReticlePosition.scale(Constants.PLAYER_RETICLE_DISTANCE);

		Vector2f.add(model.getPosition(), newReticlePosition,
				newReticlePosition);

		reticleModel.setPosition(newReticlePosition);
	}

	/**
	 * Sets the position of the ball being carried by the player
	 */
	private void setBallPosition() {
		final Vector2f newBallPosition = Tools.angleToVector(model.getAngle());

		newBallPosition.scale(Constants.PLAYER_BALL_CARRYING_DISTANCE);

		Vector2f.add(model.getPosition(), newBallPosition, newBallPosition);

		model.getBallController().setPosition(newBallPosition);
	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);

	}

	@Override
	public void collisionOccured(final Body otherBody,
			final Vector2f collisionPoint) {

		// If something fast hits this player, drop ball and flag
		if (otherBody.getVelocity().length() >= Constants.BALL_LETHAL_SPEED) {
			model.playerKnockOut();
		}
	}

	/**
	 * Sets the team of the player
	 * 
	 * @param team
	 *            the team to which the player will belong
	 */
	public void setTeam(final TeamController team) {
		model.setTeam(team);

	}

}

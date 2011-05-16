package controller.entities;

import java.util.List;

import model.entities.PlayerModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import common.body.Body;
import common.body.IBodyCollisionCallback;

import controller.common.IController;
import controller.components.KeyboardComponent;
import core.Manager;
import factories.entities.BallFactory;
import factories.entities.KeyboardReticleFactory;

/**
 * This class controls a player model
 */
public class PlayerController implements IController<PlayerModel>,
		IBodyCollisionCallback {

	private final PlayerModel model;

	private KeyboardReticleController reticleController;

	private BallController ballController = null;

	private FlagController flagController = null;

	private TeamController teamController = null;

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
	 * @see controller.common.IController#update()
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
		returnTeamFlag();

		if (model.isCarryingFlag()) {
			// Change the flag position
			updateFlagPosition();

			// Try to pick up enemy flag
			captureEnemyFlag();

		} else {
			// Try to capture enemy flag
			pickUpFlag();
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
				throwBall();
			}

		} else {

			// Pick up ball if correct key is pressed
			if (Manager.getKeyboard().getKeyDown(pickUpBallKey)) {
				pickUpBall();
			}
		}
	}

	/**
	 * Drops the flag, if carried
	 * 
	 * @return true if the flag was dropped, false if no flag was carried
	 */
	public boolean dropFlag() {
		if (model.isCarryingFlag()) {
			flagController.releaseFlag();
			flagController = null;
			model.releaseFlag();
			return true;
		}
		return false;

	}

	@Override
	public PlayerModel getModel() {
		return model;
	}

	@Override
	public void start() {
		// Subscribe to collision events for the model's body
		model.getBody().setCollisionCallback(this);

		// Instantiate the reticle and save the model reference
		reticleController = Manager.instantiate(new KeyboardReticleFactory());
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

		reticleController.setPosition(newReticlePosition);
	}

	/**
	 * Sets the position of the ball being carried by the player
	 */
	private void setBallPosition() {
		final Vector2f newBallPosition = Tools.angleToVector(model.getAngle());

		newBallPosition.scale(Constants.PLAYER_BALL_CARRYING_DISTANCE);

		Vector2f.add(model.getPosition(), newBallPosition, newBallPosition);

		ballController.setPosition(newBallPosition);
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
			playerKnockOut();
		}
	}

	/**
	 * Sets the team to which the player will belong
	 * 
	 * @param team
	 *            the team the player will belong to
	 */
	public void setTeam(final TeamController team) {
		this.teamController = team;
		setPosition(team.getHomePosition());

	}

	/**
	 * 
	 * @return the players team controller
	 */
	public TeamController getTeam() {
		return teamController;
	}

	/**
	 * Init knocked out
	 */
	public void playerKnockOut() {
		model.playerKnockOut();
		throwBall();
		dropFlag();
		for (final TeamController tc : Manager.find(TeamController.class)) {
			if (tc != this.teamController) {
				tc.addScore(Constants.KNOCK_OUT_SCORE);
			}
		}
	}

	/**
	 * @return the flag controller of the flag the player carries, return null
	 *         if the player has no flag
	 */
	public FlagController getFlagController() {
		return flagController;
	}

	/**
	 * Try to capture the enemy flag at your home base. It require that team
	 * flag is placed at home
	 * 
	 * @return true if the flag was capture, otherwise false
	 */
	private boolean captureEnemyFlag() {
		if (model.isCarryingFlag()) {
			final List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (final FlagController fc : flagControllers) {
				if (fc.getTeam() == this.teamController) {
					if (Tools.distanceBetween(model.getPosition(),
							fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE
							&& fc.isAtHome()) {
						flagController.returnFlagHome();
						flagController = null;
						teamController.addScore(Constants.FLAG_CAPTURE_SCORE);
						model.releaseFlag();
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * returnTeamFlag checks if the team flag is within pick up distance, and if
	 * it is, and it's not at home it returns it home
	 * 
	 * @return true if team flag was picked up, otherwise false
	 */
	private boolean returnTeamFlag() {

		final List<FlagController> flagControllers = Manager
				.find(FlagController.class);
		for (final FlagController fc : flagControllers) {
			if (fc.getTeam() == this.teamController) {
				if (Tools
						.distanceBetween(model.getPosition(), fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
					if (fc.isPickUpAble() && !fc.isAtHome()) {
						fc.returnFlagHome();
						teamController.addScore(Constants.FLAG_RETURN_SCORE);
						model.releaseFlag();
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Try to pick up an enemy flag if it's within pick up distance
	 * 
	 * @return true if a flag was picked up, otherwise false
	 */
	private boolean pickUpFlag() {
		if (!model.isCarryingFlag() && !model.isKnockedOut()) {
			final List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (final FlagController fc : flagControllers) {
				if (fc.getTeam() != this.teamController) {
					if (Tools.distanceBetween(model.getPosition(),
							fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
						if (fc.isPickUpAble()) {
							fc.pickUpFlag();
							flagController = fc;
							model.pickUpFlag();
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Try to throw a ball
	 * 
	 * @return true if a ball was thrown, otherwise false
	 */
	private boolean throwBall() {
		if (model.isCarryingBall()) {

			final Vector2f velocityAtPoint = model.getBody()
					.getVelocityAtPoint(ballController.getPosition());
			final Vector2f direction = Tools.angleToVector(model.getBody()
					.getAngle());
			final Vector2f velocity = new Vector2f(direction);

			velocity.scale(Constants.BALL_THROW_SPEED);

			Vector2f.add(velocity, velocityAtPoint, velocity);

			ballController.throwBall(velocity);

			ballController.releaseBall();

			ballController = null;

			model.releaseBall();

			return true;
		}

		return false;
	}

	/**
	 * Try to pick up a ball near the player
	 * 
	 * @return true if a ball was picked up, otherwise false
	 */
	private boolean pickUpBall() {

		if (!model.isCarryingBall()) {
			final List<BallController> listOfBalls = Manager
					.find(BallController.class);

			for (final BallController bc : listOfBalls) {
				if (Tools
						.distanceBetween(model.getPosition(), bc.getPosition()) <= Constants.BALL_PICK_UP_DISTANCE) {
					if (bc.isPickUpAble(model.getBody().getVelocity())) {
						bc.pickUpBall();
						model.pickUpBall();
						ballController = bc;
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * If a flag is carried, update the position of the flag
	 */
	private void updateFlagPosition() {
		if (model.isCarryingFlag()) {
			flagController.setPosition(new Vector2f(model.getPosition().x,
					model.getPosition().y - 0.7f));
		}
	}

}

package model.entities;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import common.geometry.AbstractGeometry;
import common.geometry.IGeometry;
import common.geometry.twodimensions.CircleGeometry;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controller.entities.BallController;
import controller.entities.FlagController;
import controller.entities.TeamController;
import core.Manager;
import core.body.Body;
import core.body.CircleBodyShape;

/**
 * PlayerModel represents a player in the game world
 */
public class PlayerModel implements IModel {

	private final AbstractGeometry geometry;

	private final Body body = new Body(new CircleBodyShape(0.5f), 2.0f, 3.0f);

	private final Color playerColor;

	private BallController ballController = null;

	private FlagController flagController = null;

	private TeamController teamController = null;

	private boolean isKnockedOut = false;

	private float timeSinceKnockedOut = 0.0f;

	/**
	 * @param teamColor
	 *            , is the color the team the players in, and also the color the
	 *            player will have
	 */
	public PlayerModel(final Color teamColor) {
		playerColor = teamColor;
		geometry = new CircleGeometry(teamColor, 1.0f, 0.5f, 5);
	}

	/**
	 * Init knocked out
	 */
	public void playerKnockOut() {
		timeSinceKnockedOut = 0.0f;
		isKnockedOut = true;

		throwBall();
		dropFlag();
		for (final TeamController tc : Manager.find(TeamController.class)) {
			if (tc != this.teamController) {
				tc.addScore(Constants.KNOCK_OUT_SCORE);
			}
		}
	}

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * @return the flag controller of the flag the player carries, return null
	 *         if the player has no flag
	 */
	public FlagController getFlagController() {
		return flagController;
	}

	/**
	 * @return true if the player is carrying a flag, otherwise false
	 */
	public boolean isCarryingFlag() {
		return flagController != null;
	}

	/**
	 * Try to capture the enemy flag at your home base. It require that team
	 * flag is placed at home
	 * 
	 * @return true if the flag was capture, otherwise false
	 */
	public boolean captureEnemyFlag() {
		if (isCarryingFlag()) {
			final List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (final FlagController fc : flagControllers) {
				if (fc.getTeam() == this.teamController) {
					if (Tools.distanceBetween(getPosition(), fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE
							&& fc.isAtHome()) {
						flagController.returnFlagHome();
						flagController = null;
						addScore(Constants.FLAG_CAPTURE_SCORE);
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
	public boolean returnTeamFlag() {

		final List<FlagController> flagControllers = Manager
				.find(FlagController.class);
		for (final FlagController fc : flagControllers) {
			if (fc.getTeam() == this.teamController) {
				if (Tools.distanceBetween(body.getPosition(), fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
					if (fc.isPickUpAble() && !fc.isAtHome()) {
						fc.returnFlagHome();
						addScore(Constants.FLAG_RETURN_SCORE);
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
	public boolean pickUpFlag() {
		if (!isCarryingFlag() && !isKnockedOut) {
			final List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (final FlagController fc : flagControllers) {
				if (fc.getTeam() != this.teamController) {
					if (Tools.distanceBetween(body.getPosition(),
							fc.getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
						if (fc.isPickUpAble()) {
							fc.pickUpFlag();
							flagController = fc;
							return true;
						}
					}
				}
			}
		}

		return false;
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
		return body.getPosition();
	}

	/**
	 * Moves the player in any direction
	 * 
	 * @param movement
	 *            the direction to move in
	 */
	public void move(final Vector2f movement) {
		final Vector2f force = new Vector2f(movement);

		force.scale(Constants.PLAYER_MOVEMENT_ACCELERATION);

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
	 * The angle the body is facing
	 * 
	 * @return the angle the player face
	 */
	public float getFacingAngle() {
		return body.getAngle();
	}

	/**
	 * Updates the current PlayerModel
	 */
	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle(),
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION, 0.1f);
		if (isKnockedOut) {

			// Add time counter
			timeSinceKnockedOut += Constants.DELTA_TIME;

			// Set the color of the player to a shade of gray
			geometry.setColor(new Color((playerColor.getRed() + 0.5f) / 2,
					(playerColor.getGreen() + 0.5f) / 2,
					(playerColor.getBlue() + 0.5f) / 2,
					(playerColor.getAlpha() + 0.5f) / 2));

			if (timeSinceKnockedOut >= Constants.PLAYER_KNOCKED_OUT_TIME) {

				// No longer knocked out
				isKnockedOut = false;

				// Reset the color of the player
				geometry.setColor(playerColor);
			}
		}

	}

	/**
	 * Try to throw a ball
	 * 
	 * @return true if a ball was thrown, otherwise false
	 */
	public boolean throwBall() {
		if (isCarryingBall()) {

			final Vector2f velocityAtPoint = body
					.getVelocityAtPoint(ballController.getPosition());
			final Vector2f direction = Tools.angleToVector(body.getAngle());
			final Vector2f velocity = new Vector2f(direction);

			velocity.scale(Constants.BALL_THROW_SPEED);

			Vector2f.add(velocity, velocityAtPoint, velocity);

			ballController.throwBall(velocity);

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
				if (Tools.distanceBetween(getPosition(), bc.getPosition()) <= Constants.BALL_PICK_UP_DISTANCE) {
					if (bc.isPickUpAble(body.getVelocity())) {
						bc.pickUpBall();
						ballController = bc;
						return true;
					}
				}

			}
		}
		return false;
	}

	/**
	 * Sets the position of the player
	 * 
	 * @param position
	 *            the position to be set
	 */
	public void setPosition(final Vector2f position) {
		body.setPosition(position);
	}

	/**
	 * Returns the visual angle of the player
	 * 
	 * @return the angle of the player
	 */
	public float getAngle() {
		return geometry.getAngle();
	}

	/**
	 * Set the visual angle of the player
	 * 
	 * @param angle
	 *            is the new visual angle
	 */
	public void setAngle(final float angle) {
		geometry.setAngle(angle);
	}

	/**
	 * Drops the flag, if carried
	 * 
	 * @return true if the flag was dropped, false if no flag was carried
	 */
	public boolean dropFlag() {
		if (isCarryingFlag()) {
			flagController.releaseFlag();
			flagController = null;
			return true;
		}
		return false;

	}

	/**
	 * If a flag is carried, update the position of the flag
	 */
	public void updateFlagPosition() {
		if (isCarryingFlag()) {
			flagController.setPosition(new Vector2f(getPosition().x,
					getPosition().y - 0.7f));
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
	 * Adds score to the team total
	 * 
	 * @param amount
	 *            the amount of score points to be added to the team total
	 */
	private void addScore(final int amount) {
		teamController.addScore(amount);
	}

	/**
	 * 
	 * @return true if the players is knocked out, otherwise false
	 */
	public boolean isKnockedOut() {
		return isKnockedOut;
	}

	/**
	 * 
	 * @return returns the time since the player was knocked out
	 */
	public float getKnockedOutTimer() {
		return timeSinceKnockedOut;
	}

	/**
	 * 
	 * @return the players team controller
	 */
	public TeamController getTeam() {
		return teamController;
	}

}

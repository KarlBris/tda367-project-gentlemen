package models;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controllers.BallController;
import controllers.FlagController;
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

	private int teamIndex;

	private BallController ballController = null;

	private FlagController flagController = null;

	public PlayerModel(int teamIndex) {
		this.teamIndex = teamIndex;
	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * @param teamIndex
	 *            , set the index of the players team
	 */
	public void setTeamIndex(int teamIndex) {
		this.teamIndex = teamIndex;
	}

	/**
	 * 
	 * @return the players team index
	 */
	public int getTeamIndex() {
		return this.teamIndex;
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
			List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (FlagController fc : flagControllers) {
				if (fc.getFlagTeamIndex() == this.teamIndex) {
					if (Tools.distanceBetween(body.getPosition(), fc.getModel()
							.getGeometry().getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE
							&& fc.isAtHome()) {
						flagController.returnFlagHome();
						flagController = null;
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
		List<FlagController> flagControllers = Manager
				.find(FlagController.class);
		for (FlagController fc : flagControllers) {
			if (fc.getFlagTeamIndex() == this.teamIndex) {
				if (Tools.distanceBetween(body.getPosition(), fc.getModel()
						.getGeometry().getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
					if (fc.isPickUpAble()) {

						fc.returnFlagHome();
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
		if (!isCarryingFlag()) {
			List<FlagController> flagControllers = Manager
					.find(FlagController.class);
			for (FlagController fc : flagControllers) {
				if (fc.getFlagTeamIndex() != this.teamIndex) {
					if (Tools.distanceBetween(body.getPosition(), fc.getModel()
							.getGeometry().getPosition()) <= Constants.FLAG_PICK_UP_DISTANCE) {
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

			Vector2f velocityAtPoint = body.getVelocityAtPoint(ballController
					.getPosition());
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
				if (Tools.distanceBetween(body.getPosition(), bc.getPosition()) <= Constants.BALL_PICK_UP_DISTANCE) {
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

	public void setPosition(final Vector2f position) {
		body.setPosition(position);

	}

	public float getAngle() {
		return geometry.getAngle();
	}

	public boolean dropFlag() {
		if (isCarryingFlag()) {
			flagController.releaseFlag();
			return true;
		}
		return false;

	}

	public void updateFlagPosition() {
		if (isCarryingFlag()) {
			flagController.setPosition(new Vector2f(getPosition().x,
					getPosition().y - 0.5f));
		}
	}
}

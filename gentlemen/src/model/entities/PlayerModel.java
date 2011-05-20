package model.entities;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;

import common.body.Body;
import common.body.CircleBodyShape;
import common.body.IBody;
import common.body.IBodyCollisionCallback;
import common.geometry.AbstractGeometry;
import common.geometry.IGeometry;
import common.geometry.twodimensions.CircleGeometry;

/**
 * PlayerModel represents a player in the game world
 */
public final class PlayerModel implements IModel {

	private final AbstractGeometry geometry;

	private final Body body = new Body(new CircleBodyShape(0.5f), 2.0f, 3.0f);

	private final Color playerColor;

	private boolean isCarryingFlag = false;

	private boolean isCarryingBall = false;

	private boolean isKnockedOut = false;

	private float timeSinceKnockedOut = 0.0f;

	/**
	 * Subscribes to collision callback for the model
	 * 
	 * @param collisionCallback
	 *            the object to call when collisions occurs
	 */
	public void setCollisionCallback(
			final IBodyCollisionCallback collisionCallback) {
		body.setCollisionCallback(collisionCallback);
	}

	/**
	 * @param teamColor
	 *            , is the color the team the players in, and also the color the
	 *            player will have
	 */
	public PlayerModel(final Color teamColor) {
		playerColor = teamColor;
		geometry = new CircleGeometry(teamColor, 1.0f, 0.5f, 5);
	}

	public Vector2f getVelocity() {
		return body.getVelocity();
	}

	public Vector2f getVelocityAtPoint(final Vector2f point) {
		return body.getVelocityAtPoint(point);
	}

	/**
	 * Init knocked out
	 */
	public void playerKnockOut() {
		timeSinceKnockedOut = 0.0f;
		isKnockedOut = true;

	}

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public IBody getBody() {
		return body;
	}

	/**
	 * Set the player to be carrying flag
	 */
	public void pickUpFlag() {
		isCarryingFlag = true;
	}

	/**
	 * Set the player to not be carrying flag
	 */
	public void releaseFlag() {
		isCarryingFlag = false;
	}

	/**
	 * @return true if the player is carrying a flag, otherwise false
	 */
	public boolean isCarryingFlag() {
		return isCarryingFlag;
	}

	/**
	 * Set the player to be carrying ball
	 */
	public void pickUpBall() {
		isCarryingBall = true;
	}

	/**
	 * Set the player to not be carrying ball
	 */
	public void releaseBall() {
		isCarryingBall = false;
	}

	/**
	 * @return true if the player is carrying a ball, otherwise false
	 */
	public boolean isCarryingBall() {
		return isCarryingBall;
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
		body.clearAngularVelocity();

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
	 * Sets the position of the player
	 * 
	 * @param position
	 *            the position to be set
	 */
	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);
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

}

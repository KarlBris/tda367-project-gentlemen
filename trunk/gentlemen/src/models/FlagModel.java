package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;
import controllers.TeamController;
import core.Body;
import core.CircleGeometry;
import core.Geometry;

public class FlagModel implements IModel {
	private final Geometry geometry;

	private Vector2f homePosition = null;

	private final Color flagColor;

	private boolean isPickedUp = false;

	private TeamController teamController;

	public FlagModel(final Color c) {
		flagColor = c;

		geometry = new CircleGeometry(flagColor, 0.0f, 0.3f, 3);
	}

	/**
	 * Makes the flag unable to be picked up by other players
	 */
	public void pickUpFlag() {
		isPickedUp = true;
	}

	/**
	 * Makes the flag able to be picked up by other players
	 */
	public void releaseFlag() {
		isPickedUp = false;
	}

	/**
	 * @return if the flag is pick up able
	 */
	public boolean isPickUpAble() {
		return !isPickedUp;
	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return null;
	}

	/**
	 * Set the position of the flag. The first time this i set the home position
	 * is defined
	 * 
	 * @param position
	 *            , is the flags new position
	 */
	public void setPosition(final Vector2f position) {
		if (homePosition == null) {
			homePosition = position;
		}
		geometry.setPosition(position);
	}

	/**
	 * @return the position of the flag
	 */
	public Vector2f getPosition() {
		return geometry.getPosition();
	}

	/**
	 * @return the team controller the flag belongs to
	 */
	public TeamController getTeam() {
		return teamController;
	}

	/**
	 * @return the home position of the flag
	 */
	public Vector2f getHomePosition() {
		return Tools.cloneVector(homePosition);
	}

	/**
	 * Return the flag back to it's home position, and make it pick up able by
	 * other players
	 */
	public void returnFlagHome() {
		isPickedUp = false;
		geometry.setPosition(homePosition);
	}

	/**
	 * @return true if the flag is at it's home position and is pick up able
	 */
	public boolean isAtHome() {
		return Tools.distanceBetween(geometry.getPosition(), homePosition) <= 0.001f;
	}

	/**
	 * Set the team the flag belongs to
	 * 
	 * @param team
	 *            , is the team the flag will belong to
	 */
	public void setTeam(final TeamController team) {
		this.teamController = team;

	}
}
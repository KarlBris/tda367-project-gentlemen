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
		this.flagColor = c;

		geometry = new CircleGeometry(c, 0.0f, 0.3f, 3);
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
	 * 
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

	public void setPosition(final Vector2f position) {
		if (homePosition == null) {
			homePosition = position;
		}
		geometry.setPosition(position);
	}

	public TeamController getTeam() {
		return teamController;
	}

	public Vector2f getHomePosition() {
		return Tools.cloneVector(homePosition);
	}

	public void returnFlagHome() {
		isPickedUp = false;
		geometry.setPosition(homePosition);
	}

	public boolean isAtHome() {
		return geometry.getPosition().equals(homePosition);
	}

	public void setTeam(final TeamController team) {
		this.teamController = team;

	}
}

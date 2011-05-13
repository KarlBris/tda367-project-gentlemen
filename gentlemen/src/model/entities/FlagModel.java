package model.entities;


import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import common.body.Body;
import common.geometry.AbstractGeometry;
import common.geometry.IGeometry;
import common.geometry.twodimensions.CircleGeometry;

import utilities.Color;
import utilities.Tools;
import controller.entities.TeamController;

/**
 * Represents a flag which players can interact with
 */
public class FlagModel implements IModel {
	private final AbstractGeometry geometry;

	private Color flagColor;

	private boolean isPickedUp = false;

	private TeamController teamController;

	/**
	 * @param c
	 *            the color the flag will have
	 */
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
	public IGeometry getGeometry() {
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
		return Tools.cloneVector(teamController.getHomePosition());
	}

	/**
	 * Return the flag back to it's home position, and make it pick up able by
	 * other players
	 */
	public void returnFlagHome() {
		isPickedUp = false;
		geometry.setPosition(teamController.getHomePosition());
	}

	/**
	 * @return true if the flag is at it's home position and is pick up able
	 */
	public boolean isAtHome() {
		return Tools.distanceBetween(geometry.getPosition(),
				teamController.getHomePosition()) <= 0.001f;
	}

	/**
	 * Set the team the flag belongs to
	 * 
	 * @param team
	 *            the team the flag will belong to
	 */
	public void setTeam(final TeamController team) {
		this.teamController = team;
		setPosition(team.getHomePosition());
	}

	/**
	 * Sets the color of the flag
	 * 
	 * @param color
	 *            the color to be set
	 */
	public void setColor(final Color color) {
		flagColor = color;
		geometry.setColor(color);

	}

	public Color getColor() {
		return flagColor;
	}
}

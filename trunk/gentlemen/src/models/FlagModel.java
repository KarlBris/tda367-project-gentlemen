package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.BallGeometry;
import core.Body;
import core.Geometry;

public class FlagModel implements IModel {
	private final Geometry geometry;

	private final int teamIndex;

	private final Vector2f startPosition;

	private final Color flagColor;

	private boolean isPickedUp = false;

	public FlagModel(int teamIndex, Vector2f startPosition, Color c) {
		this.teamIndex = teamIndex;
		this.startPosition = startPosition;
		this.flagColor = c;

		geometry = new BallGeometry(c, 0.3f, 3);
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

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return null;
	}

	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);
	}
}

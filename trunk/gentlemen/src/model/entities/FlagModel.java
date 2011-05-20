package model.entities;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.AbstractGeometry;
import common.geometry.IGeometry;
import common.geometry.twodimensions.CircleGeometry;

/**
 * Represents a flag which players can interact with
 */
public final class FlagModel implements IModel {
	private final AbstractGeometry geometry;
	private final IBody body = new NullBody();

	private Color flagColor;

	private boolean isPickedUp = false;

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
	public IBody getBody() {
		return body;
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

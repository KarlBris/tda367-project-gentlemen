package model.entities;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.AbstractGeometry;
import common.geometry.IGeometry;
import common.geometry.twodimensions.CircleGeometry;

/**
 * Represents a shockwave in the game
 */
public final class ShockwaveModel implements IModel {

	private final AbstractGeometry geometry = new CircleGeometry(Color.RED,
			0.05f, 0.5f, 5);
	private final IBody body = new NullBody();

	private float removeTimer = 0.0f;

	public ShockwaveModel() {
		geometry.setScale(new Vector2f(0.0f, 0.0f));
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
	 * Sets the position of the model
	 * 
	 * @param position
	 *            the position
	 */
	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);
	}

	/**
	 * @return the position of the model
	 */
	public Vector2f getPosition() {
		return geometry.getPosition();
	}

	/**
	 * @return true if the shockwave animation has finished, false otherwise
	 */
	public boolean isFinished() {
		return getAnimationScalar() >= 1.0f;
	}

	/**
	 * @return a scalar in the range [0, 1] that determines how far the
	 *         animation has played
	 */
	public float getAnimationScalar() {
		return Tools.clampValue(removeTimer
				/ Constants.SHOCKWAVE_ANIMATION_TIME, 0.0f, 1.0f);
	}

	/**
	 * Updates the model
	 */
	public void update() {
		// Update timer
		removeTimer += Constants.DELTA_TIME;

		// Scale and rotate the geometry to imitate a shockwave
		final float time = getAnimationScalar();
		final float scale = 1.0f - time;

		geometry.setColor(new Color(1.0f, scale, 0.0f));
		geometry.setScale(new Vector2f(scale, scale));
		geometry.setAngle(Constants.TWO_PI * time);
	}
}

package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import core.Body;
import core.CircleGeometry;
import core.Geometry;

public class ShockwaveModel implements IModel {

	private Geometry geometry = new CircleGeometry(new Color(0.0f, 0.0f, 0.0f),
			-0.5f, 0.5f, 5);

	private float removeTimer = 0.0f;

	public ShockwaveModel() {
		geometry.setScale(new Vector2f(0.0f, 0.0f));
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
	 * Sets the position of the model
	 * 
	 * @param position
	 *            the position
	 */
	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);
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
		float time = getAnimationScalar();
		float scale = 1.0f - time;

		geometry.setScale(new Vector2f(scale, scale));
		geometry.setAngle(Constants.TWO_PI * time);
	}
}

package model.entities;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import core.Body;
import core.CircleGeometry;
import core.Geometry;

/**
 * Represents a shockwave in the game
 */
public class ShockwaveModel implements IModel {

	private final Geometry geometry = new CircleGeometry(Color.RED, -0.5f,
			0.5f, 5);

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

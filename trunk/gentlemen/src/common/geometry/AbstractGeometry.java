package common.geometry;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

/**
 * This class represents a visible geometry in the game world. This class
 * includes basic world state and state interpolation code.
 */
public abstract class AbstractGeometry implements IGeometry {

	private Vector2f position = new Vector2f();
	private float angle = 0.0f;
	private Vector2f scale = new Vector2f(1.0f, 1.0f);

	private Color color;
	private float depth;

	public AbstractGeometry(final Color color, final float depth) {
		this.color = color;
		this.depth = depth;
	}

	@Override
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the geometry
	 * 
	 * @param color
	 *            the new color of the geometry
	 */
	public void setColor(final Color color) {
		this.color = color;
	}

	@Override
	public float getDepth() {
		return depth;
	}

	/**
	 * Sets the depth of the geometry
	 * 
	 * @param depth
	 *            the new depth of the geometry
	 */
	public void setDepth(final float depth) {
		this.depth = depth;
	}

	/**
	 * @return the position of the geometry
	 */
	@Override
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	/**
	 * Sets the position of the geometry
	 * 
	 * @param position
	 */
	public void setPosition(final Vector2f position) {
		this.position = new Vector2f(position);
	}

	/**
	 * @return the angle of the geometry
	 */
	@Override
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the angle of the geometry
	 * 
	 * @param angle
	 *            in radians
	 */
	public void setAngle(final float angle) {
		this.angle = Tools.wrapAngle(angle);
	}

	/**
	 * @return the scale of the geometry
	 */
	@Override
	public Vector2f getScale() {
		return new Vector2f(scale);
	}

	/**
	 * Sets the scale of the geometry
	 * 
	 * @param scale
	 */
	public void setScale(final Vector2f scale) {
		this.scale = new Vector2f(scale);
	}

	/**
	 * Moves the geometry towards a target.
	 * 
	 * @param targetPosition
	 *            the target position
	 * @param targetAngle
	 *            the target angle
	 * @param positionAmount
	 *            the amount to move the position
	 * @param angleAmount
	 *            the amount to move the angle
	 */
	public void moveTowards(final Vector2f targetPosition,
			final float targetAngle, final float positionAmount,
			final float angleAmount) {
		// Move position towards the targetPosition
		final Vector2f delta = new Vector2f();
		Vector2f.sub(targetPosition, getPosition(), delta);
		delta.scale(positionAmount);

		final Vector2f newPosition = new Vector2f();
		Vector2f.add(getPosition(), delta, newPosition);

		setPosition(newPosition);

		// Move angle towards targetAngle
		final float angleDelta = Tools.closestAngleDelta(getAngle(),
				targetAngle);

		setAngle(getAngle() + angleDelta * angleAmount);
	}

	/**
	 * Moves the geometry towards a target. The speed is controlled by the
	 * GEOMETRY_TO_PHYSICS_INTERPOLATION constant.
	 * 
	 * @param targetPosition
	 *            the target position
	 * @param targetAngle
	 *            the target angle
	 */
	public void moveTowards(final Vector2f targetPosition,
			final float targetAngle) {
		moveTowards(targetPosition, targetAngle,
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION,
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);
	}
}
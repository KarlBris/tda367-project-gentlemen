package model.entities;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.body.Body;
import core.body.BoxBodyShape;
import core.geometry.BoxGeometry;
import core.geometry.Geometry;

/**
 * Represent inanimate within the game world.
 */
public class PropModel implements IModel {

	private final Geometry geometry;

	private final Body body;

	/**
	 * 
	 * @param depth
	 *            , the layer which it's drawn in
	 * @param width
	 *            , the width of the prop
	 * @param height
	 *            , the height of the prop
	 * @param mass
	 *            , the mass of the prop
	 */
	public PropModel(final float depth, final float width, final float height,
			final float mass) {

		geometry = new BoxGeometry(Color.RED, depth, width, height);

		body = new Body(new BoxBodyShape(width, height), mass);
	}

	/**
	 * 
	 * @param depth
	 *            , the layer which it's drawn in
	 * @param width
	 *            , the width of the prop
	 * @param height
	 *            , the height of the prop
	 * @param mass
	 *            , the mass of the prop
	 * @param color
	 *            , the color of the prop
	 */
	public PropModel(final float depth, final float width, final float height,
			final float mass, final Color color) {

		geometry = new BoxGeometry(color, depth, width, height);

		body = new Body(new BoxBodyShape(width, height), mass);
	}

	@Override
	public Geometry getGeometry() {

		return geometry;
	}

	@Override
	public Body getBody() {

		return body;
	}

	/**
	 * Set a position of the prop
	 * 
	 * @param position
	 *            , is the new position for the prop
	 */
	public void setPosition(final Vector2f position) {
		body.setPosition(position);
		geometry.setPosition(position);
	}

	/**
	 * Set the angle of the prop
	 * 
	 * @param angle
	 *            , is the new angle for the prop
	 */
	public void setAngle(final float angle) {
		body.setAngle(angle);
		geometry.setAngle(angle);
	}

	/**
	 * Get the position of the prop
	 * 
	 * @return the position of the prop
	 */
	public Vector2f getPosition() {
		return body.getPosition();
	}

	/**
	 * Get the angle of the prop
	 * 
	 * @return the angle of the prop
	 */
	public float getAngle() {
		return body.getAngle();
	}

	/**
	 * Update the props position
	 */
	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
	}

	/**
	 * Give prop a new color
	 * 
	 * @param c
	 *            , is the new color
	 */
	public void setColor(final Color c) {
		geometry.setColor(c);

	}

}

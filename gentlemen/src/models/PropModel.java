package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Body;
import core.BoxBodyShape;
import core.BoxGeometry;
import core.Geometry;

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
	 * Update the props position
	 */
	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
	}

}

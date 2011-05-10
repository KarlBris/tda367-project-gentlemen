package models;

import org.lwjgl.util.vector.Vector2f;

import core.Body;
import core.Geometry;
import core.ReticleGeometry;

/**
 * Represents a reticle in the game
 */
public class ReticleModel implements IModel {

	private final Geometry geometry = new ReticleGeometry();

	/**
	 * @see models.IModel#getGeometry()
	 */
	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @see models.IModel#getBody()
	 */
	@Override
	public Body getBody() {
		return null;
	}

	/**
	 * Set the position of the reticle
	 * 
	 * @param position
	 *            , is the new position of the reticle
	 */
	public void setPosition(final Vector2f position) {
		geometry.setPosition(position);
	}

	/**
	 * 
	 * @return the position of the reticle
	 */
	public Vector2f getPosition() {
		return geometry.getPosition();
	}

	/**
	 * Add reticle position with a vector
	 * 
	 * @param movement
	 *            , the vector to be added to the reticle position
	 */
	public void move(final Vector2f movement) {
		final Vector2f newPosition = new Vector2f();

		Vector2f.add(geometry.getPosition(), movement, newPosition);

		geometry.setPosition(newPosition);
	}
}

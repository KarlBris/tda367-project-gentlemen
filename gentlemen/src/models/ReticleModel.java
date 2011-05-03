package models;

import org.lwjgl.util.vector.Vector2f;

import core.Body;
import core.Geometry;
import core.ReticleGeometry;

public class ReticleModel implements IModel {

	private Geometry geometry = new ReticleGeometry();

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

	public Vector2f getPosition() {
		return geometry.getPosition();
	}

	public void move(final Vector2f movement) {
		Vector2f newPosition = new Vector2f();

		Vector2f.add(geometry.getPosition(), movement, newPosition);

		geometry.setPosition(newPosition);
	}
}

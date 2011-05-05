package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Body;
import core.BoxBodyShape;
import core.Geometry;
import core.RectangleGeometry;

public class PropModel implements IModel {

	private final Geometry geometry = new RectangleGeometry(Color.RED, 5, 5);

	private final Body body = new Body(new BoxBodyShape(5, 5), 0);

	@Override
	public Geometry getGeometry() {

		return geometry;
	}

	@Override
	public Body getBody() {

		return body;
	}

	public void setPosition(final Vector2f position) {
		body.setPosition(position);
		geometry.setPosition(position);
	}

	public void setAngle(final float angle) {
		body.setAngle(angle);
		geometry.setAngle(angle);
	}

}

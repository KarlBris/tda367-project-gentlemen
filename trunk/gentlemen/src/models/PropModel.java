package models;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Body;
import core.BoxBodyShape;
import core.Geometry;
import core.RectangleGeometry;

public class PropModel implements IModel {

	private final Geometry geometry;

	private final Body body;

	public PropModel(final float width, final float height, final float mass) {

		geometry = new RectangleGeometry(Color.RED, width, height);

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

	public void setPosition(final Vector2f position) {
		body.setPosition(position);
		geometry.setPosition(position);
	}

	public void setAngle(final float angle) {
		body.setAngle(angle);
		geometry.setAngle(angle);
	}

	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
	}

}

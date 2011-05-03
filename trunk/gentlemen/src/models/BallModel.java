package models;

import core.Body;
import core.Geometry;
import core.RectangleGeometry;

public class BallModel implements IModel {

	private Geometry geometry = new RectangleGeometry(utilities.Color.RED,
			0.2f, 0.2f);

	private Body body = new Body(0.2f, 0.2f, 1.0f);

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		return body;
	}

	public void update() {
		geometry.moveTowards(body.getPosition(), body.getAngle());
	}

}

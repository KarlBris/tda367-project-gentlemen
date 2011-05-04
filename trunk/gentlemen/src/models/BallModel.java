package models;

import core.BallGeometry;
import core.Body;
import core.Geometry;
//import core.RectangleGeometry;

public class BallModel implements IModel {

	private final Geometry geometry = new BallGeometry(
			utilities.Color.randomColor(), 0.1f, 8);

	private final Body body = new Body(0.2f, 0.2f, 1.0f);

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
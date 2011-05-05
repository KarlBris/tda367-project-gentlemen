package models;

import core.BallGeometry;
import core.Body;
import core.CircleBodyShape;
import core.Geometry;

public class BallModel implements IModel {

	private final Geometry geometry = new BallGeometry(
			utilities.Color.randomColor(), 0.1f, 8);

	private final Body body = new Body(new CircleBodyShape(0.1f), 1.0f);

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
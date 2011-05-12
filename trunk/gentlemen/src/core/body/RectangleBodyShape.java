package core.body;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;


public class RectangleBodyShape implements IBodyShape {

	private PolygonShape shape = new PolygonShape();

	public RectangleBodyShape(final float width, final float height) {
		shape.setAsBox(width * 0.5f, height * 0.5f);
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}

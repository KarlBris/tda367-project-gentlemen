package model.body;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;


public class CircleBodyShape implements IBodyShape {

	private CircleShape shape = new CircleShape();

	public CircleBodyShape(final float radius) {
		shape.m_radius = radius;
	}

	@Override
	public Shape getShape() {
		return shape;
	}

}

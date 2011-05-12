package core.geometry;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

/**
 * This class represents a nonexistent geometry
 */
public class NullGeometry implements IGeometry {

	private Vector2f[] vertices = {};
	private Vector2f[] uvs = {};

	private Vector2f position = new Vector2f(0.0f, 0.0f);
	private float angle = 0.0f;
	private Vector2f scale = new Vector2f(1.0f, 1.0f);

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public float getAngle() {
		return angle;
	}

	@Override
	public Vector2f getScale() {
		return scale;
	}

	@Override
	public Color getColor() {
		return Color.MAGENTA;
	}

	@Override
	public float getDepth() {
		return 0.0f;
	}

	@Override
	public Vector2f[] getVertices() {
		return vertices;
	}

	@Override
	public Vector2f[] getUvs() {
		return uvs;
	}
}

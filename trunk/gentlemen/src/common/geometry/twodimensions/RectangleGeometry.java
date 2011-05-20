package common.geometry.twodimensions;

import org.lwjgl.util.vector.Vector2f;

import common.geometry.AbstractGeometry;


import utilities.Color;

public final class RectangleGeometry extends AbstractGeometry {

	private final Vector2f[] vertices = {
			new Vector2f(-0.5f, -0.5f), // Top-right triangle
			new Vector2f(0.5f, -0.5f), new Vector2f(0.5f, 0.5f),
			new Vector2f(-0.5f, -0.5f), // Bottom-left triangle
			new Vector2f(0.5f, 0.5f), new Vector2f(-0.5f, 0.5f) };

	private final Vector2f[] uvs = {
			new Vector2f(0.0f, 0.0f), // Top-right triangle
			new Vector2f(1.0f, 0.0f), new Vector2f(1.0f, 1.0f),
			new Vector2f(0.0f, 0.0f), // Bottom-left triangle
			new Vector2f(1.0f, 1.0f), new Vector2f(0.0f, 1.0f) };

	public RectangleGeometry(final Color color, final float depth, final float width,
			final float height) {
		super(color, depth);

		// Set the width and height of the rectangle
		for (final Vector2f v : vertices) {
			v.x *= width;
			v.y *= height;
		}
	}

	@Override
	public boolean isVisible() {
		return true;
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

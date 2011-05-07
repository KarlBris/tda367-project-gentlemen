package core;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

public class BoxGeometry extends Geometry {

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

	public BoxGeometry(final Color color, final float width,
			final float height) {
		super(color);

		// Set the width and height of the rectangle
		for (final Vector2f v : vertices) {
			v.x *= width;
			v.y *= height;
		}

		setVertices(vertices);
		setUvs(uvs);
	}
}

package core.geometry;

import org.lwjgl.util.vector.Vector2f;


import utilities.Color;
import utilities.Constants;

/**
 * This class represents a n-sided convex regular polygon
 * 
 */
public class CircleGeometry extends Geometry {

	private final Vector2f[] vertices;

	private final Vector2f[] uvs;

	public CircleGeometry(final Color color, final float depth,
			final float radius, final int sides) {
		super(color, depth);

		vertices = new Vector2f[sides * 3];
		uvs = new Vector2f[sides * 3];

		float piece = Constants.TWO_PI / sides;

		int vertexCounter = 0;
		for (int i = 0; i < sides; i++) {

			// Middle piece of triangle
			vertices[vertexCounter++] = new Vector2f(0.0f, 0.0f);

			// Second piece of triangle
			vertices[vertexCounter++] = new Vector2f(
					(float) (radius * Math.cos(piece * i)),
					(float) (radius * (-Math.sin(piece * i))));

			// Third piece of triangle
			vertices[vertexCounter++] = new Vector2f(
					(float) (radius * Math.cos(piece * (i + 1))),
					(float) (radius * (-Math.sin(piece * (i + 1)))));

		}

		// Initialize empty uv Vectors
		for (int i = 0; i < sides * 3; i++) {
			uvs[i] = new Vector2f();
		}

		setVertices(vertices);
		setUvs(uvs);
	}

}

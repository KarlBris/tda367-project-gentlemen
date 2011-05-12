package core.geometry.twodimensions;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.geometry.AbstractGeometry;

/**
 * ReticleGeometry represents the reticle geometry.
 * 
 */
public class ReticleGeometry extends AbstractGeometry {
	private static float length = 0.4f;
	private static float width = 0.05f;
	private static float distance = 0.1f;

	// Sets the coordinates of the reticle geometry
	private static Vector2f[] vertices = {
			new Vector2f(0.0f, -distance), // Top triangle
			new Vector2f(-width, -length),
			new Vector2f(width, -length),
			new Vector2f(distance, -0.0f), // Right triangle
			new Vector2f(length, -width),
			new Vector2f(length, width),
			new Vector2f(0.0f, distance), // Bottom triangle
			new Vector2f(width, length), new Vector2f(-width, length),
			new Vector2f(-distance, -0.0f), // Left triangle
			new Vector2f(-length, width), new Vector2f(-length, -width) };

	// Sets the texture coordinates of the reticle geometry
	private static Vector2f[] uvs = { new Vector2f(), new Vector2f(),
			new Vector2f(), new Vector2f(), new Vector2f(), new Vector2f(),
			new Vector2f(), new Vector2f(), new Vector2f(), new Vector2f(),
			new Vector2f(), new Vector2f() };

	public ReticleGeometry() {
		super(Color.IT, 0.0f);
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

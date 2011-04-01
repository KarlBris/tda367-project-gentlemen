package core;

import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public class RectangleGeometry extends Geometry {
	
	private static Vector2f[] vertices = {	new Vector2f(-1.0f, 1.0f), // Top-right triangle
											new Vector2f(1.0f, 1.0f),
											new Vector2f(1.0f, -1.0f),
											new Vector2f(-1.0f, 1.0f), // Bottom-left triangle
											new Vector2f(1.0f, -1.0f),
											new Vector2f(-1.0f, -1.0f) };
	
	private static Vector2f[] uvs = {	new Vector2f(0.0f, 0.0f), // Top-right triangle
										new Vector2f(1.0f, 0.0f),
										new Vector2f(1.0f, 1.0f),
										new Vector2f(0.0f, 0.0f), // Bottom-left triangle
										new Vector2f(1.0f, 1.0f),
										new Vector2f(0.0f, 1.0f) };
	
	public RectangleGeometry(Color color) {
		super(color, vertices, uvs);
	}
}

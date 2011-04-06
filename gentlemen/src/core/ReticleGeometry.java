package core;

import org.lwjgl.util.vector.Vector2f;

public class ReticleGeometry extends Geometry {
	private static float lenght = 0.4f;
	private static float width = 0.05f;
	private static float distance = 0.1f;
	
	

	private static Vector2f[] verticies = { new Vector2f(0.0f, -distance),  // Top triangle
											new Vector2f(-width, -lenght),
											new Vector2f(width, -lenght),
											new Vector2f(distance, -0.0f),  // Right triangle
											new Vector2f(lenght, -width),
											new Vector2f(lenght, width),
											new Vector2f(0.0f, distance),  // Bottom triangle
											new Vector2f(width, lenght),
											new Vector2f(-width, lenght),
											new Vector2f(-distance, -0.0f),  // Left triangle
											new Vector2f(-lenght, width),
											new Vector2f(-lenght, -width)};
	
	private static Vector2f[] uvs = 	{   new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f(),
											new Vector2f()};
	
	
	public ReticleGeometry() {
		super(null, verticies, uvs);
	}
	
}

package core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public abstract class Geometry {
	private Color color;
	private Vector2f[] vertices;
	private Vector2f[] uvs;
	
	public Geometry(Color color, Vector2f[] vertices, Vector2f[] uvs) {
		this.color = color;
		this.vertices = vertices;
		this.uvs = uvs;
	}
	
	public void render() {
		GL11.glColor3b((byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
		
		// Render the geometry as triangles
		for (Vector2f vertex : vertices) {
			GL11.glVertex2f(vertex.x, vertex.y);
		}
		
		for (Vector2f uv : uvs) {
			GL11.glTexCoord2f(uv.x, uv.y);
		}
	}
}

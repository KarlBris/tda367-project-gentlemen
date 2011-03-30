package core;
import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

public class Geometry {
	private Color color;
	private Vector2f[] vertices;
	private Vector2f[] uvs;
	
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

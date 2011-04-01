package core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public abstract class Geometry {
	
	private Vector2f position;
	private float angle;
	
	private Color color;
	private Vector2f[] vertices;
	private Vector2f[] uvs;
	
	public Geometry(Color color, Vector2f[] vertices, Vector2f[] uvs) {
		this.color = color;
		this.vertices = vertices;
		this.uvs = uvs;
	}
	
	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Vector2f getPosition() {
		return position;
	}
	
	public void setAngle(float angle) {
		this.angle = angle % Constants.TWO_PI;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void moveTowards(Vector2f targetPosition, float targetAngle) {
		// Move position towards the targetPosition
		Vector2f delta = new Vector2f();
		Vector2f.sub(targetPosition, getPosition(), delta);
		delta.scale(0.1f);
		
		Vector2f movement = new Vector2f();
		Vector2f.add(getPosition(), delta, movement);
		
		Vector2f newPosition = new Vector2f();
		Vector2f.add(position, movement, newPosition);
		
		setPosition(newPosition);
		
		// Move angle towards targetAngle
		float directDelta = targetAngle - angle;
		float indirectDelta = -Math.signum(directDelta) * (Constants.TWO_PI - directDelta);
		
		float angleMovement = 0.0f;
		
		if (Math.abs(directDelta) < Math.abs(indirectDelta)) {
			angleMovement = directDelta;
		}
		else {
			angleMovement = indirectDelta;
		}
		
		setAngle(getAngle() + angleMovement);
	}
	
	public void render() {
		
		// Set color
		GL11.glColor3b(color.getRedByte(), color.getGreenByte(), color.getBlueByte());
		
		// Render the geometry as triangles
		for (Vector2f vertex : vertices) {
			GL11.glVertex2f(vertex.x, vertex.y);
		}
		
		for (Vector2f uv : uvs) {
			GL11.glTexCoord2f(uv.x, uv.y);
		}
	}
}
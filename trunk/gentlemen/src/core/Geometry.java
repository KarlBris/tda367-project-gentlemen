package core;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.vector.Vector2f;

public abstract class Geometry {
	
	private Vector2f position = new Vector2f();
	private float angle;
	
	private Color color;
	private Vector2f[] vertices;
	private Vector2f[] uvs;
	
	public Geometry(Color color) {
		this.color = color;
	}
	
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
		angle = angle % Constants.TWO_PI;
		
		if (angle < 0) {
			angle = Constants.TWO_PI + angle;
		}
		
		this.angle = angle;
	}
	
	public float getAngle() {
		return angle;
	}
	
	public void setVertices(Vector2f[] vertices) {
		this.vertices = vertices;
	}
	
	public void setUvs(Vector2f[] uvs) {
		this.uvs = uvs;
	}
	
	public void moveTowards(Vector2f targetPosition, float targetAngle) {
		// Move position towards the targetPosition
		Vector2f delta = new Vector2f();
		Vector2f.sub(targetPosition, getPosition(), delta);
		delta.scale(Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);
		
		Vector2f newPosition = new Vector2f();
		Vector2f.add(getPosition(), delta, newPosition);
		
		setPosition(newPosition);
		
		// Move angle towards targetAngle
		float directDelta = targetAngle - angle;
		float indirectDelta = -Math.signum(directDelta) * (Constants.TWO_PI - Math.abs(directDelta));
		
		float angleMovement = 0.0f;
		
		if (Math.abs(directDelta) < Math.abs(indirectDelta)) {
			angleMovement = directDelta;
		}
		else {
			angleMovement = indirectDelta;
		}
		
		setAngle(getAngle() + angleMovement * Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);
	}
	
	public void render() {
		
		// Set color
		GL11.glColor3f(1.0f, 1.0f, 1.0f); // TODO Solve color issue; glColor3b does not seem to work -> create our own color class that uses float
		
		// Set position and angle
		GL11.glLoadIdentity();
		GL11.glTranslatef(position.x, position.y, 0.0f);
		GL11.glRotatef(angle * Constants.TO_DEGREES, 0.0f, 0.0f, -1.0f);
		
		// Begin rendering triangles
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// Render the geometry as triangles
		for (Vector2f vertex : vertices) {
			GL11.glVertex2f(vertex.x, vertex.y);
		}
		
		for (Vector2f uv : uvs) {
			GL11.glTexCoord2f(uv.x, uv.y);
		}
		
		// End rendering triangles
		GL11.glEnd();
	}
}
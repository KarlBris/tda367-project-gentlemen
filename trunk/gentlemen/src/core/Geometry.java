package core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;

public abstract class Geometry {

	private Vector2f position = new Vector2f();
	private float angle;

	private final Color color;
	private Vector2f[] vertices;
	private Vector2f[] uvs;

	public Geometry(final Color color) {
		this.color = color;
	}

	public Geometry(final Color color, final Vector2f[] vertices,
			final Vector2f[] uvs) {
		this.color = color;
		this.vertices = vertices;
		this.uvs = uvs;
	}

	/**
	 * Sets the position of the geometry
	 * 
	 * @param position
	 */
	public void setPosition(final Vector2f position) {
		this.position = position;
	}

	/**
	 * @return the position of the geometry
	 */
	public Vector2f getPosition() {
		return position;
	}

	/**
	 * Sets the angle of the geometry
	 * 
	 * @param angle
	 *            in radians
	 */
	public void setAngle(final float angle) {
		this.angle = Tools.wrapAngle(angle);
	}

	/**
	 * @return the angle of the geometry
	 */
	public float getAngle() {
		return angle;
	}

	/**
	 * Sets the vertices of the geometry
	 * 
	 * @param vertices
	 */
	public void setVertices(final Vector2f[] vertices) {
		this.vertices = vertices;
	}

	/**
	 * Sets the uvs of the geometry
	 * 
	 * @param uvs
	 */
	public void setUvs(final Vector2f[] uvs) {
		this.uvs = uvs;
	}

	/**
	 * Moves the geometry towards a target. The speed is controlled by the
	 * GEOMETRY_TO_PHYSICS_INTERPOLATION constant.
	 * 
	 * @param targetPosition
	 *            the target position
	 * @param targetAngle
	 *            the target angle
	 */
	public void moveTowards(final Vector2f targetPosition,
			final float targetAngle) {
		// Move position towards the targetPosition
		final Vector2f delta = new Vector2f();
		Vector2f.sub(targetPosition, getPosition(), delta);
		delta.scale(Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);

		final Vector2f newPosition = new Vector2f();
		Vector2f.add(getPosition(), delta, newPosition);

		setPosition(newPosition);

		// Move angle towards targetAngle
		float angleDelta = Tools.closestAngleDelta(getAngle(), targetAngle);

		setAngle(getAngle() + angleDelta
				* Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);
	}

	/**
	 * Renders the geometry
	 */
	public void render() {

		// Set color
		GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());

		// Set position and angle
		GL11.glLoadIdentity();
		GL11.glTranslatef(position.x, position.y, 0.0f);
		GL11.glRotatef(angle * Constants.TO_DEGREES, 0.0f, 0.0f, -1.0f);

		// Begin rendering triangles
		GL11.glBegin(GL11.GL_TRIANGLES);

		// Render the geometry as triangles
		for (final Vector2f vertex : vertices) {
			GL11.glVertex2f(vertex.x, vertex.y);
		}

		for (final Vector2f uv : uvs) {
			GL11.glTexCoord2f(uv.x, uv.y);
		}

		// End rendering triangles
		GL11.glEnd();
	}
}
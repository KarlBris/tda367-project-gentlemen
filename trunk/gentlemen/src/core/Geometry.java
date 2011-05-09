package core;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

public abstract class Geometry {

	private Vector2f[] vertices;
	private Vector2f[] uvs;

	private Vector2f position = new Vector2f();
	private float angle = 0.0f;
	private Vector2f scale = new Vector2f(1.0f, 1.0f);

	private Color color;
	private float depth;

	public Geometry() {
	}

	public Geometry(final Color color, final float depth) {
		this.color = color;
		this.depth = depth;
	}

	public Geometry(final Color color, final float depth,
			final Vector2f[] vertices, final Vector2f[] uvs) {
		this(color, depth);

		this.vertices = vertices;
		this.uvs = uvs;
	}

	/**
	 * @return the position of the geometry
	 */
	public Vector2f getPosition() {
		return new Vector2f(position);
	}

	/**
	 * Sets the position of the geometry
	 * 
	 * @param position
	 */
	public void setPosition(final Vector2f position) {
		this.position = new Vector2f(position);
	}

	/**
	 * @return the angle of the geometry
	 */
	public float getAngle() {
		return angle;
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
	 * @return the scale of the geometry
	 */
	public Vector2f getScale() {
		return new Vector2f(scale);
	}

	/**
	 * Sets the scale of the geometry
	 * 
	 * @param scale
	 */
	public void setScale(final Vector2f scale) {
		this.scale = new Vector2f(scale);
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
	 * @return the color of the geometry
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the geometry
	 * 
	 * @param color
	 *            the new color of the geometry
	 */
	public void setColor(final Color color) {
		this.color = color;
	}

	/**
	 * Moves the geometry towards a target.
	 * 
	 * @param targetPosition
	 *            the target position
	 * @param targetAngle
	 *            the target angle
	 * @param positionAmount
	 *            the amount to move the position
	 * @param angleAmount
	 *            the amount to move the angle
	 */
	public void moveTowards(final Vector2f targetPosition,
			final float targetAngle, final float positionAmount,
			final float angleAmount) {
		// Move position towards the targetPosition
		final Vector2f delta = new Vector2f();
		Vector2f.sub(targetPosition, getPosition(), delta);
		delta.scale(positionAmount);

		final Vector2f newPosition = new Vector2f();
		Vector2f.add(getPosition(), delta, newPosition);

		setPosition(newPosition);

		// Move angle towards targetAngle
		final float angleDelta = Tools.closestAngleDelta(getAngle(),
				targetAngle);

		setAngle(getAngle() + angleDelta * angleAmount);
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
		moveTowards(targetPosition, targetAngle,
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION,
				Constants.GEOMETRY_TO_PHYSICS_INTERPOLATION);
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
		GL11.glScalef(scale.x, scale.y, 1.0f);

		// Begin rendering triangles
		GL11.glBegin(GL11.GL_TRIANGLES);

		// Render the geometry as triangles
		for (final Vector2f vertex : vertices) {
			GL11.glVertex3f(vertex.x, vertex.y, depth);
		}

		for (final Vector2f uv : uvs) {
			GL11.glTexCoord2f(uv.x, uv.y);
		}

		// End rendering triangles
		GL11.glEnd();
	}
}
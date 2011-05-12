package core.geometry;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

public interface IGeometry {

	public boolean isVisible();

	public Vector2f getPosition();

	public float getAngle();

	public Vector2f getScale();

	/**
	 * @return the color of the geometry
	 */
	public Color getColor();

	/**
	 * @return the depth of the geometry in the game world
	 */
	public float getDepth();

	public Vector2f[] getVertices();

	public Vector2f[] getUvs();

}

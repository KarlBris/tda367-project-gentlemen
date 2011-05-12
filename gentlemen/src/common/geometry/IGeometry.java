package common.geometry;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

/**
 * The base for all geometry. All implementations of IView use this.
 */
public interface IGeometry {

	/**
	 * @return true if the geometry instance is visible, false otherwise
	 */
	public boolean isVisible();

	/**
	 * @return the position of the geometry
	 */
	public Vector2f getPosition();

	/**
	 * @return the angle of the geometry
	 */
	public float getAngle();

	/**
	 * @return the scale of the geometry
	 */
	public Vector2f getScale();

	/**
	 * @return the color of the geometry
	 */
	public Color getColor();

	/**
	 * @return the depth of the geometry in the game world
	 */
	public float getDepth();

	/**
	 * @return the vertices of the geometry
	 */
	public Vector2f[] getVertices();

	/**
	 * @return the texture coordinates of the geometry
	 */
	public Vector2f[] getUvs();

}

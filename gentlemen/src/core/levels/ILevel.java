package core.levels;

import org.lwjgl.util.vector.Vector2f;

/**
 * This interface specifies a Level object
 */
public interface ILevel {

	/**
	 * This method is called by LevelManager after instantiating the permanent
	 * entities from the levelString
	 */
	public void instantiateProps();

	/**
	 * Returns the levelString of the level
	 */
	public String getLevelString();

	/**
	 * Returns the spawn position of the balls
	 */
	public Vector2f getBallSpawnPosition();

	/**
	 * Returns the home position of team One
	 */
	public Vector2f getTeamOneHomePosition();

	/**
	 * Returns the home position of team Two
	 */
	public Vector2f getTeamTwoHomePosition();

	/**
	 * Sets the position of the initial ball spawn point
	 */
	public void setBallSpawnPosition(Vector2f position);

	/**
	 * Sets the home position of the first team
	 */
	public void setTeamOneHomePosition(Vector2f position);

	/**
	 * Sets the home position of the second team
	 */
	public void setTeamTwoHomePosition(Vector2f position);
}

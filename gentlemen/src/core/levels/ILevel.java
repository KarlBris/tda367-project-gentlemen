package core.levels;

import org.lwjgl.util.vector.Vector2f;

/**
 * This abstract class represents a level in the game
 */
public interface ILevel {

	// Eventually different score limits, point gains and (again eventually)
	// abilities

//	public AbstractLevel(final Vector2f teamOneHomePosition,
//			final Vector2f teamTwoHomePosition, final Vector2f ballSpawnPosition) {
//
//		this.teamOneHomePosition = teamOneHomePosition;
//		this.teamTwoHomePosition = teamTwoHomePosition;
//
//		this.ballSpawnPosition = ballSpawnPosition;
//	}


	
	public void instantiateProps();
	
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
	
	public void setBallSpawnPosition(Vector2f position);
	
	public void setTeamOneHomePosition(Vector2f position);
	
	public void setTeamTwoHomePosition(Vector2f position);
}

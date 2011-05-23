package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.HorizontalWallPropFactory;
import factories.entities.VerticalWallPropFactory;

/**
 * This abstract class represents a level in the game
 */
public abstract class SuperLevel implements ILevel {

	private final IMainController main = MainControllerFactory.get();

	private Vector2f teamOneHomePosition;
	private Vector2f teamTwoHomePosition;

	private Vector2f ballSpawnPosition;
	
	private String levelString;

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

	
	public SuperLevel(String levelString) {
		this.levelString = levelString;
	}
	
	/**
	 * Initializes the four walls that surround the game arena
	 */
	public void instatiateWalls() {
		// Top wall
		main.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT));

		// Bottom wall
		main.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, 0.0f));

		// Left wall
		main.instantiate(new VerticalWallPropFactory(), new Vector2f(0.0f,
				Constants.VIEWPORT_HEIGHT / 2));

		// Right wall
		main.instantiate(new VerticalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT / 2));
	}
	
	public String getLevelString() {
		return levelString;
	}
	
	/**
	 * Returns the spawn position of the balls
	 */
	public Vector2f getBallSpawnPosition() {
		return ballSpawnPosition;
	}

	/**
	 * Returns the home position of team One
	 */
	public Vector2f getTeamOneHomePosition() {
		return teamOneHomePosition;
	}

	/**
	 * Returns the home position of team Two
	 */
	public Vector2f getTeamTwoHomePosition() {
		return teamTwoHomePosition;
	}
	
	public void setBallSpawnPosition(Vector2f position) {
		ballSpawnPosition = position;
	}
	
	public void setTeamOneHomePosition(Vector2f position) {
		teamOneHomePosition = position;
	}
	
	public void setTeamTwoHomePosition(Vector2f position) {
		teamTwoHomePosition = position;
	}
}
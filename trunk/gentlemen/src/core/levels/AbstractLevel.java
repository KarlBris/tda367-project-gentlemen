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
public abstract class AbstractLevel implements ILevel {

	private final IMainController main = MainControllerFactory.get();

	private Vector2f teamOneHomePosition;
	private Vector2f teamTwoHomePosition;

	private Vector2f ballSpawnPosition;

	private final String levelString;

	public AbstractLevel(final String levelString) {
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

	@Override
	public String getLevelString() {
		return levelString;
	}

	@Override
	public Vector2f getBallSpawnPosition() {
		return ballSpawnPosition;
	}

	@Override
	public Vector2f getTeamOneHomePosition() {
		return teamOneHomePosition;
	}

	@Override
	public Vector2f getTeamTwoHomePosition() {
		return teamTwoHomePosition;
	}

	@Override
	public void setBallSpawnPosition(final Vector2f position) {
		ballSpawnPosition = position;
	}

	@Override
	public void setTeamOneHomePosition(final Vector2f position) {
		teamOneHomePosition = position;
	}

	@Override
	public void setTeamTwoHomePosition(final Vector2f position) {
		teamTwoHomePosition = position;
	}
}

package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.HorizontalWallPropFactory;
import factories.entities.VerticalWallPropFactory;

public abstract class AbstractLevel {

	private final IMainController main = MainControllerFactory.get();

	private final Vector2f teamOneHomePosition;
	private final Vector2f teamTwoHomePosition;

	private final Vector2f ballSpawnPosition;

	// Eventually different score limits, point gains and (again eventually)
	// abilities

	public AbstractLevel(final Vector2f teamOneHomePosition,
			final Vector2f teamTwoHomePosition, final Vector2f ballSpawnPosition) {

		this.teamOneHomePosition = teamOneHomePosition;
		this.teamTwoHomePosition = teamTwoHomePosition;

		this.ballSpawnPosition = ballSpawnPosition;
	}

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

	public Vector2f getBallSpawnPosition() {
		return ballSpawnPosition;
	}

	public Vector2f getTeamOneHomePosition() {
		return teamOneHomePosition;
	}

	public Vector2f getTeamTwoHomePosition() {
		return teamTwoHomePosition;
	}
}

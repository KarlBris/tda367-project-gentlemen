package controller.components.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import core.Manager;
import factories.entities.HorizontalWallPropFactory;
import factories.entities.VerticalWallPropFactory;

public abstract class AbstractLevel {

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
		Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT));

		// Bottom wall
		Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, 0.0f));

		// Left wall
		Manager.instantiate(new VerticalWallPropFactory(), new Vector2f(0.0f,
				Constants.VIEWPORT_HEIGHT / 2));

		// Right wall
		Manager.instantiate(new VerticalWallPropFactory(), new Vector2f(
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

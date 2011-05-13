package controller.entities;

import model.entities.ScoreboardModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

/**
 * This class controls a scoreboard model
 */
public class ScoreboardController implements IController<ScoreboardModel> {

	public ScoreboardModel model;

	public ScoreboardController(final ScoreboardModel model) {
		this.model = model;
	}

	/**
	 * Adds a team to the scoreboard for display
	 * 
	 * @param team
	 *            the team to add to the scoreboard
	 */
	public void addTeam(final TeamController team) {
		model.addTeam(team);
	}

	@Override
	public ScoreboardModel getModel() {
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
		// Do nothing
	}

	@Override
	public void start() {
		// Do nothing
	}

	@Override
	public void end() {
		// Do nothing
	}

	@Override
	public void update() {
		model.update();
	}

}

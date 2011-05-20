package controller.entities;

import java.util.LinkedList;
import java.util.List;

import model.entities.ScoreboardModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

/**
 * This class controls a scoreboard model
 */
public final class ScoreboardController implements IController<ScoreboardModel> {

	private final ScoreboardModel model;

	private final List<TeamController> teamList = new LinkedList<TeamController>();

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
		teamList.add(team);
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

		StringBuilder stringBuilder = new StringBuilder();

		for (final TeamController t : teamList) {
			stringBuilder.append(t.getTeamName() + ": " + t.getScore() + " ");
		}

		model.setText(stringBuilder.toString());
	}

}

package controller.entities;

import model.entities.IModel;
import model.entities.ScoreboardModel;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class controls a scoreboard model
 */
public class ScoreboardController implements IController {

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
	public IModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		model.update();

	}

}

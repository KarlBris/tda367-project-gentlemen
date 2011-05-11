package controllers;

import models.IModel;
import models.ScoreboardModel;

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

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void setPosition(final Vector2f position) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#end()
	 */
	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		model.update();

	}

	/**
	 * @see controllers.IController#networkDataSend()
	 */
	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see controllers.IController#networkDataReceive(java.lang.Object[])
	 */
	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}
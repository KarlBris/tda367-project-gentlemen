package controllers;

import models.IModel;
import models.TeamModel;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class controls a team model
 */
public class TeamController implements IController {

	private final TeamModel model;

	public TeamController(final TeamModel model) {
		this.model = model;
	}

	/**
	 * Adds an amount of score points to the team
	 * 
	 * @param amount
	 *            the amount of score points to be added
	 */
	public void addScore(final int amount) {
		model.addScore(amount);
	}

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
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
		// TODO Auto-generated method stub

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

	/**
	 * Returns the score of the team
	 * 
	 * @return the score of the team
	 */
	public int getScore() {
		return model.getScore();
	}

	/**
	 * Returns the name of the team
	 * 
	 * @return the name of the team
	 */
	public String getTeamName() {
		return model.getTeamName();
	}

	/**
	 * Sets the name of the team
	 * 
	 * @param name
	 *            the name to be set
	 */
	public void setTeamName(final String name) {
		model.setTeamName(name);
	}

	/**
	 * Sets the home position of the team
	 * 
	 * @param position
	 *            the position to be set as home position
	 */
	public void setHomePosition(final Vector2f position) {
		model.setHomePosition(position);
	}

	/**
	 * Returns the home position of the team
	 * 
	 * @return the home position of the team
	 */
	public Vector2f getHomePosition() {
		return model.getHomePosition();
	}

	/**
	 * Sets the rules the team will adhere to
	 * 
	 * @param rules
	 *            the rules to set
	 */
	public void setRules(final RuleController rules) {
		model.setRules(rules);
	}
}

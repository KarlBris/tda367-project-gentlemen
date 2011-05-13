package controller.entities;

import model.common.IModel;
import model.entities.TeamModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

/**
 * This class controls a team model
 */
public class TeamController implements IController {

	private final TeamModel model;

	public TeamController(final TeamModel model) {
		this.model = model;
	}

	/**
	 * Adds an amount of score points to the team score
	 * 
	 * @param amount
	 *            the amount of score points to be added
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void addScore(final int amount) throws NumberFormatException {
		model.addScore(amount);
	}

	/**
	 * Subtracts an amount of score points from the team score
	 * 
	 * @param amount
	 *            the amount of score points to subtract
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void subtractScore(final int amount) throws NumberFormatException {
		model.subtractScore(amount);
	}

	@Override
	public IModel getModel() {
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

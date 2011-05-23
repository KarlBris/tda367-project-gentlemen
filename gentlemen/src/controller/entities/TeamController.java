package controller.entities;

import model.entities.TeamModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import controller.common.IController;

/**
 * This class controls a team model
 */
public final class TeamController implements IController<TeamModel> {

	private final TeamModel model;

	private RuleController ruleController;

	public boolean hasWon = false;

	public TeamController(final TeamModel model) {
		this.model = model;
	}

	/**
	 * Add an amount to the team score
	 * 
	 * @param amount
	 *            is the amount to be added to the team score
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void addScore(final int amount) throws NumberFormatException {
		model.addScore(amount);

		// Check with rules. If won, celebrate!
		hasWon = ruleController.checkVictory(getScore(), getTeamName());

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
	public TeamModel getModel() {
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
	 * Set the rules the team will follow
	 * 
	 * @param rules
	 *            the rules to follow
	 */
	public void setRules(final RuleController rules) {
		ruleController = rules;
	}

	/**
	 * 
	 * @return the rules controller
	 */
	public RuleController getRules() {
		return ruleController;
	}

	public Color getColor() {
		return model.getColor();
	}

	public void setColor(final Color color) {
		model.setColor(color);
	}

}

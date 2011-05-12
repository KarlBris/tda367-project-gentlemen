package model.entities;

import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.entities.RuleController;
import core.body.Body;
import core.geometry.Geometry;
import core.geometry.NullGeometry;

/**
 * Represents a team in the game
 */
public class TeamModel implements IModel {

	private Vector2f homePosition;

	private String teamName;

	private RuleController ruleController;

	private final Geometry geometry = new NullGeometry();

	private int totalScore = 0;

	/**
	 * Add an amount to the team score
	 * 
	 * @param amount
	 *            , is the amount to be added to the team score
	 */
	public void addScore(final int amount) {
		totalScore += amount;

		// Check with rules. If won, celebrate!
		ruleController.checkVictory(this);
	}

	/**
	 * Add an amount to the team score, without checking if the team won.
	 * 
	 * @param amount
	 *            , is the amount to be added to the team score
	 */
	public void addPoints(final int amount) {
		totalScore += amount;

	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the team score
	 */
	public int getScore() {
		return totalScore;
	}

	/**
	 * Sets the team name
	 * 
	 * @param name
	 *            the name the team will be given
	 */
	public void setTeamName(final String name) {
		teamName = name;
	}

	/**
	 * Gets the team name
	 * 
	 * @return the name of the team
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Sets the home position of the team. This will be used by players and
	 * flags alike
	 * 
	 * @param position
	 *            the home position of the team
	 */
	public void setHomePosition(final Vector2f position) {
		homePosition = position;
	}

	/**
	 * @return the home position of the team
	 */
	public Vector2f getHomePosition() {
		return Tools.cloneVector(homePosition);
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

	public RuleController getRules() {
		return ruleController;
	}

}

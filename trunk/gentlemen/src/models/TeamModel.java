package models;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import controllers.RuleController;
import core.Body;
import core.Geometry;
import core.Manager;
import core.NullGeometry;

/**
 * Represents a team in the game
 */
public class TeamModel implements IModel {

	private Vector2f homePosition;

	private String teamName;

	private final RuleController ruleController;

	private final Geometry geometry = new NullGeometry();

	private int totalScore = 0;

	public TeamModel() {

		final List<RuleController> list = Manager.find(RuleController.class);
		if (list.size() > 0) {
			ruleController = list.get(0);
		} else {
			ruleController = null;
		}
	}

	/**
	 * Add an amount to the team score
	 * 
	 * @param amount
	 *            , is the amount to be added to the team score
	 */
	public void addScore(final int amount) {
		totalScore += amount;

		// Check with rules. If won, celebrate!
		ruleController.checkVictory(totalScore, this);
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
	 * 
	 * @return the team score
	 */
	public int getScore() {
		return totalScore;
	}

	public void setTeamName(final String name) {
		teamName = name;
	}

	public String getTeamName() {
		return teamName;
	}

}

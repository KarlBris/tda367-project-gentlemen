package model.entities;

import model.common.IModel;
import common.body.Body;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

import utilities.Constants;

/**
 * 
 * Represents a given set of rules in the game. Is responsible for determining
 * whether a scoring team has won or not
 * 
 */
public class RuleModel implements IModel {

	private final int scoreLimit = Constants.SCORE_LIMIT;

	private final IGeometry geometry = new NullGeometry();

	/**
	 * Checks if the given team has won or not
	 * 
	 * @param team
	 *            the team to check for victory
	 */
	public boolean checkVictory(final TeamModel team) {
		if (team.getScore() >= scoreLimit) {
			// AW YEAH, TEAM HAS WON!
			// Do something related to this

			return true;
		}
		return false;
	}

	public int getScoreLimit() {
		return scoreLimit;
	}

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	/**
	 * returns null, since the rules does not have a body
	 */
	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}
package model.entities;

import utilities.Constants;
import core.body.Body;
import core.geometry.Geometry;
import core.geometry.NullGeometry;

/**
 * 
 * Represents a given set of rules in the game. Is responsible for determining
 * whether a scoring team has won or not
 * 
 */
public class RuleModel implements IModel {

	private final int scoreLimit = Constants.SCORE_LIMIT;

	private final Geometry geometry = new NullGeometry();

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
	public Geometry getGeometry() {
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

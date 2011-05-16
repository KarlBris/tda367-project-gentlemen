package model.entities;

import model.common.IModel;
import utilities.Constants;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

/**
 * 
 * Represents a given set of rules in the game. Is responsible for determining
 * whether a scoring team has won or not
 * 
 */
public class RuleModel implements IModel {

	private final int scoreLimit = Constants.SCORE_LIMIT;

	private final IGeometry geometry = new NullGeometry();
	private final IBody body = new NullBody();

	/**
	 * Checks if the given team has won or not
	 * 
	 * @param team
	 *            the team to check for victory
	 */
	public boolean checkVictory(final int score, final String teamName) {
		if (score >= scoreLimit) {
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
	public IBody getBody() {
		// TODO Auto-generated method stub
		return body;
	}

}

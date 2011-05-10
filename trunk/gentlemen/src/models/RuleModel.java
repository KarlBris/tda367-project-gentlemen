package models;

import javax.swing.JOptionPane;

import core.Body;
import core.Geometry;
import core.NullGeometry;

/**
 * 
 * Represents a given set of rules in the game. Is responsible for determining
 * whether a scoring team has won or not
 * 
 */
public class RuleModel implements IModel {

	private final int scoreLimit;

	private final Geometry geometry = new NullGeometry();

	public RuleModel(final int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	/**
	 * Checks if the given team has won or not
	 * 
	 * @param team
	 *            the team to check for victory
	 */
	public void checkVictory(final TeamModel team) {
		if (team.getScore() >= scoreLimit) {
			// AW YEAH, TEAM HAS WON!
			// Do something related to this

			JOptionPane.showMessageDialog(null, team.getTeamName()
					+ " has won!");
		}
	}

	/**
	 * @see models.IModel#getGeometry()
	 */
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

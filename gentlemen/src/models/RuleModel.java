package models;

import core.Body;
import core.Geometry;
import core.NullGeometry;

public class RuleModel implements IModel {

	private final int scoreLimit;

	private final Geometry geometry = new NullGeometry();

	public RuleModel(final int scoreLimit) {
		this.scoreLimit = scoreLimit;
	}

	public void checkVictory(final int score, final int teamIndex) {
		if (score >= scoreLimit) {
			// AW YEAH, TEAM HAS WON!
			// Do something related to this
			System.out.println("Team " + teamIndex + " has won!");
		}
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

}

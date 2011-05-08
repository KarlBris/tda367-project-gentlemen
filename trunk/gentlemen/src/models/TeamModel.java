package models;

import org.lwjgl.util.vector.Vector2f;

import controllers.RuleController;
import controllers.TeamController;
import core.Body;
import core.Geometry;
import core.Manager;
import core.NullGeometry;

public class TeamModel implements IModel {

	private Vector2f homePosition;

	private final int teamIndex;

	private final RuleController ruleController;

	private final Geometry geometry = new NullGeometry();

	private int totalScore = 0;

	public TeamModel() {
		teamIndex = Manager.find(TeamController.class).size() + 1;

		ruleController = Manager.find(RuleController.class).get(0);
	}

	public void addScore(final int amount) {
		totalScore += amount;

		// Check with rules. If won, celebrate!
		ruleController.checkVictory(totalScore, teamIndex);
	}

	public int getTeamIndex() {
		return teamIndex;
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

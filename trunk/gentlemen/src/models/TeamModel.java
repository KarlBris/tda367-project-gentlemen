package models;

import java.util.List;

import org.lwjgl.util.vector.Vector2f;

import controllers.RuleController;
import core.Body;
import core.Geometry;
import core.Manager;
import core.NullGeometry;

public class TeamModel implements IModel {

	private Vector2f homePosition;

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

	public int getScore() {
		return totalScore;
	}

}

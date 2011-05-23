package controller.entities;

import model.entities.RuleModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

/**
 * This class controls a rule model
 */
public final class RuleController implements IController<RuleModel> {

	private final RuleModel model;

	public RuleController(final RuleModel model) {
		this.model = model;
	}

	/**
	 * Checks if a given team has won based on its total score
	 * 
	 * @param team
	 *            the team to check for victory
	 * @return
	 */
	public boolean checkVictory(final int score, final String teamName) {
		return model.checkVictory(score, teamName);
	}

	@Override
	public RuleModel getModel() {
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

}

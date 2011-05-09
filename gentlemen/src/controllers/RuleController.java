package controllers;

import models.IModel;
import models.RuleModel;
import models.TeamModel;

import org.lwjgl.util.vector.Vector2f;

public class RuleController implements IController {

	private final RuleModel model;

	public RuleController(final RuleModel model) {
		this.model = model;
	}

	public void checkVictory(final int score, final TeamModel team) {
		model.checkVictory(team);
	}

	@Override
	public IModel getModel() {
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

	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}

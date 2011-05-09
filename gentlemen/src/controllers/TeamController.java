package controllers;

import models.IModel;
import models.TeamModel;

import org.lwjgl.util.vector.Vector2f;

public class TeamController implements IController {

	private final TeamModel model;

	public TeamController(final TeamModel model) {
		this.model = model;
	}

	public void addScore(final int amount) {
		model.addScore(amount);
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

	public int getScore() {
		return model.getScore();
	}

	public String getTeamName() {
		return model.getTeamName();
	}

	public void setTeamName(final String name) {
		model.setTeamName(name);
	}
}

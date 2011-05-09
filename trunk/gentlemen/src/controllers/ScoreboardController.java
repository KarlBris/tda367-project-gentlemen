package controllers;

import models.IModel;
import models.ScoreboardModel;

import org.lwjgl.util.vector.Vector2f;

public class ScoreboardController implements IController {

	public ScoreboardModel model;

	public ScoreboardController(final ScoreboardModel model) {
		this.model = model;
	}

	public void addTeam(final TeamController team) {
		model.addTeam(team);
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
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
		model.update();

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

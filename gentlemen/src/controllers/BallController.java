package controllers;

import models.BallModel;
import models.IModel;

public class BallController implements IController {

	private final BallModel model;

	public BallController(final BallModel model) {
		this.model = model;
	}

	@Override
	public void update() {

		model.update();

	}

	@Override
	public IModel getModel() {
		return model;
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
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}
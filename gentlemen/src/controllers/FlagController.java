package controllers;

import models.FlagModel;
import models.IModel;

import org.lwjgl.util.vector.Vector2f;

public class FlagController implements IController {
	private final FlagModel model;

	public FlagController(FlagModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void update() {

	}

	@Override
	public void setPosition(Vector2f position) {
		model.setPosition(position);
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
	public void networkDataReceive(Object[] data) {
		// TODO Auto-generated method stub

	}

}

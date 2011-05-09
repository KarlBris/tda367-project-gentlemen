package controllers;

import models.IModel;
import models.ShockwaveModel;

import org.lwjgl.util.vector.Vector2f;

import core.Manager;

public class ShockwaveController implements IController {

	private ShockwaveModel model;

	public ShockwaveController(final ShockwaveModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void setPosition(final Vector2f position) {
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
	public void update() {
		model.update();

		if (model.isFinished()) {
			Manager.remove(this);
		}
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

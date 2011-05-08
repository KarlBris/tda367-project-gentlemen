package controllers;

import models.IModel;
import models.ReticleModel;

import org.lwjgl.util.vector.Vector2f;

public class KeyboardReticleController implements IController {

	private ReticleModel model;

	public KeyboardReticleController(final ReticleModel model) {
		this.model = model;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);

	}

}

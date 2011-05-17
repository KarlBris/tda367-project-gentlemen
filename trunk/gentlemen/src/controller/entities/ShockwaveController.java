package controller.entities;

import model.entities.ShockwaveModel;

import org.lwjgl.util.vector.Vector2f;

import controller.MainControllerFactory;
import controller.common.IController;

/**
 * This class controls a shockwave model
 */
public class ShockwaveController implements IController<ShockwaveModel> {

	private final ShockwaveModel model;

	public ShockwaveController(final ShockwaveModel model) {
		this.model = model;
	}

	@Override
	public ShockwaveModel getModel() {
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
			MainControllerFactory.get().remove(this);
		}
	}

}

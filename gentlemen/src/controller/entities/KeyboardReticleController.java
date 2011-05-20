package controller.entities;

import model.entities.ReticleModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

/**
 * This class controls a keyboardReticle model
 */
public final class KeyboardReticleController implements IController<ReticleModel> {

	private final ReticleModel model;

	public KeyboardReticleController(final ReticleModel model) {
		this.model = model;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public ReticleModel getModel() {
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
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * @return the position of the reticle
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

}

package controllers;

import models.IModel;
import models.ShockwaveModel;

import org.lwjgl.util.vector.Vector2f;

import core.Manager;

/**
 * This class controls a shockwave model
 */
public class ShockwaveController implements IController {

	private final ShockwaveModel model;

	public ShockwaveController(final ShockwaveModel model) {
		this.model = model;
	}

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
		return model;
	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * @see controllers.IController#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#end()
	 */
	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		model.update();

		if (model.isFinished()) {
			Manager.remove(this);
		}
	}

}

package controllers;

import models.IModel;
import models.ReticleModel;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class controls a keyboardReticle model
 */
public class KeyboardReticleController implements IController {

	private final ReticleModel model;

	public KeyboardReticleController(final ReticleModel model) {
		this.model = model;
	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
		return model;
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
	 * @see controllers.IController#networkDataSend()
	 */
	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see controllers.IController#networkDataReceive(java.lang.Object[])
	 */
	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * 
	 * @return the position of the reticle
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

}

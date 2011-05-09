package controllers;

import models.IModel;
import models.ReticleModel;

import org.lwjgl.util.vector.Vector2f;

import components.MouseComponent;

import core.Manager;

/**
 * Controls the ReticleModel using the mouse
 * 
 */
public class MouseReticleController implements IController {
	private ReticleModel model;
	private final MouseComponent mouse = Manager.getMouse();

	/**
	 * Constructor which connects this MouseReticleController to a ReticleModel
	 * object
	 * 
	 * @param model
	 *            the model which this MouseReticleController will have control
	 *            over
	 */
	public MouseReticleController(final ReticleModel model) {
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
	}

	@Override
	public void end() {
	}

	@Override
	public void update() {
		model.setPosition(mouse.getViewportPosition());
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
package controllers;

import models.IModel;
import models.PropModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

public class PropController implements IController {

	private final PropModel model;

	public PropController(final PropModel model) {
		this.model = model;
	}

	@Override
	public void update() {
		// Update model
		model.update();

	}

	/**
	 * Give prop a new color
	 * 
	 * @param c
	 *            , is the new color
	 */
	public void setColor(final Color c) {
		model.setColor(c);
	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * Sets the angle of the prop
	 * 
	 * @param angle
	 *            the angle to be set
	 */
	public void setAngle(final float angle) {
		model.setAngle(angle);
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void start() {

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

}

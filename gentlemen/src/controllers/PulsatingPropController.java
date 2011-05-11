package controllers;

import models.IModel;
import models.PropModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;

/**
 * This class controls a prop model
 */
public class PulsatingPropController implements IController {

	private final PropModel model;

	public PulsatingPropController(final PropModel model) {
		this.model = model;
	}

	private float colorWheel = 0.0f;
	private final float colorStep = Constants.TWO_PI / 300;

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		// Update model
		model.update();

		// Update color
		float tempColor = ((float) Math.cos(colorWheel)) / 2;
		tempColor += 0.5f;

		this.model.getGeometry().setColor(new Color(1.0f, 0.0f, tempColor));
		colorWheel += colorStep;

		if (colorWheel >= Constants.TWO_PI) {
			colorWheel = 0.0f;
		}

	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
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

}

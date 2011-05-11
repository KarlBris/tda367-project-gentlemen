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

	private float colorWheel = 0.0f;
	private final float colorStep;

	private final float redDelta;
	private final float greenDelta;
	private final float blueDelta;
	private final float alphaDelta;

	private final Color originColor;

	public PulsatingPropController(final PropModel model, final Color colorOne,
			final Color colorTwo, final int animationSteps) {
		this.model = model;

		this.originColor = colorTwo;

		colorStep = Constants.TWO_PI / animationSteps;

		redDelta = colorOne.getRed() - colorTwo.getRed();
		greenDelta = colorOne.getGreen() - colorTwo.getGreen();
		blueDelta = colorOne.getBlue() - colorTwo.getBlue();
		alphaDelta = colorOne.getAlpha() - colorTwo.getAlpha();

	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		// Update model
		model.update();

		// Update color
		final float phase = (((float) Math.cos(colorWheel)) / 2) + 0.5f;

		final float tempRed = (phase * redDelta) + originColor.getRed();
		final float tempGreen = (phase * greenDelta) + originColor.getGreen();
		final float tempBlue = (phase * blueDelta) + originColor.getBlue();
		final float tempAlpha = (phase * alphaDelta) + originColor.getAlpha();

		this.model.getGeometry().setColor(
				new Color(tempRed, tempGreen, tempBlue, tempAlpha));
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
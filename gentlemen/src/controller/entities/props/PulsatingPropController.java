package controller.entities.props;

import model.entities.props.PropModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import controller.common.IController;

/**
 * This class controls a prop model
 */
public final class PulsatingPropController implements IController<PropModel> {

	private final PropModel model;

	private float colorWheel = 0.0f;
	private float colorStep;

	private float redDelta;
	private float greenDelta;
	private float blueDelta;
	private float alphaDelta;

	private Color originColor;

	/**
	 * Creates a PulsatingPropController that pulses between two colors at a set
	 * speed
	 * 
	 * @param model
	 *            the PropModel this PulsatingPropController will control
	 * @param colorOne
	 *            the first color to pulse between
	 * @param colorTwo
	 *            the second color to pulse between
	 * @param animationSteps
	 *            the number of frames it takes to complete a pulse cycle
	 */
	public PulsatingPropController(final PropModel model, final Color colorOne,
			final Color colorTwo, final int animationSteps) {
		this.model = model;

		model.setColor(colorOne);
		this.originColor = colorTwo;

		colorStep = Constants.TWO_PI / animationSteps;

		redDelta = colorOne.getRed() - colorTwo.getRed();
		greenDelta = colorOne.getGreen() - colorTwo.getGreen();
		blueDelta = colorOne.getBlue() - colorTwo.getBlue();
		alphaDelta = colorOne.getAlpha() - colorTwo.getAlpha();

	}

	@Override
	public void update() {
		// Update model
		model.update();

		// Update color
		final float phase = (float) Math.cos(colorWheel) / 2 + 0.5f;

		final float tempRed = phase * redDelta + originColor.getRed();
		final float tempGreen = phase * greenDelta + originColor.getGreen();
		final float tempBlue = phase * blueDelta + originColor.getBlue();
		final float tempAlpha = phase * alphaDelta + originColor.getAlpha();

		this.model.setColor(new Color(tempRed, tempGreen, tempBlue, tempAlpha));
		colorWheel += colorStep;

		if (colorWheel >= Constants.TWO_PI) {
			colorWheel = 0.0f;
		}

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
	public PropModel getModel() {
		return model;
	}

	@Override
	public void start() {

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @return the position of the prop
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

	/**
	 * 
	 * @return angle of the prop
	 */
	public float getAngle() {
		return model.getAngle();
	}

	public void setColors(final Color colorOne, final Color colorTwo,
			final int animationSteps) {
		model.setColor(colorOne);
		this.originColor = colorTwo;

		colorStep = Constants.TWO_PI / animationSteps;

		redDelta = colorOne.getRed() - colorTwo.getRed();
		greenDelta = colorOne.getGreen() - colorTwo.getGreen();
		blueDelta = colorOne.getBlue() - colorTwo.getBlue();
		alphaDelta = colorOne.getAlpha() - colorTwo.getAlpha();

	}

}

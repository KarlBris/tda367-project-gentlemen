package controller.entities;

import model.entities.PropModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import controller.common.IController;

public final class PropController implements IController<PropModel> {

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

	/**
	 * @return the color of the prop
	 */
	public Color getColor() {
		return model.getColor();
	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * 
	 * @return the props position
	 */
	public Vector2f getPosition() {
		return model.getPosition();
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

	public float getAngle() {
		return model.getAngle();
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

}

package controllers;

import models.IModel;
import models.PropModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import core.Constants;

public class PropController implements IController {

	private final PropModel model;

	public PropController(final PropModel model) {
		this.model = model;
	}

	private float colorWheel = 0.0f;
	private final float colorStep = Constants.TWO_PI / 300;

	@Override
	public void update() {
		float tempColor = ((float) Math.cos(colorWheel)) / 2;
		tempColor += 0.5f;

		this.model.getGeometry().setColor(new Color(1.0f, 0.0f, tempColor));
		colorWheel += colorStep;

		if (colorWheel >= Constants.TWO_PI) {
			colorWheel = 0.0f;
		}

	}

	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	public void setAngle(final float angle) {
		model.setAngle(angle);
	}

	@Override
	public IModel getModel() {
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
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}

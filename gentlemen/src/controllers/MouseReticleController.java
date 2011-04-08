package controllers;

import models.IModel;
import models.ReticleModel;

import components.MouseComponent;

import core.Manager;

public class MouseReticleController implements IController {
	private ReticleModel model;
	private MouseComponent mouse = Manager.getMouse();

	public MouseReticleController(final ReticleModel model) {
		this.model = model;
	}

	@Override
	public void update() {
		model.setPosition(mouse.getViewportPosition());
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
	}

}

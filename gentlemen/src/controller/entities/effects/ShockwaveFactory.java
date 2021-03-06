package controller.entities.effects;

import model.entities.effects.ShockwaveModel;
import controller.common.IEntityFactory;

public final class ShockwaveFactory implements
		IEntityFactory<ShockwaveModel, ShockwaveController> {

	private ShockwaveModel model = new ShockwaveModel();
	private ShockwaveController controller = new ShockwaveController(model);

	@Override
	public ShockwaveModel getModel() {
		return model;
	}

	@Override
	public ShockwaveController getController() {
		return controller;
	}

}

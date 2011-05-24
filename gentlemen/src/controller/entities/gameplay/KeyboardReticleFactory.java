package controller.entities.gameplay;

import model.entities.gameplay.ReticleModel;
import controller.common.IEntityFactory;

public final class KeyboardReticleFactory implements
		IEntityFactory<ReticleModel, KeyboardReticleController> {

	private ReticleModel model = new ReticleModel();
	private KeyboardReticleController controller = new KeyboardReticleController(
			model);

	@Override
	public ReticleModel getModel() {
		return model;
	}

	@Override
	public KeyboardReticleController getController() {
		return controller;
	}

}

package factories.entities;

import model.entities.ReticleModel;
import controller.entities.KeyboardReticleController;

public class KeyboardReticleFactory implements
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

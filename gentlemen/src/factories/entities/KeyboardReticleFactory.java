package factories.entities;

import model.common.IModel;
import model.entities.ReticleModel;
import controller.common.IController;
import controller.entities.KeyboardReticleController;

public class KeyboardReticleFactory implements IEntityFactory {

	private ReticleModel model = new ReticleModel();
	private KeyboardReticleController controller = new KeyboardReticleController(
			model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

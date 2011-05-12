package factories.entities;

import model.entities.IModel;
import model.entities.ReticleModel;
import controller.entities.IController;
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

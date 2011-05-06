package factories;

import models.IModel;
import models.ReticleModel;
import controllers.IController;
import controllers.KeyboardReticleController;

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

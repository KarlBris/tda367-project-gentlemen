package factories;

import models.IModel;
import models.ReticleModel;
import controllers.IController;
import controllers.MouseReticleController;

public class MouseReticleFactory implements IEntityFactory {
	
	private ReticleModel model = new ReticleModel();
	private MouseReticleController controller = new MouseReticleController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

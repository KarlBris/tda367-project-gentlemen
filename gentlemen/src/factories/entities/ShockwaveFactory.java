package factories.entities;

import model.common.IModel;
import model.entities.ShockwaveModel;
import controller.common.IController;
import controller.entities.ShockwaveController;

public class ShockwaveFactory implements IEntityFactory {

	private ShockwaveModel model = new ShockwaveModel();
	private ShockwaveController controller = new ShockwaveController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

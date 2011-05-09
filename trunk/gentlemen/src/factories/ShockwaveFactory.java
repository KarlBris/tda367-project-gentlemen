package factories;

import models.IModel;
import models.ShockwaveModel;
import controllers.IController;
import controllers.ShockwaveController;

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

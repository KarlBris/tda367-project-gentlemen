package factories.entities;

import model.entities.ShockwaveModel;
import controller.entities.ShockwaveController;

public class ShockwaveFactory implements
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

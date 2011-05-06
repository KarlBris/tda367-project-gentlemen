package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PropController;

public class CratePropFactory implements IEntityFactory {

	private PropModel model = new PropModel(0.5f, 0.5f, 5.0f);
	private PropController controller = new PropController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

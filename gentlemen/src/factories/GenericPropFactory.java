package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PropController;

public class GenericPropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(2.0f, 2.0f, 0.0f);
	private final PropController controller = new PropController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

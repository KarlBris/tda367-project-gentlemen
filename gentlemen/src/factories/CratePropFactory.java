package factories;

import models.IModel;
import models.PropModel;
import utilities.Color;
import controllers.IController;
import controllers.PropController;

public class CratePropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(1.0f, 0.5f, 0.5f, 5.0f,
			Color.IT);
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

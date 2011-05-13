package factories.entities;

import model.common.IModel;
import model.entities.PropModel;
import utilities.Color;
import controller.common.IController;
import controller.entities.PropController;

public class CratePropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(1.0f, 0.5f, 0.5f, 5.0f,
			Color.BROWN);
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

package factories.entities;

import model.entities.IModel;
import model.entities.PropModel;
import controller.entities.IController;
import controller.entities.PropController;

public class PropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(0.0f, 1.0f, 1.0f, 1.0f);
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

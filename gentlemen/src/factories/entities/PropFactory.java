package factories.entities;

import model.entities.PropModel;
import controller.entities.PropController;

public final class PropFactory implements IEntityFactory<PropModel, PropController> {

	private final PropModel model = new PropModel(0.0f, 1.0f, 1.0f, 1.0f);
	private final PropController controller = new PropController(model);

	@Override
	public PropModel getModel() {
		return model;
	}

	@Override
	public PropController getController() {
		return controller;
	}

}

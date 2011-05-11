package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PulsatingPropController;

public class CratePropFactory implements IEntityFactory {

	private PropModel model = new PropModel(1.0f, 0.5f, 0.5f, 5.0f);
	private PulsatingPropController controller = new PulsatingPropController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

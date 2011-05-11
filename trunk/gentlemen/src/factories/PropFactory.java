package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PulsatingPropController;

public class PropFactory implements IEntityFactory {

	private PropModel model = new PropModel(0.0f, 1.0f, 1.0f, 1.0f);
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

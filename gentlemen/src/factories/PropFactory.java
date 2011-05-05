package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PropController;

public class PropFactory implements IEntityFactory {

	private final PropModel model = new PropModel();
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

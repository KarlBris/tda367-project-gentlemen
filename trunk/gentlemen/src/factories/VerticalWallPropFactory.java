package factories;

import models.IModel;
import models.PropModel;
import controllers.IController;
import controllers.PropController;
import core.Constants;

public class VerticalWallPropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(1.0f,
			Constants.VIEWPORT_HEIGHT, 0.0f);
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

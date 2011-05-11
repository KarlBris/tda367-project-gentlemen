package factories;

import models.IModel;
import models.PropModel;
import utilities.Constants;
import controllers.IController;
import controllers.PulsatingPropController;

public class HorizontalWallPropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(1.0f,
			Constants.VIEWPORT_WIDTH, 1.0f, 0.0f);
	private final PulsatingPropController controller = new PulsatingPropController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

package factories.entities;

import model.common.IModel;
import model.entities.PropModel;
import utilities.Color;
import utilities.Constants;
import controller.common.IController;
import controller.entities.PulsatingPropController;

public class VerticalWallPropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(0.2f, 0.5f,
			Constants.VIEWPORT_HEIGHT, 0.0f);
	private final PulsatingPropController controller = new PulsatingPropController(
			model, Color.RED, Color.MAGENTA, 300);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

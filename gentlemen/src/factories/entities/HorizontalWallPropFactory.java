package factories.entities;

import model.entities.IModel;
import model.entities.PropModel;
import utilities.Color;
import utilities.Constants;
import controller.entities.IController;
import controller.entities.PulsatingPropController;

public class HorizontalWallPropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(1.0f,
			Constants.VIEWPORT_WIDTH, 1.0f, 0.0f);
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

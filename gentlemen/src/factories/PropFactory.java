package factories;

import model.entities.IModel;
import model.entities.PropModel;
import utilities.Color;
import controller.entities.IController;
import controller.entities.PulsatingPropController;

public class PropFactory implements IEntityFactory {

	private final PropModel model = new PropModel(0.0f, 1.0f, 1.0f, 1.0f);
	private final PulsatingPropController controller = new PulsatingPropController(
			model, Color.CYAN, Color.YELLOW, 300);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

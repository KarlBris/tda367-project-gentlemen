package factories.entities;

import model.entities.IModel;
import model.entities.PropModel;
import utilities.Color;
import controller.entities.IController;
import controller.entities.PulsatingPropController;

public class BuildingPropFactory implements IEntityFactory {

	private PropModel model = new PropModel(1.0f, 4.0f, 3.0f, 0.0f);
	private PulsatingPropController controller = new PulsatingPropController(
			model, Color.randomColor(), Color.randomColor(),
			(int) (Math.random() * 200 + 100));

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

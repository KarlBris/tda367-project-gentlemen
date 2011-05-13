package factories.entities;

import model.common.IModel;
import model.entities.PropModel;
import utilities.Color;
import controller.common.IController;
import controller.entities.PulsatingPropController;

public class BuildingPropFactory implements IEntityFactory {

	private float sizeX = (float) Math.random() * 3 + 3;
	private float sizeY = (float) Math.random() * 3 + 2;

	private PropModel model = new PropModel(1.0f, sizeX, sizeY, 0.0f);
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

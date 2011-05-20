package factories.entities;

import model.entities.PropModel;
import utilities.Color;
import controller.entities.PulsatingPropController;

public final class BuildingPropFactory implements
		IEntityFactory<PropModel, PulsatingPropController> {

	private float sizeX = (float) Math.random() * 3 + 3;
	private float sizeY = (float) Math.random() * 3 + 2;

	private PropModel model = new PropModel(1.0f, sizeX, sizeY, 0.0f);
	private PulsatingPropController controller = new PulsatingPropController(
			model, Color.randomColor(), Color.randomColor(),
			(int) (Math.random() * 200 + 100));

	@Override
	public PropModel getModel() {
		return model;
	}

	@Override
	public PulsatingPropController getController() {
		return controller;
	}

}

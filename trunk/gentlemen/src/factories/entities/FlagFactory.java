package factories.entities;

import model.entities.FlagModel;
import model.entities.IModel;
import utilities.Color;
import controller.entities.FlagController;
import controller.entities.IController;

public class FlagFactory implements IEntityFactory {

	private final FlagModel model = new FlagModel(Color.BLACK);
	private final FlagController controller = new FlagController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

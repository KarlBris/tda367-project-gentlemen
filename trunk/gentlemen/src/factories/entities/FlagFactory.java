package factories.entities;

import model.common.IModel;
import model.entities.FlagModel;
import utilities.Color;
import controller.common.IController;
import controller.entities.FlagController;

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

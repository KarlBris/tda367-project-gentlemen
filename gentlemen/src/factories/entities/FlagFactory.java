package factories.entities;

import model.entities.FlagModel;
import utilities.Color;
import controller.entities.FlagController;

public final class FlagFactory implements IEntityFactory<FlagModel, FlagController> {

	private final FlagModel model = new FlagModel(Color.BLACK);
	private final FlagController controller = new FlagController(model);

	@Override
	public FlagModel getModel() {
		return model;
	}

	@Override
	public FlagController getController() {
		return controller;
	}

}

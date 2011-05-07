package factories;

import models.FlagModel;
import models.IModel;
import utilities.Color;
import controllers.FlagController;
import controllers.IController;

public class TeamFlagTwoFactory implements IEntityFactory {

	private final FlagModel model = new FlagModel(2, Color.RED);
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

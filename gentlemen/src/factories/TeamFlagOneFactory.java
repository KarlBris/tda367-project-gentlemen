package factories;

import models.FlagModel;
import models.IModel;
import utilities.Color;
import controllers.FlagController;
import controllers.IController;

public class TeamFlagOneFactory implements IEntityFactory {

	private final FlagModel model = new FlagModel(1, Color.BLUE);
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

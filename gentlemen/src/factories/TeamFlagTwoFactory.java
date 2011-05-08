package factories;

import models.FlagModel;
import models.IModel;
import utilities.Constants;
import controllers.FlagController;
import controllers.IController;

public class TeamFlagTwoFactory implements IEntityFactory {

	private final FlagModel model = new FlagModel(Constants.TEAM_TWO_COLOR);
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

package factories;

import models.IModel;
import models.TeamModel;
import controllers.IController;
import controllers.TeamController;

public class TeamFactory implements IEntityFactory {

	private final TeamModel model = new TeamModel();
	private final TeamController controller = new TeamController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

package factories.entities;

import model.entities.TeamModel;
import controller.entities.TeamController;

public class TeamFactory implements IEntityFactory<TeamModel, TeamController> {

	private final TeamModel model = new TeamModel();
	private final TeamController controller = new TeamController(model);

	@Override
	public TeamModel getModel() {
		return model;
	}

	@Override
	public TeamController getController() {
		return controller;
	}

}

package controller.entities.gamemode;

import model.entities.gamemode.TeamModel;
import controller.common.IEntityFactory;

public final class TeamFactory implements IEntityFactory<TeamModel, TeamController> {

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

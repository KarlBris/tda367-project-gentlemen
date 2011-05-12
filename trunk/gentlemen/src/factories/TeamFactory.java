package factories;

import model.entities.IModel;
import model.entities.TeamModel;
import controller.entities.IController;
import controller.entities.TeamController;

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

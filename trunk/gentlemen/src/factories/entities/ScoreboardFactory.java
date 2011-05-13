package factories.entities;

import model.entities.ScoreboardModel;
import controller.entities.ScoreboardController;

public class ScoreboardFactory implements
		IEntityFactory<ScoreboardModel, ScoreboardController> {

	private final ScoreboardModel model = new ScoreboardModel();
	private final ScoreboardController controller = new ScoreboardController(
			model);

	@Override
	public ScoreboardModel getModel() {
		return model;
	}

	@Override
	public ScoreboardController getController() {
		return controller;
	}

}

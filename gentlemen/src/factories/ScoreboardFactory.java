package factories;

import models.IModel;
import models.ScoreboardModel;
import controllers.IController;
import controllers.ScoreboardController;

public class ScoreboardFactory implements IEntityFactory {

	private final ScoreboardModel model = new ScoreboardModel();
	private final ScoreboardController controller = new ScoreboardController(
			model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

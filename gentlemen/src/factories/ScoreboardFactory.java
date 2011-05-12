package factories;

import model.entities.IModel;
import model.entities.ScoreboardModel;
import controller.entities.IController;
import controller.entities.ScoreboardController;

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

package controller.entities.gamemode;

import model.entities.gamemode.ScoreboardModel;
import controller.common.IEntityFactory;

public final class ScoreboardFactory implements
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

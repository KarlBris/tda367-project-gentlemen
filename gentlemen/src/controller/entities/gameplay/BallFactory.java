package controller.entities.gameplay;

import model.entities.gameplay.BallModel;
import controller.common.IEntityFactory;

public final class BallFactory implements IEntityFactory<BallModel, BallController> {

	private final BallModel model = new BallModel();
	private final BallController controller = new BallController(model);

	@Override
	public BallModel getModel() {
		return model;
	}

	@Override
	public BallController getController() {
		return controller;
	}

}

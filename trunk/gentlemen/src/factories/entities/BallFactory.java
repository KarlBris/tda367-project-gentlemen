package factories.entities;

import model.entities.BallModel;
import controller.entities.BallController;

public class BallFactory implements IEntityFactory<BallModel, BallController> {

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

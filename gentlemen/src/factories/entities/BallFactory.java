package factories.entities;

import model.common.IModel;
import model.entities.BallModel;
import controller.common.IController;
import controller.entities.BallController;

public class BallFactory implements IEntityFactory {

	private final BallModel model = new BallModel();
	private final BallController controller = new BallController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

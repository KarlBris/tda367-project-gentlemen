package factories.entities;

import model.entities.BallModel;
import model.entities.IModel;
import controller.entities.BallController;
import controller.entities.IController;

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

package factories;

import models.BallModel;
import models.IModel;
import controllers.BallController;
import controllers.IController;

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

package factories;

import models.BallModel;
import models.IModel;
import controllers.BasicBallController;
import controllers.IController;

public class BasicBallFactory implements IEntityFactory {

	private BallModel model = new BallModel();
	private BasicBallController controller = new BasicBallController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

package factories;

import models.IModel;
import models.RuleModel;
import controllers.IController;
import controllers.RuleController;

public class RuleFactory implements IEntityFactory {

	private final RuleModel model = new RuleModel(20);
	private final RuleController controller = new RuleController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

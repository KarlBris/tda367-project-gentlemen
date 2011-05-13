package factories.entities;

import model.common.IModel;
import model.entities.RuleModel;
import controller.common.IController;
import controller.entities.RuleController;

public class RuleFactory implements IEntityFactory {

	private final RuleModel model = new RuleModel();
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

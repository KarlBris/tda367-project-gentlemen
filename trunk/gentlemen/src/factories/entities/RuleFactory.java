package factories.entities;

import model.entities.IModel;
import model.entities.RuleModel;
import controller.entities.IController;
import controller.entities.RuleController;

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

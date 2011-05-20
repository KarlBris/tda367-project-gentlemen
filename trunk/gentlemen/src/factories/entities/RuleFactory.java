package factories.entities;

import model.entities.RuleModel;
import controller.entities.RuleController;

public final class RuleFactory implements IEntityFactory<RuleModel, RuleController> {

	private final RuleModel model = new RuleModel();
	private final RuleController controller = new RuleController(model);

	@Override
	public RuleModel getModel() {
		return model;
	}

	@Override
	public RuleController getController() {
		return controller;
	}

}

package controller.entities.gamemode;

import model.entities.gamemode.RuleModel;
import controller.common.IEntityFactory;

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

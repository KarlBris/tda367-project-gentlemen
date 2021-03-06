package controller.entities.props;

import model.entities.props.PropModel;
import utilities.Color;
import controller.common.IEntityFactory;

public final class CratePropFactory implements
		IEntityFactory<PropModel, PropController> {

	private final PropModel model = new PropModel(1.0f, 0.5f, 0.5f, 5.0f,
			Color.BROWN);
	private final PropController controller = new PropController(model);

	@Override
	public PropModel getModel() {
		return model;
	}

	@Override
	public PropController getController() {
		return controller;
	}

}

package controller.entities.props;

import model.entities.props.PropModel;
import utilities.Color;
import utilities.Constants;
import controller.common.IEntityFactory;

public final class HorizontalWallPropFactory implements
		IEntityFactory<PropModel, PulsatingPropController> {

	private final PropModel model = new PropModel(0.2f,
			Constants.VIEWPORT_WIDTH, 1.0f, 0.0f);
	private final PulsatingPropController controller = new PulsatingPropController(
			model, Color.RED, Color.MAGENTA, 300);

	@Override
	public PropModel getModel() {
		return model;
	}

	@Override
	public PulsatingPropController getController() {
		return controller;
	}

}

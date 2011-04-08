package factories;

import controllers.HumanPlayerController;
import controllers.IController;
import core.IModel;
import core.PlayerModel;

public class HumanPlayerFactory implements IEntityFactory {
	
	private PlayerModel model = new PlayerModel();
	private HumanPlayerController controller = new HumanPlayerController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

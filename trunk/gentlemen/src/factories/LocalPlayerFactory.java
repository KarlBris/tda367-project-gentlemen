package factories;

import controllers.LocalPlayerController;
import controllers.IController;
import core.IModel;
import core.PlayerModel;

public class LocalPlayerFactory implements IEntityFactory {
	
	private PlayerModel model = new PlayerModel();
	private LocalPlayerController controller = new LocalPlayerController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

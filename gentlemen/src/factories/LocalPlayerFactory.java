package factories;

import models.IModel;
import models.PlayerModel;
import controllers.LocalPlayerController;
import controllers.IController;

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

package factories;

import models.IModel;
import models.PlayerModel;
import controllers.OriginalPlayerController;
import controllers.IController;

public class LocalPlayerFactory implements IEntityFactory {
	
	private PlayerModel model = new PlayerModel();
	private OriginalPlayerController controller = new OriginalPlayerController(model);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}

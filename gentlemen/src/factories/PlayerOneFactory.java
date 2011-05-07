package factories;

import models.IModel;
import models.PlayerModel;

import org.lwjgl.input.Keyboard;

import utilities.Constants;
import controllers.IController;
import controllers.PlayerController;

public class PlayerOneFactory implements IEntityFactory {

	private final PlayerModel model = new PlayerModel(1,
			Constants.TEAM_ONE_COLOR);
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_D, Keyboard.KEY_A, Keyboard.KEY_W, Keyboard.KEY_S,
			Keyboard.KEY_V, Keyboard.KEY_V);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}
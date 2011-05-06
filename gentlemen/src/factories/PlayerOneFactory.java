package factories;

import models.IModel;
import models.PlayerModel;

import org.lwjgl.input.Keyboard;

import controllers.IController;
import controllers.PlayerController;

public class PlayerOneFactory implements IEntityFactory {

	private final PlayerModel model = new PlayerModel();
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_D, Keyboard.KEY_A, Keyboard.KEY_W, Keyboard.KEY_S,
			Keyboard.KEY_C, Keyboard.KEY_V);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}
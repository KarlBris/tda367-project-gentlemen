package factories;

import models.IModel;
import models.PlayerModel;

import org.lwjgl.input.Keyboard;

import utilities.Constants;
import controllers.IController;
import controllers.PlayerController;

public class PlayerTwoFactory implements IEntityFactory {

	private final PlayerModel model = new PlayerModel(Constants.TEAM_TWO_COLOR);
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_RIGHT, Keyboard.KEY_LEFT, Keyboard.KEY_UP,
			Keyboard.KEY_DOWN, Keyboard.KEY_PERIOD, Keyboard.KEY_PERIOD);

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public IController getController() {
		return controller;
	}

}
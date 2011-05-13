package factories.entities;

import model.entities.PlayerModel;

import org.lwjgl.input.Keyboard;

import utilities.Constants;
import controller.entities.PlayerController;

public class PlayerTwoFactory implements
		IEntityFactory<PlayerModel, PlayerController> {

	private final PlayerModel model = new PlayerModel(Constants.TEAM_TWO_COLOR);
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_RIGHT, Keyboard.KEY_LEFT, Keyboard.KEY_UP,
			Keyboard.KEY_DOWN, Keyboard.KEY_PERIOD, Keyboard.KEY_PERIOD);

	@Override
	public PlayerModel getModel() {
		return model;
	}

	@Override
	public PlayerController getController() {
		return controller;
	}

}
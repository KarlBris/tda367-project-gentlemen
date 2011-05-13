package factories.entities;

import model.entities.PlayerModel;

import org.lwjgl.input.Keyboard;

import utilities.Constants;
import controller.entities.PlayerController;

public class PlayerOneFactory implements
		IEntityFactory<PlayerModel, PlayerController> {

	private final PlayerModel model = new PlayerModel(Constants.TEAM_ONE_COLOR);
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_D, Keyboard.KEY_A, Keyboard.KEY_W, Keyboard.KEY_S,
			Keyboard.KEY_V, Keyboard.KEY_V);

	@Override
	public PlayerModel getModel() {
		return model;
	}

	@Override
	public PlayerController getController() {
		return controller;
	}

}
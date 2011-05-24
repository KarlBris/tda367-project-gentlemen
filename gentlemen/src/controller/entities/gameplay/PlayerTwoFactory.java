package controller.entities.gameplay;

import model.entities.gameplay.PlayerModel;

import org.lwjgl.input.Keyboard;

import controller.common.IEntityFactory;

public final class PlayerTwoFactory implements
		IEntityFactory<PlayerModel, PlayerController> {

	private final PlayerModel model = new PlayerModel();
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
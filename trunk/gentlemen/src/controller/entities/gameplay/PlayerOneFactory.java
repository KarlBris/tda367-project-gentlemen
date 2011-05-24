package controller.entities.gameplay;

import model.entities.gameplay.PlayerModel;

import org.lwjgl.input.Keyboard;

import controller.common.IEntityFactory;

public final class PlayerOneFactory implements
		IEntityFactory<PlayerModel, PlayerController> {

	private final PlayerModel model = new PlayerModel();
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
package factories;

import models.IModel;
import models.PlayerModel;

import org.lwjgl.input.Keyboard;

import controllers.IController;
import core.PlayerController;

public class PlayerTwoFactory implements IEntityFactory {

	private final PlayerModel model = new PlayerModel();
	private final PlayerController controller = new PlayerController(model,
			Keyboard.KEY_RIGHT, Keyboard.KEY_LEFT, Keyboard.KEY_UP,
			Keyboard.KEY_DOWN, Keyboard.KEY_PERIOD, Keyboard.KEY_PERIOD);

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IController getController() {
		// TODO Auto-generated method stub
		return null;
	}

}
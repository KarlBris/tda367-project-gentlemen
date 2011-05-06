package components;

import org.lwjgl.util.vector.Vector2f;

import controllers.IController;
import controllers.PlayerController;
import controllers.PropController;
import core.Constants;
import core.Manager;
import factories.PlayerOneFactory;
import factories.PropFactory;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {

		// Instantiate the player
		if (Manager.find(PlayerController.class).size() == 0) {
			Manager.instantiate(new PlayerOneFactory(),
					new Vector2f(Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
		}

		// Instantiate the prop
		if (Manager.find(PropController.class).size() == 0) {
			Manager.instantiate(new PropFactory(), new Vector2f(
					Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT));

			Manager.instantiate(new PropFactory(), new Vector2f(
					Constants.VIEWPORT_WIDTH / 2, 0.0f));
		}
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRemoved(final IController controller) {
		// TODO Auto-generated method stub

	}

}

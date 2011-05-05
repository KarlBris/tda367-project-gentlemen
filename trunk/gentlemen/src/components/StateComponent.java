package components;

import controllers.IController;
import controllers.PlayerController;
import controllers.PropController;
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
			Manager.instantiate(new PlayerOneFactory());
		}

		// Instantiate the prop
		if (Manager.find(PropController.class).size() == 0) {
			Manager.instantiate(new PropFactory());
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

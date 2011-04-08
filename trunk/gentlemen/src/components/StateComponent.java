package components;

import controllers.LocalPlayerController;
import controllers.IController;
import core.IComponent;
import core.Manager;
import factories.LocalPlayerFactory;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {
		
		// Instantiate the player
		if (Manager.find(LocalPlayerController.class).size() == 0) {
			Manager.instantiate(new LocalPlayerFactory());
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
	public void controllerAdded(IController controller) {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerRemoved(IController controller) {
		// TODO Auto-generated method stub

	}

}

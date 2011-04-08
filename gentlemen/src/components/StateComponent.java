package components;

import controllers.IController;
import core.IComponent;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {
		
		// Instantiate one test entity
		//if (Manager.find(Player.class).size() == 0) {
		//	Manager.instantiate(Player.class);
		//}
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

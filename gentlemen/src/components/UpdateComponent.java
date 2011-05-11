package components;

import java.util.List;

import controllers.IController;
import core.Manager;

/**
 * Is responsible for updating the Entities
 */
public class UpdateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		final List<IController> controllerList = Manager.getControllers();

		for (final IController c : controllerList) {
			c.update();
		}
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

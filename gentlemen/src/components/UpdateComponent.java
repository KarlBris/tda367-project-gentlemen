package components;

import java.util.List;

import controllers.IController;
import core.Manager;

/**
 * Is responsible for updating the Entities
 * 
 */
public class UpdateComponent implements IComponent {

	/**
	 * @see components.IComponent#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#cleanup()
	 */
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#update()
	 */
	@Override
	public void update() {
		final List<IController> controllerList = Manager.getControllers();

		for (final IController c : controllerList) {
			c.update();
		}
	}

	/**
	 * @see components.IComponent#controllerAdded(controllers.IController)
	 */
	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#controllerRemoved(controllers.IController)
	 */
	@Override
	public void controllerRemoved(final IController controller) {
		// TODO Auto-generated method stub

	}

}

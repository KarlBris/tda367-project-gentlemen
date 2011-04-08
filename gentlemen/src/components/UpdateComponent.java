package components;

import java.util.List;

import controllers.IController;
import core.Entity;
import core.IComponent;
import core.Manager;
/**
 * Is responsible for updating the Entities
 *
 */
public class UpdateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates all Entity objects
	 * 
	 * @see core.IComponent#update()
	 */
	@Override
	public void update() {
		List<IController> controllerList = Manager.getControllers();
		
		for (IController c : controllerList){
			c.update();
		}
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

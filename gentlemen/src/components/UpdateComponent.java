package components;

import java.util.List;

import core.Component;
import core.Entity;
import core.Manager;
/**
 * Is responsible for updating the Entities
 *
 */
public class UpdateComponent implements Component {

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
	 * @see core.Component#update()
	 */
	// Retrieves all Entity objects and calls their update() function	
	@Override
	public void update() {
		List<Entity> entityList = Manager.getEntities();
		
		for (Entity e : entityList){
			e.update();
		}

	}

	@Override
	public void entityAdded(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void entityRemoved(Entity entity) {
		// TODO Auto-generated method stub

	}

}

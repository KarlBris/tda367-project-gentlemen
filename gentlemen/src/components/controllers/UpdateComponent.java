package components.controllers;

import java.util.List;

import core.Component;
import core.Entity;
import core.Manager;

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

	// Fetches all Entities and calls their update() function	
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

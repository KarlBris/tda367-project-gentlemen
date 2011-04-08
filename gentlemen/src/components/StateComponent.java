package components;

import core.Component;
import core.Entity;
import core.Manager;
import core.Player;

public class StateComponent implements Component {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {
		
		// Instantiate one test entity
		if (Manager.find(Player.class).size() == 0) {
			Manager.instantiate(Player.class);
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
	public void entityAdded(Entity entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void entityRemoved(Entity entity) {
		// TODO Auto-generated method stub

	}

}

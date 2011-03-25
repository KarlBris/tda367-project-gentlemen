package core;

import java.util.List;

import models.Model;

/**
 * 
 * @author Karl, Gustav
 *
 */
public class Manager {
	
	private Model model = new Model();
	
	public <T extends Entity> T instantiate(Class<T> type) {
		// TODO
		return null;
	}
	
	public void remove(Entity entity) {
		model.removeEntity(entity);
	}
	
	public <T extends Entity> List<T> find(Class<T> type) {
		return model.find(type);
	}
	
	public void update() {
		// TODO
	}
}

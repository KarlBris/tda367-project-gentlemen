package core;

import java.util.List;

import models.Model;

/**
 * 
 * @author Karl, Gustav
 *
 */
public class Manager {
	
	private static Model model = new Model();
	
	private static void update() {
		// TODO
	}
	
	public static <T extends Entity> T instantiate(Class<T> type) {
		// TODO
		return null;
	}
	
	public static void remove(Entity entity) {
		model.removeEntity(entity);
	}
	
	public static <T extends Entity> List<T> find(Class<T> type) {
		return model.find(type);
	}
	
	public static List<Entity> getEntities() {
		return model.getEntities();
	}
	
	public static void start() {
		// Start game loop
	}
}
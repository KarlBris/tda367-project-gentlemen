package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import core.Entity;

/**
 * 
 * @author Gustav, Karl
 *
 */
public class Model {
	
	private HashMap<Class<? extends Entity>, ArrayList<Entity>> entityMap = new HashMap<Class<? extends Entity>, ArrayList<Entity>>();
	
	public void addEntity(Entity entity) {
		Class<? extends Entity> key = entity.getClass();
		
		ArrayList<Entity> entities;
		
		if (entityMap.containsKey(key)) {
			// The entity type exists within the map, add the element to the corresponding ArrayList
			entities = entityMap.get(key);
		}
		else {
			// The entity type does not exist within the map, create a new ArrayList
			entities = new ArrayList<Entity>();
			
			entityMap.put(key, entities);
		}
		
		// Add the entity to the corresponding ArrayList, but avoid duplicates
		if (!entities.contains(entity)) {
			entities.add(entity);
		}
	}
	
	public void removeEntity(Entity entity) {
		Class<? extends Entity> key = entity.getClass();
		
		if (entityMap.containsKey(key)) {
			ArrayList<Entity> entities = entityMap.get(key);
			
			// Remove the entity
			entities.remove(entity);
			
			// Remove the key if there are no corresponding entities left
			if (entities.isEmpty()) {
				entityMap.remove(key);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Entity> List<T> find(Class<T> type) {
		// Find entities of type T
		if (entityMap.containsKey(type)) {
			ArrayList<Entity> entities = entityMap.get(type);
			
			// Cast ArrayList<Entity> to ArrayList<T> for the caller's convenience
			ArrayList<T> output = new ArrayList<T>(entities.size());
			
			for (Entity e : entities) {
				output.add((T)e);
			}
			
			return output;
		}
		
		return new ArrayList<T>();
	}
	
	public List<Entity> getEntities() {
		// TODO Optimize this method if needed
		Collection<ArrayList<Entity>> listCollection = entityMap.values();
		
		// Add all entity lists to the output list
		List<Entity> output = new LinkedList<Entity>();
		
		for (ArrayList<Entity> list : listCollection) {
			output.addAll(list);
		}
		
		return output;
	}
}
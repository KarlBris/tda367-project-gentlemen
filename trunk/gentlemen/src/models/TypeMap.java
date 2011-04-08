package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TypeMap<T> {
	
	// Store controllers in a map for easy access
	private HashMap<Class<?>, ArrayList<T>> controllerMap = new HashMap<Class<?>, ArrayList<T>>();
	
	public void add(T item) {
		Class<?> key = item.getClass();
		
		ArrayList<T> items;
		
		if (controllerMap.containsKey(key)) {
			// The item type exists within the map, add the item to the corresponding ArrayList
			items = controllerMap.get(key);
		}
		else {
			// The item type does not exist within the map, create a new ArrayList
			items = new ArrayList<T>();
			
			controllerMap.put(key, items);
		}
		
		// Add the item to the corresponding ArrayList, but avoid duplicates
		if (!items.contains(item)) {
			items.add(item);
		}
	}
	
	public void remove(T item) {
		Class<?> key = item.getClass();
		
		if (controllerMap.containsKey(key)) {
			ArrayList<T> items = controllerMap.get(key);
			
			// Remove the entity
			items.remove(item);
			
			// Remove the key if there are no corresponding entities left
			if (items.isEmpty()) {
				controllerMap.remove(key);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <S extends T> List<S> find(Class<S> type) {
		// Find entities of type S
		if (controllerMap.containsKey(type)) {
			ArrayList<T> items = controllerMap.get(type);
			
			// Cast ArrayList<IController> to ArrayList<S> for the caller's convenience
			ArrayList<S> output = new ArrayList<S>(items.size());
			
			for (T c : items) {
				output.add((S)c);
			}
			
			return output;
		}
		
		return new ArrayList<S>();
	}
	
	public List<T> getItems() {
		// TODO Optimize this method if needed
		Collection<ArrayList<T>> listCollection = controllerMap.values();
		
		// Add all IController lists to the output list
		List<T> output = new LinkedList<T>();
		
		for (ArrayList<T> list : listCollection) {
			output.addAll(list);
		}
		
		return output;
	}
}
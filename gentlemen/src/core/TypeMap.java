package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TypeMap<T> {

	// Store instances in a map for easy access
	private final Map<Class<?>, List<T>> map = new HashMap<Class<?>, List<T>>();

	/**
	 * Adds an item to this TypeMap
	 * 
	 * @param item
	 *            the item to add
	 */
	public void add(final T item) {
		Class<?> key = item.getClass();

		List<T> items;

		if (map.containsKey(key)) {
			// The item type exists within the map, get the corresponding list
			items = map.get(key);
		} else {
			// The item type does not exist within the map, create a new
			// ArrayList
			items = new ArrayList<T>();

			map.put(key, items);
		}

		// Add the item to the list, but avoid duplicates
		if (!items.contains(item)) {
			items.add(item);
		}
	}

	/**
	 * Removes an item from this TypeMap
	 * 
	 * @param item
	 *            the item to be removed
	 */
	public void remove(final T item) {
		Class<?> key = item.getClass();

		if (map.containsKey(key)) {
			List<T> items = map.get(key);

			// Remove the item
			items.remove(item);

			// Remove the key if there are no corresponding items left
			if (items.isEmpty()) {
				map.remove(key);
			}
		}
	}

	/**
	 * Removes all items
	 */
	public void clear() {
		map.clear();
	}

	/**
	 * Finds all items of a specific type
	 * 
	 * @param <S>
	 *            the sought after type
	 * @param type
	 *            the sought after type class
	 * @return a list of items of type S
	 */
	@SuppressWarnings("unchecked")
	public <S extends T> List<S> find(final Class<S> type) {
		// Find items of type S
		if (map.containsKey(type)) {
			List<T> items = map.get(type);

			// Cast List<IController> to ArrayList<S> for the caller's
			// convenience
			List<S> output = new ArrayList<S>(items.size());

			for (T c : items) {
				output.add((S) c);
			}

			return output;
		}

		return new ArrayList<S>();
	}

	/**
	 * @return all items, regardless of type
	 */
	public List<T> getItems() {
		// TODO Optimize this method if needed
		Collection<List<T>> listCollection = map.values();

		// Add all item lists to the output list
		List<T> output = new LinkedList<T>();

		for (List<T> list : listCollection) {
			output.addAll(list);
		}

		return output;
	}
}
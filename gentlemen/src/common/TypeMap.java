package common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * This class represents a map in which all entities can be saved as values,
 * with their type as key, to allow for very easy access of all entity types.
 */
public class TypeMap<T> {

	private final Map<Class<?>, Collection<T>> map = new HashMap<Class<?>, Collection<T>>();

	/**
	 * Adds an item to this TypeMap
	 * 
	 * @param item
	 *            the item to add
	 */
	public void add(final T item) {
		final Class<?> key = item.getClass();

		Collection<T> items;

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
		final Class<?> key = item.getClass();

		if (map.containsKey(key)) {
			final Collection<T> items = map.get(key);

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
	 * @return the total number of items in the map
	 */
	public int getItemCount() {
		int count = 0;

		for (Collection<T> lists : map.values()) {
			count += lists.size();
		}

		return count;
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
	public <S extends T> Collection<S> find(final Class<S> type) {
		// Find items of type S
		if (map.containsKey(type)) {
			final Collection<T> items = map.get(type);

			return (Collection<S>) items;
		}

		return new LinkedList<S>();
	}

	/**
	 * @return all items, regardless of type
	 */
	public Collection<T> getItems() {
		Collection<T> allItems = new LinkedList<T>();

		for (Collection<T> items : map.values()) {
			allItems.addAll(items);
		}

		return allItems;
	}
}
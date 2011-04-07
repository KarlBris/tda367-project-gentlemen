package core;

/**
 * A component handles entities in some way
 */
public interface Component {
	
	/**
	 * Allows the component to initialize itself at startup
	 */
	public void initialize();

	/**
	 * Allows the component to instantiate entities that it is responsible for
	 */
	public void instantiatePermanentEntities();
	
	/**
	 * Allows the component perform cleanup tasks before the game exits
	 */
	public void cleanup();
	
	/**
	 * Allows the component to perform update tasks every frame of the game loop
	 */
	public void update();
	
	/**
	 * Called when a new entity is instantiated
	 * @param entity the newly created entity
	 */
	public void entityAdded(Entity entity);
	
	/**
	 * Called when an entity is removed
	 * @param entity the entity that is about to be removed
	 */
	public void entityRemoved(Entity entity);

}

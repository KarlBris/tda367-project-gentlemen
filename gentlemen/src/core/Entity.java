package core;

/**
 * The base class of all logical and/or physical objects in the game world
 */
public abstract class Entity {
	
	private Geometry geometry;
	
	/**
	 * Initializes the entity. super() is the only allowed code in derived subclass constructors.
	 * @param geometry
	 */
	public Entity(Geometry geometry) {
		this.geometry = geometry;
	}
	
	/**
	 * @return the geometry of this entity
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	
	/**
	 * Allows an entity to perform tasks when the entity is instantiated
	 */
	public void start() {}
	
	/**
	 * Allows an entity to perform tasks before the entity is removed
	 */
	public void end() {}
	
	/**
	 * Allows an entity to perform tasks every frame in the game loop
	 */
	public void update() {}
}

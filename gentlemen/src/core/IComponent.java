package core;

import controllers.IController;

/**
 * A component handles entities in some way
 */
public interface IComponent {
	
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
	
	public void controllerAdded(IController controller);
	
	public void controllerRemoved(IController controller);

}

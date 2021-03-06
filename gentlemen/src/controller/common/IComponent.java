package controller.common;

import model.common.IModel;

/**
 * A component handles entities in some way
 */
public interface IComponent {

	/**
	 * Allows the component to initialize itself at startup
	 */
	public void initialize();

	/**
	 * Allows the component to perform update tasks every frame of the game loop
	 */
	public void update();

	/**
	 * Allows the component to perform certain tasks when a controller is
	 * instantiated through Manager.instantiate()
	 * 
	 * @param controller
	 *            the instantiated controller
	 */
	public <M extends IModel> void controllerAdded(IController<M> controller);

	/**
	 * Allows the component to perform certain tasks when a controller is
	 * removed from the game world
	 * 
	 * @param controller
	 *            the removed controller
	 */
	public <M extends IModel> void controllerRemoved(IController<M> controller);

}

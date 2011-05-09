package core;

import java.util.List;

import models.IModel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;

import components.IComponent;
import components.KeyboardComponent;
import components.MouseComponent;
import components.PhysicsComponent;
import components.RenderComponent;
import components.StateComponent;
import components.UpdateComponent;

import controllers.IController;
import factories.IEntityFactory;

public class Manager {

	// Models and Controllers
	private static TypeMap<IModel> modelManager = new TypeMap<IModel>();
	private static TypeMap<IController> controllerManager = new TypeMap<IController>();

	// Components
	private static PhysicsComponent physics = new PhysicsComponent();
	private static StateComponent state = new StateComponent();
	private static UpdateComponent update = new UpdateComponent();
	private static RenderComponent render = new RenderComponent();
	private static KeyboardComponent keyboard = new KeyboardComponent();
	private static MouseComponent mouse = new MouseComponent();

	// All components in a easy to use format
	private static IComponent[] components = { physics, state, update, render,
			keyboard, mouse };

	/**
	 * @return gets the keyboard component
	 */
	public static KeyboardComponent getKeyboard() {
		return keyboard;
	}

	/**
	 * @return gets the mouse component
	 */
	public static MouseComponent getMouse() {
		return mouse;
	}

	/**
	 * Instantiates a new IModel/IController pair. The only way to create a new
	 * instance of an IModel/IController.
	 * 
	 * @param factory
	 *            the entity factory of the sought after IModel/IController pair
	 * @return the IController instance of the newly instantiated
	 *         IModel/IController pair
	 */
	public static IController instantiate(final IEntityFactory factory) {
		return instantiate(factory, new Vector2f(0.0f, 0.0f));
	}

	public static IController instantiate(final IEntityFactory factory,
			final Vector2f position) {
		if (factory != null) {
			// Get the new model and controller
			IModel newModel = factory.getModel();
			IController newController = factory.getController();

			// Add model and controller to managers
			modelManager.add(newModel);
			controllerManager.add(newController);

			// Let all components know that the new controller has been created
			for (IComponent component : components) {
				component.controllerAdded(newController);
			}

			// Set position
			newController.setPosition(position);

			// Let the controller know that it has been created
			newController.start();

			return newController;
		}

		return null;
	}

	/**
	 * Removes an existing IController instance and corresponding IModel from
	 * the game world
	 * 
	 * @param controller
	 *            the IController instance to be removed
	 */
	public static void remove(final IController controller) {
		if (controller != null) {

			// Let the controller know that it is being removed
			controller.end();

			// Let all components know that the controller is being removed
			for (IComponent component : components) {
				component.controllerRemoved(controller);
			}

			// Remove the model and the controller from the managers
			modelManager.remove(controller.getModel());
			controllerManager.remove(controller);
		}
	}

	/**
	 * Finds all controllers of a specific sub-type to IController
	 * 
	 * @param <T>
	 *            the sought after sub-type to IController
	 * @param type
	 *            the sought after type class
	 * @return a list of controllers of type T
	 */
	public static <T extends IController> List<T> find(
			final Class<T> controllerType) {
		return controllerManager.find(controllerType);
	}

	/**
	 * @return a list of all IModel instances
	 */
	public static List<IModel> getModels() {
		return modelManager.getItems();
	}

	/**
	 * @return a list of all IController instances
	 */
	public static List<IController> getControllers() {
		return controllerManager.getItems();
	}

	/**
	 * Initializes the display
	 * 
	 * @return true if the setup succeeded, false otherwise
	 */
	private static boolean initializeDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(
					Constants.DEFAULT_SCREEN_WIDTH,
					Constants.DEFAULT_SCREEN_HEIGHT));

			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();

			return false;
		}

		return true;
	}

	/**
	 * Cleans up the display
	 */
	private static void cleanupDisplay() {
		Display.destroy();
	}

	/**
	 * Updates the display
	 */
	private static void updateDisplay() {

		Display.update();

		// Lock framerate
		Display.sync(Constants.FRAMES_PER_SECOND);

		Display.processMessages();
	}

	/**
	 * Initializes all components
	 */
	private static void initializeComponents() {

		for (IComponent component : components) {
			component.initialize();
		}
	}

	/**
	 * Cleans up all the components
	 */
	private static void cleanupComponents() {

		for (IComponent component : components) {
			component.cleanup();
		}
	}

	/**
	 * Updates all the components
	 */
	private static void updateComponents() {

		for (IComponent component : components) {
			component.instantiatePermanentEntities();

			component.update();
		}
	}

	/**
	 * Initializes the game and starts the game loop
	 */
	public static void start() {
		if (!initializeDisplay()) {
			return;
		}

		initializeComponents();

		while (!Display.isCloseRequested()) {

			updateComponents();

			updateDisplay();
		}

		cleanupComponents();
		cleanupDisplay();
	}
}
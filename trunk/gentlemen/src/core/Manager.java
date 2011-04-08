package core;

import java.util.List;

import models.TypeMap;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import components.KeyboardComponent;
import components.MouseComponent;
import components.NetworkComponent;
import components.PhysicsComponent;
import components.RenderComponent;
import components.StateComponent;
import components.UpdateComponent;

import controllers.IController;
import factories.IEntityFactory;

public class Manager {
	
	private static TypeMap<IModel> modelManager = new TypeMap<IModel>();
	private static TypeMap<IController> controllerManager = new TypeMap<IController>();
	
	// Components
	private static NetworkComponent network = new NetworkComponent();
	private static PhysicsComponent physics = new PhysicsComponent();
	private static StateComponent state = new StateComponent();
	private static UpdateComponent update = new UpdateComponent();
	private static RenderComponent render = new RenderComponent();
	private static KeyboardComponent keyboard = new KeyboardComponent();
	private static MouseComponent mouse = new MouseComponent();
	
	// All components in a easy to use format
	private static Component[] components = { network, physics, state, update, render, keyboard, mouse };
	
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
	 * Instantiates an entity. The only way to create a new instance of an entity.
	 * @param type the class object of the sought type
	 * @return a reference to the newly instantiated entity
	 */
	public static IController instantiate(IEntityFactory factory) {
		// Get the new model and controller
		IModel newModel = factory.getModel();
		IController newController = factory.getController();
		
		// Add model and controller to managers
		modelManager.add(newModel);
		controllerManager.add(newController);
		
		// Let all components know that the new controller has been created
		for (Component component : components) {
			//component.entityAdded(entity)
		}
		
		// Let the controller know that it has been created
		newController.start();
		
		return newController;
	}
	
	/**
	 * Removes an entity. The only way to remove an entity.
	 * @param entity the entity to remove
	 */
	public static void remove(IController controller) {
		if (controller != null) {
			
			// Let the controller know that it is being removed
			controller.end();
			
			// Let all components know that the controller is being removed
			for (Component component : components) {
				//component.entityRemoved(entity);
			}
			
			// Remove the model and the controller from the managers
			modelManager.remove(controller.getModel());
			controllerManager.remove(controller);
		}
	}
	
	/**
	 * Finds entities of a specific type
	 * @param type the class object of the sought type
	 * @return a list of found entities
	 */
	public static <T extends IController> List<T> find(Class<T> controllerType) {
		return controllerManager.find(controllerType);
	}
	
	public static List<IModel> getModels() {
		return modelManager.getItems();
	}
	
	public static List<IController> getControllers() {
		return controllerManager.getItems();
	}
	
	/**
	 * Initializes the display
	 * @return true if the setup succeeded, false otherwise
	 */
	private static boolean initializeDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(Constants.DEFAULT_SCREEN_WIDTH, Constants.DEFAULT_SCREEN_HEIGHT));
			
			Display.create();
		}
		catch (LWJGLException e) {
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
		
		for (Component component : components) {
			component.initialize();
		}
	}
	
	/**
	 * Cleans up all the components
	 */
	private static void cleanupComponents() {
		
		for (Component component : components) {
			component.cleanup();
		}
	}
	
	/**
	 * Updates all the components
	 */
	private static void updateComponents() {
		
		for (Component component : components) {
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
package core;

import java.util.List;

import models.Model;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import components.controllers.NetworkComponent;
import components.controllers.PhysicsComponent;
import components.controllers.StateComponent;
import components.controllers.UpdateComponent;
import components.utilities.KeyboardComponent;
import components.utilities.MouseComponent;
import components.views.RenderComponent;

/**
 * Holds a model, controllers and views
 */
public class Manager {
	
	// Model
	private static Model model = new Model();
	
	// Controllers
	private static NetworkComponent network = new NetworkComponent();
	private static PhysicsComponent physics = new PhysicsComponent();
	private static StateComponent state = new StateComponent();
	private static UpdateComponent update = new UpdateComponent();
	
	// View
	private static RenderComponent render = new RenderComponent();
	
	// Utilities
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
	public static <T extends Entity> T instantiate(Class<T> type) {
		// Instantiate an entity of type T
		T newEntity = null;
		
		try {
			newEntity = type.newInstance();
		}
		catch (InstantiationException e) {
			// TODO Do whatever it takes!
		}
		catch (IllegalAccessException e) {
			// TODO Do whatever it takes!
		}
		
		if (newEntity != null) {
			
			// Add this entity to the model
			model.addEntity(newEntity);
			
			// Let all components know that this entity has been created
			for (Component component : components) {
				component.entityAdded(newEntity);
			}
			
			// Let the entity know that it has been created
			newEntity.start();
		}
		
		return newEntity;
	}
	
	/**
	 * Removes an entity. The only way to remove an entity.
	 * @param entity the entity to remove
	 */
	public static void remove(Entity entity) {
		// TODO Implement!
	}
	
	/**
	 * Finds entities of a specific type
	 * @param type the class object of the sought type
	 * @return a list of found entities
	 */
	public static <T extends Entity> List<T> find(Class<T> type) {
		return model.find(type);
	}
	
	/**
	 * Gets all entities
	 * @return a list of all entities
	 */
	public static List<Entity> getEntities() {
		return model.getEntities();
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
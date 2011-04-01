package core;

import java.util.List;

import models.Model;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import components.controllers.NetworkComponent;
import components.controllers.PhysicsComponent;
import components.controllers.StateComponent;
import components.controllers.UpdateComponent;
import components.utilities.KeyboardComponent;
import components.utilities.MouseComponent;
import components.views.RenderComponent;

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
	
	private static Component[] components = { network, physics, state, update, render, keyboard, mouse };
	
	public static KeyboardComponent getKeyboard(){
		return keyboard;
	}
	
	public static MouseComponent getMouse(){
		return mouse;
	}
	
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
	
	public static void remove(Entity entity) {
		// TODO Implement!
	}
	
	public static <T extends Entity> List<T> find(Class<T> type) {
		return model.find(type);
	}
	
	public static List<Entity> getEntities() {
		return model.getEntities();
	}
	
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
	
	private static void cleanupDisplay() {
		Display.destroy();
	}
	
	private static void updateDisplay() {
		Display.update();
		
		// Lock framerate
		Display.sync(Constants.FRAMES_PER_SECOND);
	}
	
	private static void initializeComponents() {
		
		for (Component component : components) {
			component.initialize();
		}
	}
	
	private static void cleanupComponents() {
		
		for (Component component : components) {
			component.cleanup();
		}
	}
	
	private static void updateComponents() {
		
		for (Component component : components) {
			component.instantiatePermanentEntities();
			
			component.update();
		}
	}
	
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
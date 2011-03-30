package core;

import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import models.Model;

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
	
	private static KeyboardComponent getKeyboard(){
		return keyboard;
	}
	
	private static MouseComponent getMouse(){
		return mouse;
	}
	
	public static <T extends Entity> T instantiate(Class<T> type) {
		// TODO
		return null;
	}
	
	public static void remove(Entity entity) {
		model.removeEntity(entity);
	}
	
	public static <T extends Entity> List<T> find(Class<T> type) {
		return model.find(type);
	}
	
	public static List<Entity> getEntities() {
		return model.getEntities();
	}
	
	private static boolean initializeDisplay() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			
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
		Display.sync(60);
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
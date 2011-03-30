package core;

import java.util.List;

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
	
	private static KeyboardComponent getKeyboard(){
		return keyboard;
	}
	private static MouseComponent getMouse(){
		return mouse;
	}
	
	private static void update() {
		// TODO
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
	
	public static void start() {
		// Start game loop
	}
}
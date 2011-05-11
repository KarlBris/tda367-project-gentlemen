package controller;

import java.util.List;

import model.IMainModel;

import org.lwjgl.util.vector.Vector2f;


import controller.components.IComponent;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import controller.components.PhysicsComponent;
import controller.components.StateComponent;
import controllers.IController;
import core.TypeMap;

public class MainController implements IMainController {

	private IMainModel mainModel;

	private TypeMap<IController> controllers = new TypeMap<IController>();

	private PhysicsComponent physicsComponent = new PhysicsComponent();
	private StateComponent stateComponent = new StateComponent();
	private KeyboardComponent keyboardComponent = new KeyboardComponent();
	private MouseComponent mouseComponent = new MouseComponent();

	private IComponent[] components = { physicsComponent, stateComponent,
			keyboardComponent, mouseComponent };

	public MainController(IMainModel mainModel) {
		this.mainModel = mainModel;
	}

	@Override
	public void add(IController controller, Vector2f position) {

		// Add model and controller
		mainModel.add(controller.getModel());

		controllers.add(controller);

		// Let all components know about the creation of this controller
		for (IComponent component : components) {
			component.controllerAdded(controller);
		}

		// Set the start position of the controller
		controller.setPosition(position);

		// Let the controller know that it has been created
		controller.start();
	}

	@Override
	public void remove(IController controller) {
		// Let the controller know that it is being removed
		controller.end();

		// Let all components know about the removal of this controller
		for (IComponent component : components) {
			component.controllerRemoved(controller);
		}

		// Remove model and controller
		mainModel.remove(controller.getModel());

		controllers.remove(controller);
	}

	@Override
	public <T extends IController> List<T> find(Class<T> type) {
		return controllers.find(type);
	}

	@Override
	public List<IController> getControllers() {
		return controllers.getItems();
	}

	@Override
	public KeyboardComponent getKeyboardComponent() {
		return keyboardComponent;
	}

	@Override
	public MouseComponent getMouseComponent() {
		return mouseComponent;
	}

	@Override
	public void initialize() {

		// Initialize all components
		for (IComponent component : components) {
			component.initialize();
		}

		// Initialize all entities
		stateComponent.initializeEntities();

	}

	@Override
	public void update() {

		// Update all components
		for (IComponent component : components) {
			component.update();
		}

		// Update all controllers
		List<IController> list = controllers.getItems();

		for (IController controller : list) {
			controller.update();
		}
	}
}

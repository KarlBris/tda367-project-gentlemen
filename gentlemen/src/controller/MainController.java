package controller;

import java.util.List;

import model.IMainModel;

import org.lwjgl.util.vector.Vector2f;

import common.TypeMap;

import controller.common.IController;
import controller.components.IComponent;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import controller.components.PhysicsComponent;
import controller.components.StateComponent;
import factories.entities.IEntityFactory;

public class MainController implements IMainController {

	// References
	private IMainModel mainModel;

	// Collection of controllers
	private TypeMap<IController> controllerMap = new TypeMap<IController>();

	// Components
	private PhysicsComponent physicsComponent = new PhysicsComponent();
	private StateComponent stateComponent = new StateComponent();
	private KeyboardComponent keyboardComponent = new KeyboardComponent();
	private MouseComponent mouseComponent = new MouseComponent();

	private IComponent[] components = { physicsComponent, stateComponent,
			keyboardComponent, mouseComponent };

	public MainController(final IMainModel mainModel) {
		this.mainModel = mainModel;
	}

	@Override
	public <T extends IController> T instantiate(final IEntityFactory factory,
			final Vector2f position) {
		if (factory != null) {
			T controller = (T) factory.getController();

			// Add model and controller
			mainModel.add(controller.getModel());

			controllerMap.add(controller);

			// Let all components know about the creation of this controller
			for (IComponent component : components) {
				component.controllerAdded(controller);
			}

			// Set the start position of the controller
			controller.setPosition(position);

			// Let the controller know that it has been created
			controller.start();

			return controller;
		}

		return null;
	}

	@Override
	public void remove(final IController controller) {
		// Let the controller know that it is being removed
		controller.end();

		// Let all components know about the removal of this controller
		for (IComponent component : components) {
			component.controllerRemoved(controller);
		}

		// Remove model and controller
		mainModel.remove(controller.getModel());

		controllerMap.remove(controller);
	}

	@Override
	public <T extends IController> List<T> find(final Class<T> type) {
		return controllerMap.find(type);
	}

	@Override
	public List<IController> getControllers() {
		return controllerMap.getItems();
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
		List<IController> allControllers = controllerMap.getItems();

		for (IController controller : allControllers) {
			controller.update();
		}
	}
}

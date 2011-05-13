package controller;

import java.util.List;

import model.IMainModel;
import model.common.IModel;

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
	private TypeMap<IController<? extends IModel>> controllerMap = new TypeMap<IController<? extends IModel>>();

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
	public <M extends IModel, C extends IController<M>, T extends IEntityFactory<M, C>> C instantiate(
			final IEntityFactory<M, C> factory, final Vector2f position) {
		if (factory != null) {
			C controller = factory.getController();

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
	public <M extends IModel> void remove(final IController<M> controller) {
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
	public <M extends IModel, C extends IController<M>> List<C> find(
			final Class<C> type) {
		return controllerMap.find(type);
	}

	@Override
	public List<IController<? extends IModel>> getControllers() {
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
		List<IController<? extends IModel>> allControllers = controllerMap
				.getItems();

		for (IController<? extends IModel> controller : allControllers) {
			controller.update();
		}
	}
}
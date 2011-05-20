package controller;

import java.util.Collection;

import model.IMainModel;
import model.MainModelFactory;
import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import common.TypeMap;

import controller.common.IController;
import controller.components.IComponent;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import controller.components.PhysicsComponent;
import factories.entities.IEntityFactory;

public final class MainController implements IMainController {

	private static MainController instance;

	// References
	private final IMainModel mainModel;

	// Collection of controllers
	private final TypeMap<IController<? extends IModel>> controllerMap = new TypeMap<IController<? extends IModel>>();

	// Components
	private final PhysicsComponent physicsComponent = new PhysicsComponent();
	private final KeyboardComponent keyboardComponent = new KeyboardComponent();
	private final MouseComponent mouseComponent = new MouseComponent();

	private final IComponent[] components = { physicsComponent,
			keyboardComponent, mouseComponent };

	private MainController() {
		this.mainModel = MainModelFactory.get();
	}

	public static MainController get() {
		if (instance == null) {
			instance = new MainController();
		}

		return instance;
	}

	@Override
	public <M extends IModel, C extends IController<M>, T extends IEntityFactory<M, C>> C instantiate(
			final IEntityFactory<M, C> factory) {
		return instantiate(factory, new Vector2f());
	}

	@Override
	public <M extends IModel, C extends IController<M>, T extends IEntityFactory<M, C>> C instantiate(
			final IEntityFactory<M, C> factory, final Vector2f position) {
		if (factory != null) {
			final C controller = factory.getController();

			// Add model and controller
			mainModel.add(controller.getModel());

			controllerMap.add(controller);

			// Let all components know about the creation of this controller
			for (final IComponent component : components) {
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
		for (final IComponent component : components) {
			component.controllerRemoved(controller);
		}

		// Remove model and controller
		mainModel.remove(controller.getModel());

		controllerMap.remove(controller);
	}

	@Override
	public void removeAll() {
		Collection<IController<? extends IModel>> controllers = getControllers();

		for (IController<? extends IModel> controller : controllers) {
			remove(controller);
		}
	}

	@Override
	public <M extends IModel, C extends IController<M>> Collection<C> find(
			final Class<C> type) {
		return controllerMap.find(type);
	}

	@Override
	public Collection<IController<? extends IModel>> getControllers() {
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
		for (final IComponent component : components) {
			component.initialize();
		}

	}

	@Override
	public void update() {

		// Update all components
		for (final IComponent component : components) {
			component.update();
		}

		// Update all controllers
		final Collection<IController<? extends IModel>> allControllers = controllerMap
				.getItems();

		for (final IController<? extends IModel> controller : allControllers) {
			controller.update();
		}
	}
}
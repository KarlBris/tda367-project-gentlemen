package core;

import java.util.List;

import model.IMainModel;
import model.common.IModel;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import view.IView;
import controller.IMainController;
import controller.common.IController;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import factories.MainControllerFactory;
import factories.MainModelFactory;
import factories.ViewFactory;
import factories.entities.IEntityFactory;

public class Manager {

	private static IMainModel mainModel = MainModelFactory.get();

	private static IMainController mainController = MainControllerFactory
			.get(mainModel);

	private static IView view = ViewFactory.get(mainModel);

	/**
	 * @return gets the keyboard component
	 */
	public static KeyboardComponent getKeyboard() {
		return mainController.getKeyboardComponent();
	}

	/**
	 * @return gets the mouse component
	 */
	public static MouseComponent getMouse() {
		return mainController.getMouseComponent();
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
	public static <M extends IModel, C extends IController<M>> C instantiate(
			final IEntityFactory<M, C> factory) {
		return instantiate(factory, new Vector2f(0.0f, 0.0f));
	}

	/**
	 * Instantiates a new IModel/IController pair. The only way to create a new
	 * instance of an IModel/IController.
	 * 
	 * @param factory
	 *            the entity factory of the sought after IModel/IController pair
	 * @param position
	 *            the desired position of the entity in the game world
	 * @return the IController instance of the newly instantiated
	 *         IModel/IController pair
	 */
	public static <M extends IModel, C extends IController<M>> C instantiate(
			final IEntityFactory<M, C> factory, final Vector2f position) {
		return mainController.instantiate(factory, position);
	}

	/**
	 * Removes an existing IController instance and corresponding IModel from
	 * the game world
	 * 
	 * @param controller
	 *            the IController instance to be removed
	 */
	public static void remove(final IController<? extends IModel> controller) {
		if (controller != null) {
			mainController.remove(controller);
		}
	}

	/**
	 * Removes all models and controllers from the game world
	 */
	public static void removeAll() {
		final List<IController<? extends IModel>> controllers = mainController
				.getControllers();

		for (final IController<? extends IModel> controller : controllers) {
			remove(controller);
		}
	}

	/**
	 * Finds all controllers of a specific sub-type to IController
	 * 
	 * @param <T>
	 *            the sought after sub-type to IController
	 * @param controllerType
	 *            the sought after type class
	 * @return a list of controllers of type T
	 */
	public static <M extends IModel, C extends IController<M>> List<C> find(
			final Class<C> type) {
		return mainController.find(type);
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
		} catch (final LWJGLException e) {
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
	 * Initializes the game and starts the game loop
	 */
	public static void start() {

		if (!initializeDisplay()) {
			return;
		}

		mainController.initialize();

		view.initialize();

		while (!Display.isCloseRequested()) {

			mainController.update();

			view.render();

			updateDisplay();
		}

		cleanupDisplay();
	}
}
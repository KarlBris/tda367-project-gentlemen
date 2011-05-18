package core;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import utilities.Constants;
import view.IView;
import view.ViewFactory;
import controller.IMainController;
import controller.MainControllerFactory;

public class Manager {

	private static IMainController mainController = MainControllerFactory.get();

	private static IView view = ViewFactory.get();

	private static LevelManager levelManager = new LevelManager();

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

		// Initialize game
		if (!initializeDisplay()) {
			return;
		}

		mainController.initialize();

		view.initialize();

		// Load level
		levelManager.initializeEntities();

		while (!Display.isCloseRequested()) {

			// Update game
			mainController.update();

			// Update level manager
			levelManager.update();

			// Render game
			view.render();

			updateDisplay();
		}

		cleanupDisplay();
	}
}
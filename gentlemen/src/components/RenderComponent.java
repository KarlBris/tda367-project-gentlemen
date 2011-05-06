package components;

import java.util.List;

import models.IModel;

import org.lwjgl.opengl.GL11;

import controllers.IController;
import core.Manager;

/**
 * This class is responsible for setting up the viewport projection and making
 * Entities render themselves
 */
public class RenderComponent implements IComponent {

	/**
	 * Initializes the viewport projection based upon the aspect ratio of the
	 * display window
	 * 
	 * @see components.IComponent#initialize()
	 */
	@Override
	public void initialize() {

		// The width and height of the viewport in game units
		float width;
		float height;

		// Initializes the display window size
		final int displayHeight = utilities.Constants.getScreenHeight();
		final int displayWidth = utilities.Constants.getScreenWidth();

		// Calculate the aspect ratio of the display window
		final float displayRatio = (float) displayWidth / (float) displayHeight;

		// If the screen is slimmer than the standard ratio (16:9), make a new,
		// smaller, width but keep the standard height
		if (displayRatio < utilities.Constants.VIEWPORT_RATIO) {
			width = (utilities.Constants.VIEWPORT_HEIGHT / displayHeight)
					* displayWidth;
			height = utilities.Constants.VIEWPORT_HEIGHT;
		}
		// If the screen is wider than or equally wide to the standard ratio,
		// keep the standard width and make a new, smaller, height
		else {
			width = utilities.Constants.VIEWPORT_WIDTH;
			height = (utilities.Constants.VIEWPORT_WIDTH / displayWidth)
					* displayHeight;
		}

		// Initialize the projection and matrix modes
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	/**
	 * @see components.IComponent#instantiatePermanentEntities()
	 */
	@Override
	public void instantiatePermanentEntities() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see components.IComponent#cleanup()
	 */
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
	}

	/**
	 * Commands each instantiated Entity to render itself
	 * 
	 * @see components.IComponent#update()
	 */
	@Override
	public void update() {

		final List<IModel> models = Manager.getModels();

		GL11.glGetError();

		// Clear the color buffer and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// Let each entity render itself
		for (final IModel m : models) {

			m.getGeometry().render();
		}
	}

	/**
	 * @see components.IComponent#controllerAdded(core.Entity)
	 */
	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see components.IComponent#controllerRemoved(core.Entity)
	 */
	@Override
	public void controllerRemoved(final IController controller) {
		// TODO Auto-generated method stub
	}

}

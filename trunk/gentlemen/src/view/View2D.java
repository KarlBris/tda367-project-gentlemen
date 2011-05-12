package view;

import java.util.List;

import model.IMainModel;
import model.entities.IModel;

import org.lwjgl.opengl.GL11;

import utilities.Constants;
import utilities.Tools;

public class View2D implements IView {

	public IMainModel mainModel;

	public View2D(IMainModel mainModel) {
		this.mainModel = mainModel;
	}

	@Override
	public void initialize() {
		// The width and height of the viewport in game units
		float width;
		float height;

		// Initializes the display window size
		final int displayHeight = Tools.getScreenHeight();
		final int displayWidth = Tools.getScreenWidth();

		// Calculate the aspect ratio of the display window
		final float displayRatio = (float) displayWidth / (float) displayHeight;

		// If the screen is slimmer than the standard ratio (16:9), make a new,
		// smaller, width but keep the standard height
		if (displayRatio < Constants.VIEWPORT_RATIO) {
			width = (Constants.VIEWPORT_HEIGHT / displayHeight) * displayWidth;
			height = Constants.VIEWPORT_HEIGHT;
		}
		// If the screen is wider than or equally wide to the standard ratio,
		// keep the standard width and make a new, smaller, height
		else {
			width = Constants.VIEWPORT_WIDTH;
			height = (Constants.VIEWPORT_WIDTH / displayWidth) * displayHeight;
		}

		// Initialize the projection and matrix modes
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	@Override
	public void render() {
		GL11.glGetError();

		// Clear the color buffer and depth buffer
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// Render all models
		final List<IModel> models = mainModel.getModels();

		for (final IModel m : models) {

			// TODO: Implement a better/more flexible solution
			m.getGeometry().render();
		}
	}

}

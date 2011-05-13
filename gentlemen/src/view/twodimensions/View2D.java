package view.twodimensions;

import java.util.List;

import model.IMainModel;
import model.common.IModel;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import common.geometry.IGeometry;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import view.IView;

public class View2D implements IView {

	public IMainModel mainModel;

	public View2D(final IMainModel mainModel) {
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

		// Enable depth buffer
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL); // Pass test if pixel depth is less
											// than or equal to the currently
											// written value
		GL11.glDepthMask(true);
	}

	@Override
	public void render() {
		GL11.glGetError();

		// Clear the color buffer and depth buffer
		Color clearColor = Constants.BACKGROUND_COLOR;

		GL11.glClearColor(clearColor.getRed(), clearColor.getGreen(),
				clearColor.getBlue(), 0.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		// Render all models
		final List<IModel> models = mainModel.getModels();

		for (final IModel m : models) {

			IGeometry geometry = m.getGeometry();

			if (geometry.isVisible()) {
				// Set color
				Color color = geometry.getColor();

				GL11.glColor3f(color.getRed(), color.getGreen(),
						color.getBlue());

				// Set position and angle
				Vector2f position = geometry.getPosition();
				float angle = geometry.getAngle();
				Vector2f scale = geometry.getScale();

				GL11.glLoadIdentity();
				GL11.glTranslatef(position.x, position.y, 0.0f);
				GL11.glRotatef(angle * Constants.TO_DEGREES, 0.0f, 0.0f, -1.0f);
				GL11.glScalef(scale.x, scale.y, 1.0f);

				// Begin rendering triangles
				GL11.glBegin(GL11.GL_TRIANGLES);

				// Render the geometry as triangles
				Vector2f[] vertices = geometry.getVertices();
				Vector2f[] uvs = geometry.getUvs();
				float depth = geometry.getDepth();

				for (final Vector2f vertex : vertices) {
					GL11.glVertex3f(vertex.x, vertex.y, depth);
				}

				for (final Vector2f uv : uvs) {
					GL11.glTexCoord2f(uv.x, uv.y);
				}

				// End rendering triangles
				GL11.glEnd();
			}
		}
	}

}

package utilities;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;

public class Constants {

	public static final float PI = (float) Math.PI;
	public static final float TWO_PI = (float) (Math.PI * 2);
	public static final float TO_DEGREES = (float) (180.0 / Math.PI);

	public static final int FRAMES_PER_SECOND = 60;
	public static final float DELTA_TIME = 1.0f / FRAMES_PER_SECOND;

	public static final int DEFAULT_SCREEN_WIDTH = 960;
	public static final int DEFAULT_SCREEN_HEIGHT = 540;
	public static final float VIEWPORT_RATIO = 16.0f / 9.0f;
	public static final float VIEWPORT_WIDTH = 22.0f;
	public static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH / VIEWPORT_RATIO;

	public static final float GEOMETRY_TO_PHYSICS_INTERPOLATION = 0.8f;

	public static final float PLAYER_RETICLE_DISTANCE = 1.0f;
	public static final float PLAYER_BALL_CARRYING_DISTANCE = 1.0f;

	public static final float FLAG_PICK_UP_DISTANCE = 0.8f;

	public static final float LETHAL_BALL_SPEED = 1.0f;
	public static final float BALL_PICK_UP_DISTANCE = 1.0f;
	public static final float BALL_THROW_SPEED = 20.0f;

	public static final int NETWORK_PORT = 13781;

	public static final Color TEAM_ONE_COLOR = Color.RED;
	public static final Color TEAM_TWO_COLOR = Color.BLUE;

	/**
	 * @return the current screen width in pixels
	 */
	public static int getScreenWidth() {
		if (Display.getDisplayMode() != null) {
			return Display.getDisplayMode().getWidth();
		}

		return 0;
	}

	/**
	 * @return the current screen height in pixels
	 */
	public static int getScreenHeight() {
		if (Display.getDisplayMode() != null) {
			return Display.getDisplayMode().getHeight();
		}

		return 0;
	}

	/**
	 * Converts a pixel in screen-space to a point in viewport-space
	 * 
	 * @param pixelX
	 *            x-coordinate in screen-space
	 * @param pixelY
	 *            y-coordinate in screen-space
	 * @return a point in viewport-space
	 */
	public static Vector2f screenToViewport(final int pixelX, final int pixelY) {
		return new Vector2f(
				(pixelX + 0.5f) / getScreenWidth() * VIEWPORT_WIDTH,
				(pixelY + 0.5f) / getScreenHeight() * VIEWPORT_HEIGHT);
	}

	/**
	 * Converts a point in viewport-space to a pixel in screen-space
	 * 
	 * @param position
	 *            a point in viewport-space
	 * @return a pixel in screen-space
	 */
	public static Point viewportToScreen(final Vector2f position) {
		return new Point(
				(int) (position.x / VIEWPORT_WIDTH * getScreenWidth()),
				(int) (position.y / VIEWPORT_HEIGHT * getScreenHeight()));
	}
}

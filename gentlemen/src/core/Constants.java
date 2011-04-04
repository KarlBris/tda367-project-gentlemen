package core;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;

public class Constants {
	
	public static float PI = (float)Math.PI;
	public static float TWO_PI = (float)(Math.PI * 2);
	public static float TO_DEGREES = (float)(180.0 / Math.PI);
	
	public static int FRAMES_PER_SECOND = 60;
	public static float DELTA_TIME = 1.0f / (float)FRAMES_PER_SECOND;
	
	public static int DEFAULT_SCREEN_WIDTH = 960;
	public static int DEFAULT_SCREEN_HEIGHT = 540;
	public static float VIEWPORT_RATIO = 16.0f / 9.0f;
	public static float VIEWPORT_WIDTH = 22.0f;
	public static float VIEWPORT_HEIGHT = VIEWPORT_WIDTH / VIEWPORT_RATIO;
	
	// Gameplay
	public static float GEOMETRY_TO_PHYSICS_INTERPOLATION = 0.1f;
	
	public static int getScreenWidth() {
		if (Display.getDisplayMode() != null) {
			return Display.getDisplayMode().getWidth();
		}
		
		return 0;
	}
	
	public static int getScreenHeight() {
		if (Display.getDisplayMode() != null) {
			return Display.getDisplayMode().getHeight();
		}
		
		return 0;
	}
	
	public static Vector2f screenToViewport(int pixelX, int pixelY) {
		return new Vector2f(((float)pixelX + 0.5f) / getScreenWidth() * VIEWPORT_WIDTH,
							((float)pixelY + 0.5f) / getScreenHeight() * VIEWPORT_HEIGHT);
	}
	
	public static Point viewportToScreen(Vector2f position) {
		return new Point((int)(position.x / VIEWPORT_WIDTH * getScreenWidth()),
						 (int)(position.y / VIEWPORT_HEIGHT * getScreenHeight()));
	}
}

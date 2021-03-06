package utilities;

import java.io.File;

import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;

/**
 * This class contains static methods used by many parts of the game
 */
public final class Tools {

	/**
	 * Clamps a value to the range [min, max]
	 * 
	 * @param value
	 *            the value to clamp
	 * @param min
	 *            the minimum value
	 * @param max
	 *            the maximum value
	 * @return the clamped value
	 */
	public static float clampValue(final float value, final float min,
			final float max) {
		if (value < min) {
			return min;
		} else if (value > max) {
			return max;
		}

		return value;
	}

	/**
	 * Wraps an angle to the range [0, TWO_PI)
	 * 
	 * @param angle
	 *            the angle to wrap
	 * @return the wrapped angle
	 */
	public static float wrapAngle(float angle) {
		angle = angle % Constants.TWO_PI;

		if (angle < 0.0f) {
			angle += Constants.TWO_PI;
		}

		return angle;
	}

	/**
	 * Calculates the closest delta (from->to) between two angles
	 * 
	 * @param from
	 *            from angle
	 * @param to
	 *            to angle
	 * @return the delta, in radians
	 */
	public static float closestAngleDelta(float from, float to) {
		from = wrapAngle(from);
		to = wrapAngle(to);

		final float directDelta = to - from;
		final float indirectDelta = -Math.signum(directDelta)
				* (Constants.TWO_PI - Math.abs(directDelta));

		if (Math.abs(directDelta) < Math.abs(indirectDelta)) {
			return directDelta;
		} else {
			return indirectDelta;
		}
	}

	/**
	 * Converts an angle to a unit vector.
	 * 
	 * @param angle
	 *            the angle that is to be converted to a Vector
	 * 
	 * @return the resulting unit vector
	 */
	public static Vector2f angleToVector(float angle) {
		final Vector2f resVec = new Vector2f();

		// Safety measures, ensures error-free computing later
		if (Float.isNaN(angle) || Float.isInfinite(angle)) {
			angle = 0.0f;
		}

		// Compute the x and y values of resVec
		resVec.x = (float) Math.cos(angle);
		resVec.y = (float) -Math.sin(angle);

		return resVec;
	}

	/**
	 * Converts a vector to an angle
	 * 
	 * @param vector
	 *            the vector
	 * @return an angle in the range [0, TWO_PI]
	 */
	public static float vectorToAngle(final Vector2f vector) {
		// Calculate the angle from the x-axis to the vector
		float angle = Vector2f.angle(vector, new Vector2f(1.0f, 0.0f));

		// Avoid error that can be generated by the Vector2f.angle() method
		if (Float.isNaN(angle) || Float.isInfinite(angle)) {
			angle = 0.0f;
		}

		// To take in to account if the angle is between PI and two PI
		if (vector.y > 0) {
			angle = Constants.TWO_PI - angle;
		}

		return angle;
	}

	/**
	 * Returns the delta vector between two points
	 * 
	 * @param source
	 *            the from point
	 * @param destination
	 *            the to point
	 * @return the delta
	 */
	public static Vector2f vectorBetween(final Vector2f source,
			final Vector2f destination) {

		final Vector2f resVect = new Vector2f();

		Vector2f.sub(destination, source, resVect);

		return resVect;
	}

	/**
	 * Returns the distance between two points
	 * 
	 * @param source
	 * @param destination
	 * @return the distance
	 */
	public static float distanceBetween(final Vector2f source,
			final Vector2f destination) {

		return vectorBetween(source, destination).length();
	}

	/**
	 * Converts a normal vector to a physics vector
	 * 
	 * @param input
	 *            the normal vector to convert
	 * @return the converted vector
	 */
	public static Vec2 toPhysicsVector(final Vector2f input) {
		return new Vec2(input.x, input.y);
	}

	/**
	 * Converts a physics vector to a normal vector
	 * 
	 * @param input
	 *            the physics vector to convert
	 * @return the converted vector
	 */
	public static Vector2f toNormalVector(final Vec2 input) {
		return new Vector2f(input.x, input.y);
	}

	/**
	 * Clone a Vector2f
	 * 
	 * @param vector
	 *            , is the vector to clone
	 * @return a cloned vector
	 */
	public static Vector2f cloneVector(final Vector2f vector) {
		return new Vector2f(vector);
	}

	/**
	 * @return the current screen width in pixels
	 */
	public static int getScreenWidth() {
		if (Display.isCreated()) {
			return Display.getDisplayMode().getWidth();
		}

		return Constants.DEFAULT_SCREEN_WIDTH;
	}

	/**
	 * @return the current screen height in pixels
	 */
	public static int getScreenHeight() {
		if (Display.isCreated()) {
			return Display.getDisplayMode().getHeight();
		}

		return Constants.DEFAULT_SCREEN_HEIGHT;
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
		return new Vector2f((pixelX + 0.5f) / getScreenWidth()
				* Constants.VIEWPORT_WIDTH, (pixelY + 0.5f) / getScreenHeight()
				* Constants.VIEWPORT_HEIGHT);
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
				(int) (position.x / Constants.VIEWPORT_WIDTH * getScreenWidth()),
				(int) (position.y / Constants.VIEWPORT_HEIGHT * getScreenHeight()));
	}

	/**
	 * Returns a random Vector2f in the given area
	 * 
	 * @param start
	 *            the starting point of the area
	 * @param size
	 *            the size of the area
	 * @return the randomized vector
	 */
	public static Vector2f randomVectorInArea(final Vector2f start,
			final Vector2f size) {
		float posX = (float) Math.random() * size.x + start.x;
		float posY = (float) Math.random() * size.y + start.y;

		return new Vector2f(posX, posY);
	}

	/**
	 * Returns a random Vector2f, in the given area, which is far away enough
	 * from provided restriction vectors
	 * 
	 * @param start
	 *            the starting point of the area
	 * @param size
	 *            the size of the area
	 * @param restrictions
	 *            the restriction vectors
	 * @return the randomized vector
	 */
	public static Vector2f randomVectorInArea(final Vector2f start,
			final Vector2f size, final Vector2f[] restrictions) {
		float posX, posY;
		Vector2f newVector = new Vector2f(0.0f, 0.0f);
		boolean tooClose = true;
		int randomizations = 0;

		while (tooClose == true) {
			posX = (float) Math.random() * size.x + start.x;
			posY = (float) Math.random() * size.y + start.y;
			newVector.set(posX, posY);

			for (Vector2f restriction : restrictions) {
				if (distanceBetween(newVector, restriction) < 4 && randomizations < 100) {
					tooClose = true;
					randomizations++;
					break;
				} else {
					tooClose = false;
				}
			}

		}
		return newVector;
	}

	/**
	 * Tests if floating point values are equal (within the epsilon threshold)
	 * 
	 * @param a
	 *            the first floating point value
	 * @param b
	 *            the second floating point value
	 * @return true if equal, false otherwise
	 */
	public static boolean floatsEqual(final float a, final float b) {
		return Math.abs(a - b) <= Constants.EPSILON;
	}

	/**
	 * Test if the two vectors are equal (within the epsilon threshold)
	 * 
	 * @param vector1
	 *            , is the first vector to be tested
	 * @param vector2
	 *            , is the second vector the vector1 is compared to
	 * @return true if the vectors are equal to the specification described in
	 *         the description, otherwise false.
	 */
	public static boolean vectorsEqual(final Vector2f vector1,
			final Vector2f vector2) {
		return Tools.distanceBetween(vector1, vector2) <= Constants.EPSILON;
	}

	/**
	 * Sets the lwjgl native library path according to what operating system is running
	 */
	public static void identifyOS() {
		
		if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_LINUX) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "linux");
		} else if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_MACOSX) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "macosx");
		} else if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_WINDOWS) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "windows");
		}
		
	}
}
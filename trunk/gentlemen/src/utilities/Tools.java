package utilities;

import org.lwjgl.util.vector.Vector2f;

import core.Constants;

public class Tools {

	public static float wrapAngle(float angle) {
		angle = angle % Constants.TWO_PI;

		if (angle < 0.0f) {
			angle += Constants.TWO_PI;
		}

		return angle;
	}

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

		// Don't normalize if length is zero
		if (resVec.length() != 0) {
			resVec.normalise(resVec);
		}

		return resVec;
	}
}

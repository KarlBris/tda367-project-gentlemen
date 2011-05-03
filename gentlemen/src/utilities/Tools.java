package utilities;

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

		float directDelta = to - from;
		float indirectDelta = -Math.signum(directDelta)
				* (Constants.TWO_PI - Math.abs(directDelta));

		if (Math.abs(directDelta) < Math.abs(indirectDelta)) {
			return directDelta;
		} else {
			return indirectDelta;
		}
	}

}

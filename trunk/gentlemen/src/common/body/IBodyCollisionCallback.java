package common.body;

import org.lwjgl.util.vector.Vector2f;

public interface IBodyCollisionCallback {
	public void collisionOccured(Vector2f otherPosition,
			Vector2f otherVelocity, Vector2f collisionPoint);
}

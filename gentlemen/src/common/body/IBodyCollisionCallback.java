package common.body;

import org.lwjgl.util.vector.Vector2f;

public interface IBodyCollisionCallback {
	public void collisionOccured(IBody otherBody, Vector2f collisionPoint);
}

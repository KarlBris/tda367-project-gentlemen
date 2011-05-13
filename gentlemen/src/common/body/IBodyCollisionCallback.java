package common.body;


import org.lwjgl.util.vector.Vector2f;



public interface IBodyCollisionCallback {
	public void collisionOccured(Body otherBody, Vector2f collisionPoint);
}

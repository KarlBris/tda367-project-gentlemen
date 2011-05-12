package controller.components;

import org.lwjgl.util.vector.Vector2f;

import core.body.Body;

public interface IBodyCollisionCallback {
	public void collisionOccured(Body otherBody, Vector2f collisionPoint);
}

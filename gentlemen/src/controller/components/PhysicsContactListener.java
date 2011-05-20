package controller.components;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.WorldManifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.contacts.Contact;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;

import common.body.IBody;
import common.body.IBodyCollisionCallback;

public final class PhysicsContactListener implements ContactListener {

	@Override
	public void beginContact(final Contact arg0) {

		// Get the world collision manifold
		WorldManifold worldManifold = new WorldManifold();

		arg0.getWorldManifold(worldManifold);

		// Calculate the average manifold point
		Vec2 point = worldManifold.points[0];

		// Send collision callbacks
		IBody bodyA = (IBody) arg0.getFixtureA().getBody().getUserData();
		IBody bodyB = (IBody) arg0.getFixtureB().getBody().getUserData();

		IBodyCollisionCallback callbackA = bodyA.getCollisionCallback();
		IBodyCollisionCallback callbackB = bodyB.getCollisionCallback();

		Vector2f collisionPoint = Tools.toNormalVector(point);

		if (callbackA != null) {
			callbackA.collisionOccured(bodyB, collisionPoint);
		}

		if (callbackB != null) {
			callbackB.collisionOccured(bodyA, collisionPoint);
		}
	}

	@Override
	public void endContact(final Contact arg0) {
		// Do nothing
	}

	@Override
	public void postSolve(final Contact arg0, final ContactImpulse arg1) {
		// Do nothing
	}

	@Override
	public void preSolve(final Contact arg0, final Manifold arg1) {
		// Do nothing
	}

}

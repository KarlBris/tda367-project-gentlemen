package common.body;

import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector2f;

public final class NullBody implements IBody {

	@Override
	public void addToWorld(final World world) {
		// Do nothing
	}

	@Override
	public void removeFromWorld(final World world) {
		// Do nothing
	}

	@Override
	public Vector2f getVelocity() {
		// Do nothing
		return null;
	}

	@Override
	public IBodyCollisionCallback getCollisionCallback() {
		// Do nothing
		return null;
	}

	@Override
	public void setCollisionCallback(
			final IBodyCollisionCallback collisionCallback) {
		// Do nothing
	}

	@Override
	public float getMass() {
		// Do nothing
		return 0;
	}

	@Override
	public void setMass(final float mass) {
		// Do nothing
	}

	@Override
	public Vector2f getPosition() {
		// Do nothing
		return null;
	}

	@Override
	public void setPosition(final Vector2f position) {
		// Do nothing
	}

	@Override
	public float getAngle() {
		// Do nothing
		return 0;
	}

	@Override
	public void setAngle(final float angle) {
		// Do nothing
	}

	@Override
	public Vector2f getAcceleration() {
		// Do nothing
		return null;
	}

	@Override
	public float getAngularAcceleration() {
		// Do nothing
		return 0;
	}

	@Override
	public float getAngularVelocity() {
		// Do nothing
		return 0;
	}

	@Override
	public float getTorque() {
		// Do nothing
		return 0;
	}

	@Override
	public Vector2f getVelocityAtPoint(final Vector2f point) {
		// Do nothing
		return null;
	}

	@Override
	public void clearVelocity() {
		// Do nothing
	}

	@Override
	public void clearAngularVelocity() {
		// Do nothing
	}

	@Override
	public void applyForce(final Vector2f force) {
		// Do nothing
	}

	@Override
	public void applyTorque(final float torque) {
		// Do nothing
	}

	@Override
	public void applyVelocityChange(final Vector2f velocityChange) {
		// Do nothing
	}

	@Override
	public void applyAngularVelocityChange(final float velocityChange) {
		// Do nothing
	}
}

package common.body;

import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector2f;

public interface IBody {

	/**
	 * Adds the body to a physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void addToWorld(World world);

	/**
	 * Removes the body from the physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void removeFromWorld(World world);

	/**
	 * @return the collision callback object this body reports to
	 */
	public IBodyCollisionCallback getCollisionCallback();

	/**
	 * Sets the collision callback object this body will report to
	 * 
	 * @param collisionCallback
	 *            the collision callback object
	 */
	public void setCollisionCallback(
			final IBodyCollisionCallback collisionCallback);

	/**
	 * @return the mass of the body
	 */
	public float getMass();

	/**
	 * Sets the mass of the body
	 * 
	 * @param mass
	 *            the new mass
	 */
	public void setMass(final float mass);

	/**
	 * @return the body's current position
	 */
	public Vector2f getPosition();

	/**
	 * Sets the body's position
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(final Vector2f position);

	/**
	 * @return the current angle of the body
	 */
	public float getAngle();

	/**
	 * Sets the angle of the body
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(final float angle);

	/**
	 * @return the current linear acceleration of the body
	 */
	public Vector2f getAcceleration();

	/**
	 * @return the current angular acceleration of the body
	 */
	public float getAngularAcceleration();

	/**
	 * @return the current linear velocity of the body
	 */
	public Vector2f getVelocity();

	/**
	 * @return the current angular velocity of the body
	 */
	public float getAngularVelocity();

	/**
	 * @return the current torque of the body
	 */
	public float getTorque();

	/**
	 * Returns the velocity at a world-space point
	 * 
	 * @param point
	 *            the world-space point
	 * @return the velocity
	 */
	public Vector2f getVelocityAtPoint(final Vector2f point);

	/**
	 * Clears the linear velocity of the body
	 */
	public void clearVelocity();

	/**
	 * Clears the angular velocity of the body
	 */
	public void clearAngularVelocity();

	/**
	 * Applies a force to the center of mass of the body
	 * 
	 * @param force
	 *            the force
	 */
	public void applyForce(final Vector2f force);

	/**
	 * Applies torque to the body
	 * 
	 * @param torque
	 *            the torque
	 */
	public void applyTorque(final float torque);

	/**
	 * Applies a velocity change to the body
	 * 
	 * @param velocity
	 *            the velocity to add
	 */
	public void applyVelocityChange(final Vector2f velocityChange);

	/**
	 * Applies an angular velocity change to the body
	 * 
	 * @param velocityChange
	 *            the angular velocity to add
	 */
	public void applyAngularVelocityChange(final float velocityChange);

}

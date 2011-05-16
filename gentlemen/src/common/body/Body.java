package common.body;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

/**
 * The physical representation of a model in the game world
 */
public class Body implements IBody {

	// Body properties
	private final IBodyShape shape;
	private final boolean isStatic;

	private float mass = 0.0f;
	private float damping = Constants.BODY_DEFAULT_DAMPING;
	private float angularDamping = Constants.BODY_DEFAULT_ANGULAR_DAMPING;

	// External references
	private IBodyCollisionCallback collisionCallback;

	private org.jbox2d.dynamics.Body rigidbody;

	/**
	 * Initializes a static/dynamic body
	 * 
	 * @param shape
	 *            the body shape of the body
	 * 
	 * @param mass
	 *            the mass of the body; if 0 the body will be static, otherwise
	 *            dynamic
	 */
	public Body(final IBodyShape shape, final float mass) {
		this.shape = shape;

		if (mass == 0.0f) {
			this.isStatic = true;
		} else {
			this.isStatic = false;
		}

		this.mass = mass;
	}

	/**
	 * Initializes a static/dynamic body
	 * 
	 * @param shape
	 *            the body shape of the body
	 * 
	 * @param mass
	 *            the mass of the body; if 0 the body will be static, otherwise
	 *            dynamic
	 * @param damping
	 *            the desired linear damping of the body
	 */
	public Body(final IBodyShape shape, final float mass, final float damping) {
		this(shape, mass);

		this.damping = damping;
	}

	/**
	 * @return false if the body is movable, true otherwise
	 */
	public boolean isStatic() {
		return isStatic;
	}

	/**
	 * @return the mass of the body
	 */
	public float getMass() {
		return mass;
	}

	/**
	 * Sets the mass of the body
	 * 
	 * @param mass
	 *            the new mass
	 */
	public void setMass(final float mass) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		// Set mass
		if (!this.isStatic) {
			this.mass = mass;

			final MassData data = new MassData();

			rigidbody.getMassData(data);
			data.mass = this.mass;
			rigidbody.setMassData(data);
		}
	}

	/**
	 * @return the linear damping of the body
	 */
	public float getDamping() {
		return damping;
	}

	/**
	 * Sets the linear damping of the body
	 * 
	 * @param damping
	 *            the linear damping
	 */
	public void setDamping(final float damping) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		// Set damping
		this.damping = damping;

		rigidbody.setLinearDamping(this.damping);
	}

	/**
	 * @return the angular damping of the body
	 */
	public float getAngularDamping() {
		return angularDamping;
	}

	/**
	 * Sets the angular damping of the body
	 * 
	 * @param angularDamping
	 *            the angular damping
	 */
	public void setAngularDamping(final float angularDamping) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		// Set angular damping
		this.angularDamping = angularDamping;

		rigidbody.setAngularDamping(this.angularDamping);
	}

	/**
	 * @return the collision callback object this body reports to
	 */
	public IBodyCollisionCallback getCollisionCallback() {
		return collisionCallback;
	}

	/**
	 * Sets the collision callback object this body will report to
	 * 
	 * @param collisionCallback
	 *            the collision callback object
	 */
	public void setCollisionCallback(
			final IBodyCollisionCallback collisionCallback) {
		this.collisionCallback = collisionCallback;
	}

	/**
	 * @return the body's current position
	 */
	public Vector2f getPosition() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		return Tools.toNormalVector(rigidbody.getWorldCenter());
	}

	/**
	 * Sets the body's position
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(final Vector2f position) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.setTransform(Tools.toPhysicsVector(position),
				rigidbody.getAngle());
	}

	/**
	 * @return the current angle of the body
	 */
	public float getAngle() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		return Tools.wrapAngle(-rigidbody.getAngle());
	}

	/**
	 * Sets the angle of the body
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(final float angle) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.setTransform(rigidbody.getWorldCenter(), -angle);
	}

	@Override
	public void addToWorld(final World world) {
		// Create body definition
		final BodyDef bodyDef = new BodyDef();

		if (isStatic) {
			bodyDef.type = BodyType.STATIC;
		} else {
			bodyDef.type = BodyType.DYNAMIC;
		}

		// Create rigidbody
		rigidbody = world.createBody(bodyDef);

		rigidbody.setUserData(this);

		// Set body properties
		rigidbody.createFixture(shape.getShape(), 1.0f);

		try {

			setMass(mass);
			setDamping(damping);
			setAngularDamping(angularDamping);

		} catch (BodyNotInitializedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeFromWorld(final World world) {
		world.destroyBody(rigidbody);
	}

	/**
	 * @return the current acceleration of the body
	 */
	public Vector2f getAcceleration() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		final Vector2f acceleration = Tools.toNormalVector(rigidbody.m_force);

		acceleration.scale(1.0f / getMass());

		return acceleration;
	}

	/**
	 * @return the current linear velocity of the body
	 */
	public Vector2f getVelocity() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		return Tools.toNormalVector(rigidbody.getLinearVelocity());
	}

	/**
	 * Returns the velocity at a world-space point
	 * 
	 * @param point
	 *            the world-space point
	 * @return the velocity
	 */
	public Vector2f getVelocityAtPoint(final Vector2f point) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		final Vec2 velocityAtPoint = new Vec2(0.0f, 0.0f);

		rigidbody.getLinearVelocityFromWorldPointToOut(
				Tools.toPhysicsVector(point), velocityAtPoint);

		return Tools.toNormalVector(velocityAtPoint);
	}

	/**
	 * Clears the linear velocity of the body
	 */
	public void clearVelocity() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.setLinearVelocity(new Vec2(0.0f, 0.0f));
	}

	/**
	 * Clears the angular velocity of the body
	 */
	public void clearAngularVelocity() {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.setAngularVelocity(0.0f);
	}

	/**
	 * Applies a force to the center of mass of the body
	 * 
	 * @param force
	 *            the force
	 */
	public void applyForce(final Vector2f force) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.applyForce(Tools.toPhysicsVector(force),
				rigidbody.getWorldCenter());
	}

	/**
	 * Applies a torque to the body
	 * 
	 * @param torque
	 *            the torque
	 */
	public void applyTorque(final float torque) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		rigidbody.applyTorque(-torque);
	}

	/**
	 * Applies a velocity change to the body
	 * 
	 * @param velocity
	 *            the velocity to add
	 */
	public void applyVelocityChange(final Vector2f velocity) {
		if (rigidbody == null) {
			throw new BodyNotInitializedException();
		}

		final Vec2 impulse = Tools.toPhysicsVector(velocity);

		impulse.mul(mass);

		rigidbody.applyLinearImpulse(impulse, rigidbody.getWorldCenter());
	}
}
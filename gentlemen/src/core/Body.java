package core;

import org.jbox2d.collision.shapes.MassData;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;

/**
 * The physical representation of a model in the game world
 */
public class Body {

	// Body properties
	private float mass = 0.0f;

	private IBodyShape shape;

	// References
	private org.jbox2d.dynamics.Body rigidbody;

	/**
	 * Initializes a static body
	 * 
	 * @param width
	 *            the width of the body
	 * @param height
	 *            the height of the body
	 */
	public Body(final IBodyShape shape) {
		this.shape = shape;
	}

	/**
	 * Initializes a static/dynamic body
	 * 
	 * @param width
	 *            the width of the body
	 * @param height
	 *            the height of the body
	 * @param mass
	 *            the mass of the body; if 0 the body will be static, otherwise
	 *            dynamic
	 */
	public Body(final IBodyShape shape, final float mass) {
		this(shape);

		this.mass = mass;
	}

	/**
	 * Adds the body to a physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void addToWorld(final World world) {
		BodyDef bodyDef = new BodyDef();

		if (mass == 0.0f) {
			bodyDef.type = BodyType.STATIC;
		} else {
			bodyDef.type = BodyType.DYNAMIC;
		}

		rigidbody = world.createBody(bodyDef);

		rigidbody.createFixture(shape.getShape(), 1.0f);

		setMass(mass);
	}

	/**
	 * Removes the body from the physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void removeFromWorld(final World world) {
		world.destroyBody(rigidbody);
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
		if (rigidbody != null) {
			if (rigidbody.getType() == BodyType.DYNAMIC) {
				// Set mass
				this.mass = mass;

				// Update rigidbody
				MassData data = new MassData();

				rigidbody.getMassData(data);
				data.mass = this.mass;
				rigidbody.setMassData(data);
			}
		}
	}

	/**
	 * @return the body's current position
	 */
	public Vector2f getPosition() {
		if (rigidbody != null) {
			Vec2 position = rigidbody.getWorldCenter();

			return new Vector2f(position.x, position.y);
		}

		return new Vector2f(0.0f, 0.0f);
	}

	/**
	 * Sets the body's position
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(final Vector2f position) {
		if (rigidbody != null) {
			rigidbody.setTransform(new Vec2(position.x, position.y),
					rigidbody.getAngle());
		}
	}

	/**
	 * @return the current angle of the body
	 */
	public float getAngle() {
		if (rigidbody != null) {
			return Tools.wrapAngle(-rigidbody.getAngle());
		}

		return 0.0f;
	}

	/**
	 * Sets the angle of the body
	 * 
	 * @param angle
	 *            the new angle
	 */
	public void setAngle(final float angle) {
		if (rigidbody != null) {
			rigidbody.setTransform(rigidbody.getWorldCenter(), -angle);
		}
	}

	/**
	 * @return the current linear velocity of the body
	 */
	public Vector2f getVelocity() {
		Vec2 velocity = rigidbody.getLinearVelocity();

		return new Vector2f(velocity.x, velocity.y);
	}

	/**
	 * Clears the linear velocity of the body
	 */
	public void clearVelocity() {
		if (rigidbody != null) {
			rigidbody.setLinearVelocity(new Vec2(0.0f, 0.0f));
		}
	}

	/**
	 * Applies a force to the center of mass of the body
	 * 
	 * @param force
	 *            the force
	 */
	public void applyForce(final Vector2f force) {
		if (rigidbody != null) {
			rigidbody.applyForce(new Vec2(force.x, force.y),
					rigidbody.getWorldCenter());
		}
	}

	/**
	 * Applies a torque to the body
	 * 
	 * @param torque
	 *            the torque
	 */
	public void applyTorque(final float torque) {
		if (rigidbody != null) {
			rigidbody.applyTorque(-torque);
		}
	}

}

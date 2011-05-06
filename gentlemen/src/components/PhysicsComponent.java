package components;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import utilities.Constants;

import controllers.IController;
import core.Body;

public class PhysicsComponent implements IComponent {

	private World world;

	@Override
	public void initialize() {
		// Create the physics world
		world = new World(new Vec2(0.0f, 0.0f), true);
	}

	@Override
	public void instantiatePermanentEntities() {
	}

	@Override
	public void cleanup() {
	}

	@Override
	public void update() {
		// Step forward in time
		world.step(Constants.DELTA_TIME, 5, 5);

		// Clear all forces to avoid accumulation from frame to frame
		world.clearForces();
	}

	@Override
	public void controllerAdded(final IController controller) {
		// Create the body and add it to the world
		Body body = controller.getModel().getBody();

		if (body != null) {
			body.addToWorld(world);
		}
	}

	@Override
	public void controllerRemoved(final IController controller) {
		// Destroy the body and remove it from the world
		Body body = controller.getModel().getBody();

		if (body != null) {
			body.removeFromWorld(world);
		}
	}

}

package controller.components;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import common.body.Body;

import utilities.Constants;
import controller.entities.IController;

public class PhysicsComponent implements IComponent {

	private final World world = new World(new Vec2(0.0f, 0.0f), true);

	private final PhysicsContactListener listener = new PhysicsContactListener();

	@Override
	public void initialize() {
		// Attach the contact listener to the world
		world.setContactListener(listener);
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
		final Body body = controller.getModel().getBody();

		if (body != null) {
			body.addToWorld(world);
		}
	}

	@Override
	public void controllerRemoved(final IController controller) {
		// Destroy the body and remove it from the world
		final Body body = controller.getModel().getBody();

		if (body != null) {
			body.removeFromWorld(world);
		}
	}

}

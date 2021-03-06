package controller.components;

import model.common.IModel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import utilities.Constants;
import controller.common.IComponent;
import controller.common.IController;

public final class PhysicsComponent implements IComponent {

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
	public <M extends IModel> void controllerAdded(
			final IController<M> controller) {
		// Create the body and add it to the world
		controller.getModel().getBody().addToWorld(world);
	}

	@Override
	public <M extends IModel> void controllerRemoved(
			final IController<M> controller) {
		// Destroy the body and remove it from the world
		controller.getModel().getBody().removeFromWorld(world);
	}

}

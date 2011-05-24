package model.entities.gameplay;

import static org.junit.Assert.assertTrue;

import model.entities.gameplay.ReticleModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.MainControllerFactory;
import controller.entities.gameplay.KeyboardReticleFactory;

public final class ReticleModelTest {

	private ReticleModel model;

	@Before
	public void setUp() throws Exception {
		model = MainControllerFactory.get()
				.instantiate(new KeyboardReticleFactory()).getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		// Test if the method returns a Body object
		assertTrue(model.getBody() != null);
	}

	@Test
	public void testSetPosition() {
		// Test if setting the prop's position to different values renders any
		// errors.
		Vector2f newPosition = new Vector2f(1.0f, 1.0f);
		model.setPosition(newPosition);

		newPosition = new Vector2f(2.0f, 2.0f);
		model.setPosition(newPosition);

		newPosition = new Vector2f(-1.0f, -1.0f);
		model.setPosition(newPosition);

	}

	@Test
	public void testGetPosition() {

		Vector2f position = new Vector2f(1.0f, 1.0f);
		model.setPosition(position);

		// Test if getting the prop's position returns the correct value
		assertTrue(Tools.vectorsEqual(position, model.getPosition()));

		position = new Vector2f(2.0f, 2.0f);
		model.setPosition(position);

		// Same test with new values
		assertTrue(Tools.vectorsEqual(position, model.getPosition()));

	}

	@Test
	public void testMove() {
		Vector2f oldPosition = model.getPosition();
		Vector2f movement = new Vector2f(3.0f, 3.0f);
		model.move(movement);

		// Test if moving the reticle results in it having a different position.
		assertTrue(Tools.distanceBetween(oldPosition, model.getPosition()) != 0);
	}

}

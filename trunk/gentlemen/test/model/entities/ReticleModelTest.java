package model.entities;

import static org.junit.Assert.assertTrue;

import model.entities.ReticleModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import core.Manager;
import factories.entities.KeyboardReticleFactory;

public class ReticleModelTest {
	private final float epsilon = 0.01f;
	private ReticleModel model;

	@Before
	public void setUp() throws Exception {
		model = (ReticleModel) Manager
				.instantiate(new KeyboardReticleFactory()).getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testPosition() {
		Vector2f newPosition = new Vector2f(3.0f, 3.0f);
		model.setPosition(newPosition);

		// Test if setting the reticle's position gives it the correct values
		assertTrue(Tools.distanceBetween(model.getPosition(), newPosition) <= epsilon);
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

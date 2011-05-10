package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controllers.KeyboardReticleController;
import core.Manager;
import factories.KeyboardReticleFactory;

public class ReticleModelTest {
	private final float precision = 0.01f;
	private KeyboardReticleController controller;
	private ReticleModel model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		controller = (KeyboardReticleController) Manager
				.instantiate(new KeyboardReticleFactory());
		model = (ReticleModel) controller.getModel();
	}

	@After
	public void tearDown() throws Exception {
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
		assertTrue(Tools.distanceBetween(model.getPosition(), newPosition) <= precision);
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

package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controller.MainControllerFactory;
import factories.entities.ShockwaveFactory;

public final class ShockwaveModelTest {

	private ShockwaveModel model;

	@Before
	public void setUp() throws Exception {
		model = MainControllerFactory.get().instantiate(new ShockwaveFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testShockwaveModel() {
		model = MainControllerFactory.get().instantiate(new ShockwaveFactory())
				.getModel();
		assertTrue(!model.isFinished());
		assertTrue(model.getAnimationScalar() == 0.0f);
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
	public void testIsFinished() {
		assertTrue(!model.isFinished());

		// Animate the shockwave throughout its full animation length
		int stepsNeeded = (int) (Constants.SHOCKWAVE_ANIMATION_TIME / Constants.DELTA_TIME) + 5;

		for (int i = 0; i < stepsNeeded; i++) {
			model.update();
		}

		assertTrue(model.isFinished());
	}

	@Test
	public void testUpdateAndGetAnimationScalar() {
		assertTrue(model.getAnimationScalar() == 0.0f);

		model.update();

		assertTrue(model.getAnimationScalar() > 0.0f);
	}

}

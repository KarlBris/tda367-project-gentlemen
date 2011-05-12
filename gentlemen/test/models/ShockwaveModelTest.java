package models;

import static org.junit.Assert.assertTrue;

import model.entities.ShockwaveModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import core.Manager;
import factories.ShockwaveFactory;

public class ShockwaveModelTest {

	private ShockwaveModel model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		model = (ShockwaveModel) Manager.instantiate(new ShockwaveFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testShockwaveModel() {
		assertTrue(!model.isFinished());
		assertTrue(model.getAnimationScalar() == 0.0f);
	}

	@Test
	public void testGetGeometry() {
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(model.getBody() == null);
	}

	@Test
	public void testPosition() {
		model.setPosition(new Vector2f(5.0f, 4.0f));

		assertTrue(model.getPosition().x == 5.0f);
		assertTrue(model.getPosition().y == 4.0f);
	}

	@Test
	public void testUpdateAndGetAnimationScalar() {
		assertTrue(model.getAnimationScalar() == 0.0f);

		model.update();

		assertTrue(model.getAnimationScalar() > 0.0f);
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

}

/**
 * 
 */
package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controllers.PropController;
import core.Manager;
import factories.PropFactory;

public class PropModelTest {
	private final float precision = 0.01f;
	private PropModel model;
	private PropController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = (PropController) Manager.instantiate(new PropFactory());
		model = (PropModel) controller.getModel();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	/**
	 * Test method for
	 * {@link models.PropModel#PropModel(float, float, float, float)}.
	 */
	@Test
	public void testPropModel() {
		// Test if the model has the correct mass
		assertTrue(Math.abs(model.getBody().getMass() - 1.0f) <= precision);

	}

	/**
	 * Test method for {@link models.PropModel#getGeometry()}.
	 */
	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}

	/**
	 * Test method for
	 * {@link models.PropModel#setPosition(org.lwjgl.util.vector.Vector2f)}.
	 */
	@Test
	public void testPosition() {
		Vector2f newPosition = new Vector2f(3.0f, 3.0f);
		model.setPosition(newPosition);

		// Test if setting the prop's position gives it the correct values
		assertTrue(Tools.distanceBetween(model.getPosition(), newPosition) <= precision);
	}

	/**
	 * Test method for {@link models.PropModel#setAngle(float)}.
	 */
	@Test
	public void testAngle() {
		float newAngle = Constants.PI / 2;
		model.setAngle(newAngle);

		// Test if setting the prop's angle gives its body and geometry their
		// correct values
		assertTrue(Math.abs(model.getAngle() - newAngle) <= precision);
	}

}

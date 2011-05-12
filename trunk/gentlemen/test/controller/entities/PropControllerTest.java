package controller.entities;

import static org.junit.Assert.assertTrue;
import model.entities.PropModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;
import core.Manager;
import factories.entities.PropFactory;

public class PropControllerTest {
	private PropModel pm;
	private PropController pc;
	private final float precision = 0.001f;

	@Before
	public void setUp() throws Exception {
		pc = (PropController) Manager.instantiate(new PropFactory());
		pm = (PropModel) pc.getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testPropControllerAndGetModel() {
		// Set up new PropController
		pm = new PropModel(10.0f, 0.0f, 0.0f, 10.0f);
		pc = new PropController(pm);
		// Test the model is the correct one
		assertTrue(pc.getModel() == pm);
	}

	@Test
	public void testSetColorAndGetColor() {
		pc.setColor(Color.BLACK);
		assertTrue(pc.getColor() == Color.BLACK);

		pc.setColor(Color.BLUE);
		assertTrue(pc.getColor() == Color.BLUE);
	}

	@Test
	public void testSetPositionAndGetPosition() {
		Vector2f pos = new Vector2f(10.0f, 10.0f);
		pc.setPosition(pos);
		// Test if the distance is zero (or near zero)
		assertTrue(Tools.distanceBetween(pc.getPosition(), pos) <= precision);

		pos = new Vector2f(0.0f, 10.0f);
		pc.setPosition(pos);
		// Test if the distance is zero (or near zero)
		assertTrue(Tools.distanceBetween(pc.getPosition(), pos) <= precision);
	}

	@Test
	public void testSetAngleAndGetAngle() {
		float angle = 0.1f;
		pc.setAngle(angle);
		// Test the angle is the same
		assertTrue(pc.getAngle() == angle);

		angle = 1.0f;
		pc.setAngle(angle);
		// Test the angle is the same
		assertTrue(pc.getAngle() == angle);
	}

}

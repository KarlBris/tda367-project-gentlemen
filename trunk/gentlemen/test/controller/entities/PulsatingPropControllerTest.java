package controller.entities;

import static org.junit.Assert.assertTrue;
import model.entities.PropModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controller.MainControllerFactory;
import factories.entities.VerticalWallPropFactory;

public class PulsatingPropControllerTest {
	private PropModel pm;
	private PulsatingPropController pc;

	@Before
	public void setUp() throws Exception {
		pc = MainControllerFactory.get().instantiate(
				new VerticalWallPropFactory());
		pm = pc.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testPropControllerAndGetModel() {
		// Set up new PropController
		pm = new PropModel(10.0f, 0.0f, 0.0f, 10.0f);
		pc = new PulsatingPropController(pm, Color.BLACK, Color.BLUE, 4);
		// Test the model is the correct one
		assertTrue(pc.getModel() == pm);
	}

	@Test
	public void testSetPositionAndGetPosition() {
		Vector2f pos = new Vector2f(10.0f, 10.0f);
		pc.setPosition(pos);
		// Test if the distance is zero (or near zero)
		assertTrue(Tools.distanceBetween(pc.getPosition(), pos) <= Constants.EPSILON);

		pos = new Vector2f(0.0f, 10.0f);
		pc.setPosition(pos);
		// Test if the distance is zero (or near zero)
		assertTrue(Tools.distanceBetween(pc.getPosition(), pos) <= Constants.EPSILON);
	}

	@Test
	public void testSetAngleAndGetAngle() {
		float angle = 0.1f;
		pc.setAngle(angle);
		// Test the angle is the same
		assertTrue(Math.abs(pc.getAngle() - angle) <= Constants.EPSILON);

		angle = 1.0f;
		pc.setAngle(angle);
		// Test the angle is the same
		assertTrue(Math.abs(pc.getAngle() - angle) <= Constants.EPSILON);
	}

}

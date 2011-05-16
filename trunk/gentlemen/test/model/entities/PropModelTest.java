package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

import common.body.IBody;
import common.geometry.IGeometry;

import controller.entities.PropController;
import core.Manager;
import factories.entities.PropFactory;

public class PropModelTest {

	private PropModel model;
	private PropController controller;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = Manager.instantiate(new PropFactory());
		model = controller.getModel();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testPropModelFloatFloatFloatFloat() {
		// Test if this constructor works for a number of different parameter
		// values.
		model = new PropModel(1.0f, 1.0f, 1.0f, 1.0f);

		model = new PropModel(0.0f, 0.0f, 0.0f, 0.0f);

		model = new PropModel(-1.0f, -1.0f, -1.0f, -1.0f);
	}

	@Test
	public void testPropModelFloatFloatFloatFloatColor() {
		// Test if this constructor works for a number of different parameter
		// values.
		Color color = new Color(1.0f, 1.0f, 1.0f);
		model = new PropModel(1.0f, 1.0f, 1.0f, 1.0f, color);

		color = new Color(2.0f, 2.0f, 2.0f);
		model = new PropModel(1.0f, 1.0f, 1.0f, 1.0f, color);

		color = new Color(-1.0f, -1.0f, -1.0f);
		model = new PropModel(1.0f, 1.0f, 1.0f, 1.0f, color);

		model = new PropModel(-1.0f, -1.0f, -1.0f, -1.0f, color);
	}

	/**
	 * Test method for {@link model.entities.PropModel#getGeometry()}.
	 */
	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() instanceof IGeometry);
	}

	@Test
	public void testGetBody() {
		// Test if the method returns a Body object
		assertTrue(model.getBody() instanceof IBody);
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
	public void testSetAngle() {
		// Test if setting the prop's angle to different values renders any
		// errors.
		model.setAngle(1.0f);
		model.setAngle(Constants.PI);
		model.setAngle(Constants.TWO_PI - 1);

		model.setAngle(-Constants.PI);
		model.setAngle(-Constants.TWO_PI - 1);

		model.setAngle(0.0f);
	}

	@Test
	public void testGetPosition() {
		Vector2f position = new Vector2f(1.0f, 1.0f);
		model.setPosition(position);

		// Test if getting the prop's position returns the correct value
		assertTrue(Tools.isVectorsEqual(position, model.getPosition()));

		position = new Vector2f(2.0f, 2.0f);
		model.setPosition(position);

		// Same test with new values
		assertTrue(Tools.isVectorsEqual(position, model.getPosition()));

	}

	@Test
	public void testGetAngle() {
		float newAngle = 1.0f;
		model.setAngle(newAngle);

		// Test if getting the prop's angle returns the correct value
		assertTrue(model.getAngle() == newAngle);

		newAngle = Constants.TWO_PI - 1;
		model.setAngle(newAngle);

		// Same test with new values
		assertTrue(model.getAngle() == newAngle);
	}

	@Test
	public void testSetColor() {
		// Test if setting the prop's color to different values renders any
		// errors.
		Color color = new Color(1.0f, 1.0f, 1.0f);
		model.setColor(color);

		color = new Color(2.0f, 2.0f, 2.0f);
		model.setColor(color);

		color = new Color(-1.0f, -1.0f, -1.0f);
		model.setColor(color);
	}

	@Test
	public void testGetColor() {
		Color color = new Color(1.0f, 1.0f, 1.0f);
		model.setColor(color);

		// Test if getting the prop's color returns the correct RGB values
		assertTrue(model.getColor().getBlue() == color.getBlue());
		assertTrue(model.getColor().getRed() == color.getRed());
		assertTrue(model.getColor().getGreen() == color.getGreen());
		assertTrue(model.getColor().getAlpha() == color.getAlpha());

		color = new Color(2.0f, 2.0f, 2.0f);
		model.setColor(color);

		// Same test with new values
		assertTrue(model.getColor().getBlue() == color.getBlue());
		assertTrue(model.getColor().getRed() == color.getRed());
		assertTrue(model.getColor().getGreen() == color.getGreen());
		assertTrue(model.getColor().getAlpha() == color.getAlpha());

		color = new Color(-1.0f, -1.0f, -1.0f);
		model.setColor(color);

		// Same test with new values
		assertTrue(model.getColor().getBlue() == color.getBlue());
		assertTrue(model.getColor().getRed() == color.getRed());
		assertTrue(model.getColor().getGreen() == color.getGreen());
		assertTrue(model.getColor().getAlpha() == color.getAlpha());
	}
}

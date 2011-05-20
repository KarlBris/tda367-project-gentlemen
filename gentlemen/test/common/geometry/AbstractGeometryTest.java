package common.geometry;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

public class AbstractGeometryTest {

	private static class TestAbstractGeometry extends AbstractGeometry {

		public TestAbstractGeometry() {
			super(Color.RED, 0.5f);
		}

		private Vector2f[] vertices = new Vector2f[] { new Vector2f(1.0f, 1.0f) };
		private Vector2f[] uvs = new Vector2f[] { new Vector2f(1.0f, 1.0f) };

		@Override
		public boolean isVisible() {
			return true;
		}

		@Override
		public Vector2f[] getVertices() {
			return vertices;
		}

		@Override
		public Vector2f[] getUvs() {
			return uvs;
		}
	};

	private AbstractGeometry geometry;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		geometry = new TestAbstractGeometry();
	}

	@After
	public void tearDown() throws Exception {
		geometry = null;
	}

	@Test
	public void testAbstractGeometry() {
		try {
			geometry = new TestAbstractGeometry();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetColor() {
		assertTrue(geometry.getColor() == Color.RED);
	}

	@Test
	public void testSetColor() {
		geometry.setColor(Color.YELLOW);

		assertTrue(geometry.getColor() == Color.YELLOW);
	}

	@Test
	public void testGetDepth() {
		assertTrue(Tools.floatsEqual(geometry.getDepth(), 0.5f));
	}

	@Test
	public void testSetDepth() {
		float newDepth = 0.2f;

		geometry.setDepth(newDepth);

		assertTrue(Tools.floatsEqual(geometry.getDepth(), newDepth));
	}

	@Test
	public void testGetPosition() {
		assertTrue(Tools.vectorsEqual(geometry.getPosition(), new Vector2f(
				0.0f, 0.0f)));
	}

	@Test
	public void testSetPosition() {
		Vector2f newPosition = new Vector2f(500.0f, 20.0f);

		geometry.setPosition(newPosition);

		assertTrue(Tools.vectorsEqual(geometry.getPosition(), newPosition));
	}

	@Test
	public void testGetAngle() {
		assertTrue(Tools.floatsEqual(geometry.getAngle(), 0.0f));
	}

	@Test
	public void testSetAngle() {
		float newAngle = Constants.PI;

		geometry.setAngle(newAngle);

		assertTrue(Tools.floatsEqual(geometry.getAngle(), newAngle));
	}

	@Test
	public void testGetScale() {
		assertTrue(Tools.vectorsEqual(geometry.getScale(), new Vector2f(1.0f,
				1.0f)));
	}

	@Test
	public void testSetScale() {
		Vector2f newScale = new Vector2f(5.0f, 10.0f);

		geometry.setScale(newScale);

		assertTrue(Tools.vectorsEqual(geometry.getScale(), newScale));
	}

	@Test
	public void testMoveTowardsVector2fFloatFloatFloat() {
		assertTrue(Tools.vectorsEqual(geometry.getPosition(), new Vector2f(
				0.0f, 0.0f)));
		assertTrue(Tools.floatsEqual(geometry.getAngle(), 0.0f));

		Vector2f targetPosition = new Vector2f(10.0f, 5.0f);
		float targetAngle = Constants.PI * 0.5f;

		// Measure the distance before the method call
		float distance = Tools.distanceBetween(targetPosition,
				geometry.getPosition());
		float angleDelta = Math.abs(targetAngle - geometry.getAngle());

		geometry.moveTowards(targetPosition, targetAngle, 0.5f, 0.5f);

		// Make sure the geometrical representation has moved towards the target
		assertTrue(Tools.floatsEqual(
				Tools.distanceBetween(targetPosition, geometry.getPosition()),
				distance * 0.5f));
		assertTrue(Tools.floatsEqual(
				Math.abs(targetAngle - geometry.getAngle()), angleDelta * 0.5f));
	}

	@Test
	public void testMoveTowardsVector2fFloat() {
		assertTrue(Tools.vectorsEqual(geometry.getPosition(), new Vector2f(
				0.0f, 0.0f)));
		assertTrue(Tools.floatsEqual(geometry.getAngle(), 0.0f));

		Vector2f targetPosition = new Vector2f(10.0f, 5.0f);
		float targetAngle = Constants.PI * 0.5f;

		// Measure the distance before the method call
		float distance = Tools.distanceBetween(targetPosition,
				geometry.getPosition());
		float angleDelta = Math.abs(targetAngle - geometry.getAngle());

		geometry.moveTowards(targetPosition, targetAngle);

		// Make sure the geometrical representation has moved towards the target
		assertTrue(Tools
				.distanceBetween(targetPosition, geometry.getPosition()) <= distance);
		assertTrue(Math.abs(targetAngle - geometry.getAngle()) <= angleDelta);
	}

}

package common.geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;

public class NullGeometryTest {

	private static NullGeometry geometry;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		geometry = new NullGeometry();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsVisible() {
		assertFalse(geometry.isVisible());
	}

	@Test
	public void testGetPosition() {
		assertTrue(Tools.vectorsEqual(geometry.getPosition(), new Vector2f(
				0.0f, 0.0f)));
	}

	@Test
	public void testGetAngle() {
		assertTrue(Tools.floatsEqual(geometry.getAngle(), 0.0f));
	}

	@Test
	public void testGetScale() {
		assertTrue(Tools.vectorsEqual(geometry.getScale(), new Vector2f(0.0f,
				0.0f)));
	}

	@Test
	public void testGetColor() {
		assertTrue(geometry.getColor() == Color.BLACK);
	}

	@Test
	public void testGetDepth() {
		assertTrue(Tools.floatsEqual(geometry.getDepth(), 0.0f));
	}

	@Test
	public void testGetVertices() {
		assertTrue(geometry.getVertices().length == 0);
	}

	@Test
	public void testGetUvs() {
		assertTrue(geometry.getUvs().length == 0);
	}

}

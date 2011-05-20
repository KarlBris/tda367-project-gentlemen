package common.body;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jbox2d.collision.shapes.CircleShape;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CircleBodyShapeTest {

	private static CircleBodyShape shape;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		shape = new CircleBodyShape(5.0f);
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
	public void testCircleBodyShape() {
		try {
			shape = new CircleBodyShape(5.0f);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetShape() {
		assertTrue(shape.getShape() instanceof CircleShape);
	}

}

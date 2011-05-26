package common.body;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jbox2d.collision.shapes.PolygonShape;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RectangleBodyShapeTest {

	private RectangleBodyShape shape;

	@Before
	public void setUp() throws Exception {
		shape = new RectangleBodyShape(10.0f, 5.0f);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRectangleBodyShape() {
		try {
			shape = new RectangleBodyShape(10.0f, 5.0f);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testGetShape() {
		assertTrue(shape.getShape() instanceof PolygonShape);
	}

}

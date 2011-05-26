package common.body;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jbox2d.collision.shapes.CircleShape;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CircleBodyShapeTest {

	private CircleBodyShape shape;

	@Before
	public void setUp() throws Exception {
		shape = new CircleBodyShape(5.0f);
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

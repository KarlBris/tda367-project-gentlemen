package common.body;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NullBodyTest {

	private static NullBody body;
	private static World testWorld;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		body = new NullBody();
		testWorld = new World(new Vec2(0.0f, 0.0f), true);
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
	public void testAddToWorld() {
		// The body should not get added to the world
		assertTrue(testWorld.getBodyCount() == 0);

		try {
			body.addToWorld(testWorld);
		} catch (Exception e) {
			fail();
		}

		assertTrue(testWorld.getBodyCount() == 0);
	}

	@Test
	public void testRemoveFromWorld() {
		// The body should not get added to the world
		assertTrue(testWorld.getBodyCount() == 0);

		try {
			body.removeFromWorld(testWorld);
		} catch (Exception e) {
			fail();
		}

		assertTrue(testWorld.getBodyCount() == 0);
	}
}
package common.body;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import model.common.IModel;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import common.geometry.IGeometry;
import common.geometry.NullGeometry;

import controller.MainControllerFactory;
import controller.common.IController;
import factories.entities.IEntityFactory;

public class BodyTest {

	private static class TestModel implements IModel {
		private IBody body = new Body(new CircleBodyShape(1.0f), 1.0f, 1.0f);
		private IGeometry geometry = new NullGeometry();

		@Override
		public IBody getBody() {
			return body;
		}

		@Override
		public IGeometry getGeometry() {
			return geometry;
		}
	};

	private static class TestController implements IController<TestModel> {

		private TestModel model = new TestModel();

		@Override
		public TestModel getModel() {
			return model;
		}

		@Override
		public void setPosition(final Vector2f position) {
			// Do nothing
		}

		@Override
		public void start() {
			// Do nothing
		}

		@Override
		public void end() {
			// Do nothing
		}

		@Override
		public void update() {
			// Do nothing
		}
	};

	private static class TestFactory implements
			IEntityFactory<TestModel, TestController> {

		private TestModel model = new TestModel();
		private TestController controller = new TestController();

		@Override
		public TestModel getModel() {
			return model;
		}

		@Override
		public TestController getController() {
			return controller;
		}

	};

	private static class TestCollisionCallback implements
			IBodyCollisionCallback {

		@Override
		public void collisionOccured(final Body otherBody,
				final Vector2f collisionPoint) {
			// Do nothing
		}

	};

	private static Body body;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		body = (Body) MainControllerFactory.get()
				.instantiate(new TestFactory()).getModel().getBody();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testBodyIBodyShapeFloat() {
		IBodyShape shape = new CircleBodyShape(1.0f);

		// Test body constructor
		try {
			new Body(shape, 1.0f);
		} catch (Exception e) {
			fail();
		}

		try {
			new Body(shape, 10.0f);
		} catch (Exception e) {
			fail();
		}

		try {
			new Body(shape, 0.0f);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testBodyIBodyShapeFloatFloat() {
		IBodyShape shape = new CircleBodyShape(1.0f);

		// Test body constructor
		try {
			new Body(shape, 1.0f, 1.0f);
		} catch (Exception e) {
			fail();
		}

		try {
			new Body(shape, 10.0f, 10.0f);
		} catch (Exception e) {
			fail();
		}

		try {
			new Body(shape, 0.0f, 0.0f);
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testIsStatic() {
		assertTrue(!body.isStatic());
	}

	@Test
	public void testGetMass() {
		assertTrue(Tools.floatsEqual(body.getMass(), 1.0f));
	}

	@Test
	public void testSetMass() {
		body.setMass(5.0f);

		assertTrue(Tools.floatsEqual(body.getMass(), 5.0f));
	}

	@Test
	public void testGetDamping() {
		assertTrue(Tools.floatsEqual(body.getDamping(), 1.0f));
	}

	@Test
	public void testSetDamping() {
		body.setDamping(5.0f);

		assertTrue(Tools.floatsEqual(body.getDamping(), 5.0f));
	}

	@Test
	public void testGetAngularDamping() {
		assertTrue(Tools.floatsEqual(body.getAngularDamping(),
				Constants.BODY_DEFAULT_ANGULAR_DAMPING));
	}

	@Test
	public void testSetAngularDamping() {
		body.setAngularDamping(5.0f);

		assertTrue(Tools.floatsEqual(body.getAngularDamping(), 5.0f));
	}

	@Test
	public void testGetCollisionCallback() {
		assertTrue(body.getCollisionCallback() == null);
	}

	@Test
	public void testSetCollisionCallback() {
		body.setCollisionCallback(new TestCollisionCallback());

		assertTrue(body.getCollisionCallback() instanceof TestCollisionCallback);
	}

	@Test
	public void testGetPosition() {
		assertTrue(Tools.vectorsEqual(body.getPosition(), new Vector2f(0.0f,
				0.0f)));
	}

	@Test
	public void testSetPosition() {
		Vector2f newPosition = new Vector2f(10.0f, 5.0f);

		body.setPosition(newPosition);

		assertTrue(Tools.vectorsEqual(body.getPosition(), newPosition));
	}

	@Test
	public void testGetAngle() {
		assertTrue(Tools.floatsEqual(body.getAngle(), 0.0f));
	}

	@Test
	public void testSetAngle() {
		float newAngle = Constants.PI;

		body.setAngle(newAngle);

		assertTrue(Tools.floatsEqual(body.getAngle(), newAngle));
	}

	@Test
	public void testAddToWorld() {
		body = new Body(new CircleBodyShape(1.0f), 1.0f, 1.0f);

		try {
			body.addToWorld(new World(new Vec2(0.0f, 0.0f), true));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testRemoveFromWorld() {
		try {
			body.removeFromWorld(new World(new Vec2(0.0f, 0.0f), true));

			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testGetAcceleration() {
		assertTrue(Tools.vectorsEqual(body.getAcceleration(), new Vector2f(
				0.0f, 0.0f)));
	}

	@Test
	public void testGetTorque() {
		assertTrue(Tools.floatsEqual(body.getTorque(), 0.0f));
	}

	@Test
	public void testGetAngularAcceleration() {
		assertTrue(Tools.floatsEqual(body.getAngularAcceleration(), 0.0f));
	}

	@Test
	public void testGetVelocity() {
		assertTrue(Tools.vectorsEqual(body.getVelocity(), new Vector2f(0.0f,
				0.0f)));
	}

	@Test
	public void testGetAngularVelocity() {
		assertTrue(Tools.floatsEqual(body.getAngularVelocity(), 0.0f));
	}

	@Test
	public void testGetVelocityAtPoint() {
		Vector2f point = new Vector2f(1.0f, 0.0f);

		assertTrue(Tools.vectorsEqual(body.getVelocityAtPoint(point),
				new Vector2f(0.0f, 0.0f)));
	}

	@Test
	public void testApplyForce() {
		Vector2f force = new Vector2f(1000.0f, 500.0f);
		Vector2f acceleration = new Vector2f(force);
		acceleration.scale(1.0f / body.getMass());

		assertTrue(Tools.vectorsEqual(body.getAcceleration(), new Vector2f(
				0.0f, 0.0f)));

		body.applyForce(force);

		assertTrue(Tools.vectorsEqual(body.getAcceleration(), acceleration));
	}

	@Test
	public void testApplyTorque() {
		float torque = 1000.0f;

		assertTrue(Tools.floatsEqual(body.getTorque(), 0.0f));

		body.applyTorque(torque);

		assertTrue(Tools.floatsEqual(body.getTorque(), torque));
	}

	@Test
	public void testApplyVelocityChange() {
		Vector2f velocityChange = new Vector2f(1000.0f, 0.0f);

		assertTrue(Tools.vectorsEqual(body.getVelocity(), new Vector2f(0.0f,
				0.0f)));

		body.applyVelocityChange(velocityChange);

		assertTrue(Tools.vectorsEqual(body.getVelocity(), velocityChange));
	}

	@Test
	public void testApplyAngularVelocityChange() {
		float velocityChange = 5.0f;

		assertTrue(Tools.floatsEqual(body.getAngularVelocity(), 0.0f));

		body.applyAngularVelocityChange(velocityChange);

		assertTrue(Tools.floatsEqual(body.getAngularVelocity(), velocityChange));
	}

	@Test
	public void testClearVelocity() {
		Vector2f velocityChange = new Vector2f(5.0f, 5.0f);

		body.applyVelocityChange(velocityChange);

		assertTrue(Tools.vectorsEqual(body.getVelocity(), velocityChange));

		body.clearVelocity();

		assertTrue(Tools.vectorsEqual(body.getVelocity(), new Vector2f(0.0f,
				0.0f)));
	}

	@Test
	public void testClearAngularVelocity() {
		float velocityChange = 5.0f;

		body.applyAngularVelocityChange(velocityChange);

		assertTrue(Tools.floatsEqual(body.getAngularVelocity(), velocityChange));

		body.clearAngularVelocity();

		assertTrue(Tools.floatsEqual(body.getAngularVelocity(), 0.0f));
	}

}

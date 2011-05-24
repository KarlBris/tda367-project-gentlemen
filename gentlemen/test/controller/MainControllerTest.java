package controller;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import model.common.IModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

import controller.common.IController;
import factories.entities.IEntityFactory;

public class MainControllerTest {

	private static class TestModel implements IModel {
		private IGeometry geometry = new NullGeometry();
		private IBody body = new NullBody();

		private Vector2f position = new Vector2f(0.0f, 0.0f);

		@Override
		public IGeometry getGeometry() {
			return geometry;
		}

		@Override
		public IBody getBody() {
			return body;
		}

		public void setPosition(final Vector2f position) {
			this.position = position;
		}

		public Vector2f getPosition() {
			return position;
		}
	}

	private static class TestController implements IController<TestModel> {

		private TestModel model;

		public TestController(final TestModel model) {
			this.model = model;
		}

		@Override
		public TestModel getModel() {
			return model;
		}

		@Override
		public void setPosition(final Vector2f position) {
			model.setPosition(position);
		}

		public Vector2f getPosition() {
			return model.getPosition();
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
	}

	private static class TestFactory implements
			IEntityFactory<TestModel, TestController> {
		private TestModel model = new TestModel();
		private TestController controller = new TestController(model);

		@Override
		public TestModel getModel() {
			return model;
		}

		@Override
		public TestController getController() {
			return controller;
		}
	}

	private MainController mainController;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// MainController components depend upon LWJGL; set it up temporarily
		Tools.identifyOS();

		Display.create();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Display.destroy();
	}

	@Before
	public void setUp() throws Exception {
		mainController = MainController.get();
	}

	@After
	public void tearDown() throws Exception {
		mainController.removeAll();
	}

	@Test
	public void testGet() {
		assertTrue(mainController != null);
	}

	@Test
	public void testGetControllers() {
		assertTrue(mainController.getControllers().size() == 0);
	}

	@Test
	public void testInstantiateIEntityFactoryOfMC() {
		assertTrue(mainController.getControllers().size() == 0);

		mainController.instantiate(new TestFactory());

		assertTrue(mainController.getControllers().size() == 1);

		mainController.instantiate(new TestFactory());

		assertTrue(mainController.getControllers().size() == 2);

		for (IController<? extends IModel> controller : mainController
				.getControllers()) {
			assertTrue(controller instanceof TestController);
		}
	}

	@Test
	public void testInstantiateIEntityFactoryOfMCVector2f() {
		assertTrue(mainController.getControllers().size() == 0);

		Vector2f position = new Vector2f(10.0f, 20.0f);

		mainController.instantiate(new TestFactory(), position);

		assertTrue(mainController.getControllers().size() == 1);

		mainController.instantiate(new TestFactory(), position);

		assertTrue(mainController.getControllers().size() == 2);

		for (IController<? extends IModel> controller : mainController
				.getControllers()) {
			assertTrue(controller instanceof TestController);

			assertTrue(Tools.vectorsEqual(
					((TestController) controller).getPosition(), position));
		}
	}

	@Test
	public void testFind() {
		assertTrue(mainController.getControllers().size() == 0);

		mainController.instantiate(new TestFactory());
		mainController.instantiate(new TestFactory());

		assertTrue(mainController.getControllers().size() == 2);

		Collection<TestController> controllers = mainController
				.find(TestController.class);

		assertTrue(controllers.size() == 2);
	}

	@Test
	public void testRemove() {
		assertTrue(mainController.getControllers().size() == 0);

		TestController controller = mainController
				.instantiate(new TestFactory());

		assertTrue(mainController.getControllers().size() == 1);

		mainController.remove(controller);

		assertTrue(mainController.getControllers().size() == 0);
	}

	@Test
	public void testRemoveAll() {
		mainController.instantiate(new TestFactory());
		mainController.instantiate(new TestFactory());

		assertTrue(mainController.getControllers().size() == 2);

		mainController.removeAll();

		assertTrue(mainController.getControllers().size() == 0);
	}

	@Test
	public void testGetKeyboardComponent() {
		assertTrue(mainController.getKeyboardComponent() != null);
	}

	@Test
	public void testGetMouseComponent() {
		assertTrue(mainController.getMouseComponent() != null);
	}

	@Test
	public void testInitialize() {
		try {
			mainController.initialize();
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testUpdate() {
		try {
			mainController.update();
		} catch (Exception e) {
			fail();
		}
	}
}

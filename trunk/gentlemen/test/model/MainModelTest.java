package model;

import static org.junit.Assert.assertTrue;
import model.common.IModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

public class MainModelTest {

	private static class TestModel implements IModel {
		private final IGeometry geometry = new NullGeometry();
		private final IBody body = new NullBody();

		@Override
		public IGeometry getGeometry() {
			return geometry;
		}

		@Override
		public IBody getBody() {
			return body;
		}
	}

	private static IModel testModel = new TestModel();

	private IMainModel mainModel;

	@Before
	public void setUp() throws Exception {
		mainModel = MainModel.get();
	}

	@After
	public void tearDown() throws Exception {
		mainModel.removeAll();
	}

	@Test
	public void testGet() {
		assertTrue(mainModel != null);
	}

	@Test
	public void testGetModels() {
		assertTrue(mainModel.getModels().size() == 0);
	}

	@Test
	public void testAdd() {
		assertTrue(mainModel.getModels().size() == 0);

		mainModel.add(testModel);

		assertTrue(mainModel.getModels().size() == 1);
		assertTrue(mainModel.getModels().contains(testModel));
	}

	@Test
	public void testRemove() {
		assertTrue(mainModel.getModels().size() == 0);

		mainModel.add(testModel);

		assertTrue(mainModel.getModels().size() == 1);
		assertTrue(mainModel.getModels().contains(testModel));

		mainModel.remove(testModel);

		assertTrue(mainModel.getModels().size() == 0);
	}
}
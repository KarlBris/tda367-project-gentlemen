package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Tools;

import controller.MainControllerFactory;
import factories.entities.ScoreboardFactory;

public final class ScoreboardModelTest {

	private ScoreboardModel model;

	@Before
	public void setUp() throws Exception {
		model = MainControllerFactory.get()
				.instantiate(new ScoreboardFactory()).getModel();
		
		Tools.identifyOS();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry objects
		assertTrue(model.getGeometry() != null);

	}

	@Test
	public void testGetBody() {
		// Test if the method returns a Body object. Since RuleModel does not
		// have a body, this should not be the case
		assertTrue(model.getBody() != null);
	}

	@Test
	public void testSetText() {
		String title = "Score!";
		model.setText(title);
		assertTrue(org.lwjgl.opengl.Display.getTitle().equals(title));
	}

}

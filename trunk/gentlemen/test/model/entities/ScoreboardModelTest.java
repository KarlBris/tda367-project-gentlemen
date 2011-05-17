package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.Manager;
import factories.entities.ScoreboardFactory;

public class ScoreboardModelTest {

	private ScoreboardModel model;

	@Before
	public void setUp() throws Exception {
		model = Manager.instantiate(new ScoreboardFactory()).getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
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

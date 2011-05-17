package model.entities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import core.Manager;
import factories.entities.TeamFactory;

public class TeamModelTest {
	private TeamModel model;

	// private RuleController ruleController;

	@Before
	public void setUp() throws Exception {
		model = Manager.instantiate(new TeamFactory()).getModel();
		// ruleController = (RuleController) Manager
		// .instantiate(new RuleFactory());
		// model.setRules(ruleController);
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testGetGeometry() {
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(model.getBody() != null);
	}

	@Test
	public void testAddScore() {
		model.addScore(2);
		try {
			model.addScore(-2);
			fail("This should have generated an NumberFormatException");
		} catch (NumberFormatException e) {
			// success
		}
	}

	@Test
	public void testSubtractScore() {
		model.subtractScore(2);
		try {
			model.subtractScore(-2);
			fail("This should have generated an NumberFormatException");
		} catch (NumberFormatException e) {
			// success
		}
	}

	@Test
	public void testGetScore() {
		// Test if the team has any points before receiving any points
		// Also tests getScore()
		assertTrue(model.getScore() == 0);
		model.addScore(1);

		// Test if the team has the correct number of points after adding points
		// using addScore() and addPoints()
		assertTrue(model.getScore() == 1);

		model.addScore(5);
		assertTrue(model.getScore() == 6);

		model.subtractScore(5);
		assertTrue(model.getScore() == 1);
	}

	@Test
	public void testAddPoint() {
		model.addPoints(4);
		assertTrue(model.getScore() == 4);

		try {
			model.addPoints(-2);
			fail("This should have generated an NumberFormatException");
		} catch (NumberFormatException e) {
			// success
		}
	}

	@Test
	public void testSetTeamName() {
		model.setTeamName("");
		model.setTeamName("Test Name");
	}

	@Test
	public void testGetTeamName() {
		String name = "hej";
		model.setTeamName(name);
		assertTrue(model.getTeamName().equals(name));

	}

	@Test
	public void testSetHomePosition() {
		model.setHomePosition(new Vector2f(20.0f, 10.0f));
	}

	@Test
	public void testGetHomePosition() {
		Vector2f v = new Vector2f(10.0f, 12.0f);
		model.setHomePosition(v);
		assertTrue(Tools.isVectorsEqual(v, model.getHomePosition()));
	}

}

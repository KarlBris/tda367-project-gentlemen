package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.entities.RuleController;
import core.Manager;
import factories.entities.RuleFactory;
import factories.entities.TeamFactory;

public class TeamModelTestOld {
	private TeamModel model;
	private RuleController ruleController;

	@Before
	public void setUp() throws Exception {
		model = (TeamModel) Manager.instantiate(new TeamFactory()).getModel();
		ruleController = (RuleController) Manager
				.instantiate(new RuleFactory());
		model.setRules(ruleController);
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testAddAndGetScore() {
		// Test if the team has any points before receiving any points
		// Also tests getScore()
		assertTrue(model.getScore() == 0);
		model.addScore(1);
		model.addPoints(1);

		// Test if the team has the correct number of points after adding points
		// using addScore() and addPoints()
		assertTrue(model.getScore() == 2);

	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testTeamName() {
		assertTrue(model.getTeamName() == null);
		model.setTeamName("NAME");
		assertTrue(model.getTeamName().equals("NAME"));
	}

	@Test
	public void testHomePosition() {
		Vector2f home = new Vector2f(15.0f, 18.0f);
		model.setHomePosition(home);

		// Test if moving the team's home position results in it having a
		// different home position
		assertTrue(Tools.isVectorsEqual(model.getHomePosition(), home));
	}

	@Test
	public void testRules() {
		ruleController = (RuleController) Manager
				.instantiate(new RuleFactory());
		model.setRules(ruleController);

		// Test if the new rules in the model have the same reference as
		// ruleController
		assertTrue(model.getRules() == ruleController);
	}

}

package models;

import static org.junit.Assert.assertTrue;

import model.entities.RuleModel;
import model.entities.TeamModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.Manager;
import factories.RuleFactory;
import factories.TeamFactory;

public class RuleModelTest {

	private RuleModel model;

	@Before
	public void setUp() throws Exception {
		model = (RuleModel) Manager.instantiate(new RuleFactory()).getModel();

	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testRuleModel() {
		// Tests if the score limit has a correct value. Also tests
		// getScoreLimit()
		assertTrue(model.getScoreLimit() == 20);
	}

	@Test
	public void testCheckVictory() {
		TeamModel teamModel = (TeamModel) Manager
				.instantiate(new TeamFactory()).getModel();

		// Test if the team has won before receiving any points
		assertTrue(!model.checkVictory(teamModel));

		teamModel.addPoints(model.getScoreLimit() + 1);

		// Test if the team has won after passing score limit
		assertTrue(model.checkVictory(teamModel));
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}
}

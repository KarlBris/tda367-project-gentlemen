package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Constants;
import core.Manager;
import factories.entities.RuleFactory;
import factories.entities.TeamFactory;

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
		assertTrue(model.getScoreLimit() == Constants.SCORE_LIMIT);
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
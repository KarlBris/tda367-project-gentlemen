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
		model = Manager.instantiate(new RuleFactory()).getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testCheckVictory() {
		TeamModel teamModel = Manager.instantiate(new TeamFactory()).getModel();

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

	@Test
	public void testGetScoreLimit() {
		// Check if the rule model's score limit has the correct value
		assertTrue(model.getScoreLimit() == Constants.SCORE_LIMIT);
	}

	@Test
	public void testGetBody() {
		// Test if the method returns a Body object. Since RuleModel does not
		// have a body, this should not be the case
		assertTrue(model.getBody() == null);
	}

}

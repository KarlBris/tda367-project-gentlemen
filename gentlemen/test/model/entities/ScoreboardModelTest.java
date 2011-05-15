package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.entities.TeamController;
import core.Manager;
import factories.entities.ScoreboardFactory;
import factories.entities.TeamFactory;

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
	public void testAddTeam() {
		TeamController teamController = Manager.instantiate(new TeamFactory());
		// Test if it is possible to add a new team to the team list
		model.addTeam(teamController);
	}

	@Test
	public void testGetTeamList() {
		TeamController teamController = Manager.instantiate(new TeamFactory());

		// Test if the team list is empty before any teams have been added
		assertTrue(model.getTeamList().isEmpty());

		model.addTeam(teamController);

		// Test if the team list is still empty after a team has been added
		assertTrue(!model.getTeamList().isEmpty());
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
		assertTrue(model.getBody() == null);
	}
}

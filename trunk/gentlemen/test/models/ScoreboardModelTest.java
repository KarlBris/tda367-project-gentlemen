package models;

import static org.junit.Assert.assertTrue;

import model.entities.ScoreboardModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.entities.TeamController;
import core.Manager;
import factories.ScoreboardFactory;
import factories.TeamFactory;

public class ScoreboardModelTest {
	private ScoreboardModel model;

	@Before
	public void setUp() throws Exception {
		model = (ScoreboardModel) Manager.instantiate(new ScoreboardFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testTeam() {
		// Test if the team list is empty before any teams have been added.
		assertTrue(model.getTeamList().isEmpty());

		TeamController teamController = (TeamController) Manager
				.instantiate(new TeamFactory());
		model.addTeam(teamController);

		// Test if the team list is still empty after a team has been added.
		assertTrue(!model.getTeamList().isEmpty());
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry objects
		assertTrue(model.getGeometry() != null);

	}
}

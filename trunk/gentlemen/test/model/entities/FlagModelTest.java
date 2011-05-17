package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controller.MainControllerFactory;
import controller.entities.TeamController;
import factories.entities.TeamFactory;

public class FlagModelTest {
	private FlagModel fm;
	private TeamController teamController;

	@Before
	public void setUp() throws Exception {
		teamController = MainControllerFactory.get().instantiate(
				new TeamFactory());
		teamController.setHomePosition(Constants.TEAM_ONE_HOME_POSITION);
		fm = new FlagModel(Color.BLUE);
		// fm.setTeam(teamController);
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testFlagModel() {
		// No error should occur
		new FlagModel(Color.BLACK);
	}

	@Test
	public void testPickUpFlag() {
		// No error should occur
		fm.pickUpFlag();

		// No error should occur
		fm.pickUpFlag();
	}

	@Test
	public void testReleaseFlag() {
		// No error should occur
		fm.releaseFlag();

		// No error should occur
		fm.releaseFlag();
	}

	@Test
	public void testIsPickUpAble() {
		// At start the flag should be pickup able
		assertTrue(fm.isPickUpAble());

		// If a flag is picked up the flag should no longer be pickup able
		fm.pickUpFlag();
		assertTrue(!fm.isPickUpAble());
		// Redundancy
		fm.pickUpFlag();
		assertTrue(!fm.isPickUpAble());

		// If a flag is released the flag should be pickup able again
		fm.releaseFlag();
		assertTrue(fm.isPickUpAble());
		// Redundancy
		fm.releaseFlag();
		assertTrue(fm.isPickUpAble());

	}

	@Test
	public void testGetGeometry() {
		assertTrue(fm.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(fm.getBody() != null);
	}

	@Test
	public void testSetPosition() {
		// No error should occur
		fm.setPosition(new Vector2f(20.0f, 10.0f));

		fm.setPosition(new Vector2f(10.0f, 10.0f));
	}

	@Test
	public void testGetPosition() {
		Vector2f initPosition = new Vector2f(1.0f, 1.0f);
		fm.setPosition(new Vector2f(7.0f, 4.0f));
		// Test that the object has moved
		assertTrue(!Tools.isVectorsEqual(initPosition, fm.getPosition()));

		initPosition = new Vector2f(4.0f, 7.0f);
		fm.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.isVectorsEqual(initPosition, fm.getPosition()));

	}

	// @Test
	// public void testSetTeam() {
	// TeamController tc = MainControllerFactory.get().instantiate(new
	// TeamFactory());
	// tc.setHomePosition(new Vector2f(10.0f, 9.0f));
	// fm.setTeam(tc);
	//
	// }

	// @Test
	// public void testGetTeam() {
	//
	// assertTrue(fm.getTeam() == teamController);
	//
	// TeamController tc = MainControllerFactory.get().instantiate(new
	// TeamFactory());
	// tc.setHomePosition(new Vector2f(10.0f, 9.0f));
	//
	// fm.setTeam(tc);
	// assertTrue(fm.getTeam() == tc);
	// }

	// @Test
	// public void testGetHomePosition() {
	// // Test if the flag's home position is the same position as that of the
	// // team.
	// assertTrue(Tools.isVectorsEqual(fm.getHomePosition(),
	// teamController.getHomePosition()));
	// }

	// @Test
	// public void testIsAtHome() {
	// fm.setPosition(new Vector2f(20.0f, 34.0f));
	// // Test that it's not returning true if the flag ain't home
	// assertTrue(!fm.isAtHome());
	//
	// fm.setPosition(fm.getHomePosition());
	// // Test that it's returning true if the flag is home
	// assertTrue(fm.isAtHome());
	// }

	// @Test
	// public void testReturnFlagHome() {
	// fm.setPosition(new Vector2f(23.0f, 76.0f));
	// fm.pickUpFlag();
	// fm.returnFlagHome();
	// // Test that the flag was returned home
	// assertTrue(fm.isAtHome());
	// // Test that the flag is now pick up able
	// assertTrue(fm.isPickUpAble());
	// }

	@Test
	public void testSetColor() {
		fm.setColor(Color.BLACK);
	}

	@Test
	public void testGetColor() {
		fm.setColor(Color.RED);
		assertTrue(fm.getColor() == Color.RED);

		fm.setColor(Color.BLACK);
		assertTrue(fm.getColor() == Color.BLACK);
	}

}

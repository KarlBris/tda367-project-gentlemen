package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controllers.TeamController;
import core.Manager;
import factories.TeamFactory;

public class FlagModelTest {
	private FlagModel fm;
	private TeamController teamController;
	private final float epsilon = 0.001f;

	@Before
	public void setUp() throws Exception {
		teamController = (TeamController) Manager
				.instantiate(new TeamFactory());
		teamController.setHomePosition(Constants.TEAM_ONE_HOME_POSITION);
		fm = new FlagModel(Color.BLUE);
		fm.setTeam(teamController);
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testFlagModel() {
		// Test if the color was successfully added to the geometry
		assertTrue(fm.getGeometry().getColor() == Color.BLUE);
	}

	@Test
	public void testIsPickUpAble() {
		// Test if the ball was pick up able from the start
		assertTrue(fm.isPickUpAble());
		fm.pickUpFlag();
		// Test if the ball is unpick up able when something told it that it was
		// picked up
		assertTrue(!fm.isPickUpAble());
		fm.releaseFlag();
		// Test if the ball is pick up able when something told it that is was
		// dropped
		assertTrue(fm.isPickUpAble());
	}

	@Test
	public void testGetGeometry() {
		// Test if the geometry ain't null
		assertTrue(fm.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		// Test if the body is null
		assertTrue(fm.getBody() == null);
	}

	@Test
	public void testSetPosition() {
		Vector2f initPosition = new Vector2f(1.0f, 1.0f);
		fm.setPosition(new Vector2f(7.0f, 4.0f));
		// Test that the object has moved
		assertTrue(!(Tools.distanceBetween(initPosition, fm.getPosition()) <= epsilon));

		initPosition = new Vector2f(4.0f, 7.0f);
		fm.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.distanceBetween(initPosition, fm.getPosition()) <= epsilon);
	}

	@Test
	public void testGetTeamAndSetTeam() {

		fm.setTeam(teamController);

		// Test correct team was set
		assertTrue(fm.getTeam() == teamController);
	}

	@Test
	public void testGetHomePosition() {

		// Test if the flag's home position is the same position as that of the
		// team.
		assertTrue(Tools.distanceBetween(fm.getHomePosition(),
				teamController.getHomePosition()) <= epsilon);
	}

	@Test
	public void testIsAtHome() {
		fm.setPosition(new Vector2f(20.0f, 34.0f));
		// Test that it's not returning true if the flag ain't home
		assertTrue(!fm.isAtHome());

		fm.setPosition(fm.getHomePosition());
		// Test that it's returning true if the flag is home
		assertTrue(fm.isAtHome());
	}

	@Test
	public void testReturnFlagHome() {

		fm.setPosition(new Vector2f(23.0f, 76.0f));
		fm.pickUpFlag();
		fm.returnFlagHome();
		// Test that the flag was returned home
		assertTrue(fm.isAtHome());
		// Test that the flag is now pick up able
		assertTrue(fm.isPickUpAble());
	}

	@Test
	public void testSetColorAndGetColor() {
		fm.setColor(Color.RED);
		assertTrue(fm.getColor() == Color.RED);

		fm.setColor(Color.BLACK);
		assertTrue(fm.getColor() == Color.BLACK);
	}

}

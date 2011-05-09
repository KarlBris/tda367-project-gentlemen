package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;
import controllers.TeamController;
import core.Manager;
import factories.TeamFactory;

public class FlagModelTest {
	private FlagModel fm;
	private Vector2f startPos;
	private final float precision = 0.001f;

	@Before
	public void setUp() throws Exception {
		fm = new FlagModel(Color.BLUE);
		startPos = new Vector2f(50.0f, 40.0f);

	}

	@After
	public void tearDown() throws Exception {
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
		fm.setPosition(startPos);
		Vector2f initPosition = startPos;
		fm.setPosition(new Vector2f(7.0f, 4.0f));
		// Test that the object has moved
		assertTrue(!(Tools.distanceBetween(initPosition, fm.getPosition()) <= precision));

		initPosition = new Vector2f(4.0f, 7.0f);
		fm.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.distanceBetween(initPosition, fm.getPosition()) <= precision);
	}

	@Test
	public void testGetTeamAndSetTeam() {
		TeamController teamController = (TeamController) Manager
				.instantiate(new TeamFactory());

		fm.setTeam(teamController);

		// Test correct team was set
		assertTrue(fm.getTeam() == teamController);
	}

	@Test
	public void testGetHomePosition() {
		// Home position should be the first position set on a flag
		fm.setPosition(startPos);
		fm.setPosition(new Vector2f(20.0f, 30.0f));
		// Test if that is true
		assertTrue(Tools.distanceBetween(fm.getHomePosition(), startPos) <= precision);
	}

	@Test
	public void testIsAtHome() {
		fm.setPosition(startPos);

		fm.setPosition(new Vector2f(20.0f, 34.0f));
		// Test that it's not returning true if the flag ain't home
		assertTrue(!fm.isAtHome());

		fm.setPosition(fm.getHomePosition());
		// Test that it's returning true if the flag is home
		assertTrue(fm.isAtHome());
	}

	@Test
	public void testReturnFlagHome() {

		fm.setPosition(startPos);
		fm.setPosition(new Vector2f(23.0f, 76.0f));
		fm.pickUpFlag();
		fm.returnFlagHome();
		// Test that the flag was returned home
		assertTrue(fm.isAtHome());
		// Test that the flag is now pick up able
		assertTrue(fm.isPickUpAble());
	}

}

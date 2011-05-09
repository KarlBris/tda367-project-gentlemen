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
		assertTrue(fm.getGeometry().getColor() == Color.BLUE);
	}

	@Test
	public void testIsPickUpAble() {
		assertTrue(fm.isPickUpAble());
		fm.pickUpFlag();
		assertTrue(!fm.isPickUpAble());
		fm.releaseFlag();
		assertTrue(fm.isPickUpAble());
		fm.pickUpFlag();
		assertTrue(!fm.isPickUpAble());
	}

	@Test
	public void testGetGeometry() {
		assertTrue(fm.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(fm.getBody() == null);
	}

	@Test
	public void testSetPosition() {
		fm.setPosition(startPos);
		Vector2f initPosition = startPos;
		fm.setPosition(new Vector2f(7.0f, 4.0f));
		assertTrue(!(Tools.distanceBetween(initPosition, fm.getPosition()) <= precision));

		initPosition = new Vector2f(4.0f, 7.0f);
		fm.setPosition(initPosition);
		assertTrue(Tools.distanceBetween(initPosition, fm.getPosition()) <= precision);
	}

	@Test
	public void testGetTeamAndSetTeam() {
		TeamController teamController = (TeamController) Manager
				.instantiate(new TeamFactory());

		fm.setTeam(teamController);

		assertTrue(fm.getTeam() == teamController);
	}

	@Test
	public void testGetHomePosition() {
		// Home position should be the first position set on a flag
		fm.setPosition(startPos);
		fm.setPosition(new Vector2f(20.0f, 30.0f));
		assertTrue(Tools.distanceBetween(fm.getHomePosition(), startPos) <= precision);
	}

	@Test
	public void testIsAtHome() {
		fm.setPosition(startPos);

		fm.setPosition(new Vector2f(20.0f, 34.0f));
		assertTrue(!fm.isAtHome());

		fm.setPosition(fm.getHomePosition());
		assertTrue(fm.isAtHome());
	}

	@Test
	public void testReturnFlagHome() {
		fm.setPosition(new Vector2f(23.0f, 76.0f));
		fm.returnFlagHome();
		assertTrue(fm.isAtHome());
	}

}

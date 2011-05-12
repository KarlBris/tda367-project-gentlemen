package controllers;

import static org.junit.Assert.assertTrue;
import model.entities.FlagModel;
import model.entities.TeamModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import controller.entities.FlagController;
import controller.entities.TeamController;

import utilities.Color;
import utilities.Tools;
import core.Manager;
import factories.entities.FlagFactory;
import factories.entities.TeamFactory;

public class FlagControllerTest {
	private FlagController fc;
	private TeamController tc;
	private final float precision = 0.001f;

	@Before
	public void setUp() throws Exception {
		fc = (FlagController) Manager.instantiate(new FlagFactory());
		tc = (TeamController) Manager.instantiate(new TeamFactory());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFlagControllerAndGetModel() {
		FlagModel fm = new FlagModel(Color.RED);
		fc = new FlagController(fm);

		assertTrue(fm == fc.getModel());
	}

	@Test
	public void testSetPositionAndGetPosition() {
		Vector2f vector = new Vector2f(20.0f, 20.0f);
		fc.setPosition(vector);
		assertTrue(Tools.distanceBetween(fc.getPosition(), vector) <= precision);

		vector = new Vector2f(2.0f, 1.0f);
		fc.setPosition(vector);
		assertTrue(Tools.distanceBetween(fc.getPosition(), vector) <= precision);
	}

	@Test
	public void testIsPickUpAbleAndPickUpFlagAndReleaseFlag() {
		// At start the flag is suppose to be pick up able
		assertTrue(fc.isPickUpAble());

		// pick up flag and the flag is suppose to not be able to be picked up
		fc.pickUpFlag();
		assertTrue(!fc.isPickUpAble());

		// No change
		fc.pickUpFlag();
		assertTrue(!fc.isPickUpAble());

		// If it's released it's suppose to be pick up able again
		fc.releaseFlag();
		assertTrue(fc.isPickUpAble());

		// No change
		fc.releaseFlag();
		assertTrue(fc.isPickUpAble());

	}

	@Test
	public void testSetTeamAndGetTeam() {
		tc.setHomePosition(new Vector2f(2.0f, 1.0f));
		fc.setTeam(tc);
		assertTrue(tc == fc.getTeam());

		TeamController tc2 = new TeamController(new TeamModel());
		tc2.setHomePosition(new Vector2f(2.0f, 1.0f));
		fc.setTeam(tc2);
		assertTrue(tc2 == fc.getTeam());
	}

	@Test
	public void testIsAtHome() {
		tc.setHomePosition(new Vector2f(0.0f, 0.0f));
		fc.setTeam(tc);

		// It should be at its homeposition
		assertTrue(fc.isAtHome());

		fc.setPosition(new Vector2f(40.0f, 0.0f));

		assertTrue(!fc.isAtHome());

	}

	@Test
	public void testReturnFlagHome() {
		tc.setHomePosition(new Vector2f(0.0f, 0.0f));
		fc.setTeam(tc);

		fc.setPosition(new Vector2f(40.0f, 0.0f));

		fc.returnFlagHome();
		assertTrue(fc.isAtHome());

	}

	@Test
	public void testSetColorAndGetColor() {
		fc.setColor(Color.RED);
		assertTrue(fc.getColor() == Color.RED);

		fc.setColor(Color.BLACK);
		assertTrue(fc.getColor() == Color.BLACK);
	}

	@Test
	public void testGetHomePosition() {
		tc.setHomePosition(new Vector2f(101.0f, 0.0f));
		fc.setTeam(tc);
		assertTrue(Tools.distanceBetween(new Vector2f(101.0f, 0.0f),
				fc.getHomePosition()) <= precision);
	}
}

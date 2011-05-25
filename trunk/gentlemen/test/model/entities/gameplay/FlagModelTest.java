package model.entities.gameplay;

import static org.junit.Assert.assertTrue;

import model.entities.gameplay.FlagModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;
import controller.MainControllerFactory;
import controller.entities.gamemode.TeamController;
import controller.entities.gamemode.TeamFactory;

public final class FlagModelTest {
	private FlagModel fm;
	private TeamController teamController;

	@Before
	public void setUp() throws Exception {
		teamController = MainControllerFactory.get().instantiate(
				new TeamFactory());
		teamController.setHomePosition(Constants.TEAM_ONE_HOME_POSITION);
		fm = new FlagModel(Color.BLUE);
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
		assertTrue(!Tools.vectorsEqual(initPosition, fm.getPosition()));

		initPosition = new Vector2f(4.0f, 7.0f);
		fm.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.vectorsEqual(initPosition, fm.getPosition()));

	}

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

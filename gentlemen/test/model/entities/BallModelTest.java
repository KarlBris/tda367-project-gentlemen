package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import common.body.Body;

import controller.MainControllerFactory;
import factories.entities.BallFactory;

public class BallModelTest {
	private BallModel bm;

	@Before
	public void setUp() throws Exception {
		bm = MainControllerFactory.get().instantiate(new BallFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testGetGeometry() {
		assertTrue(bm.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(bm.getBody() instanceof Body);
	}

	@Test
	public void testIsLethal() {

		// Test if standing still is above lethal speed
		final Vector2f relativeVelocity = new Vector2f(0.0f, 0.0f);
		assertTrue(!bm.isLethal(relativeVelocity));

		// Test if moving faster than lethal speed is above lethal speed
		((Body) bm.getBody()).applyVelocityChange(new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1));
		assertTrue(bm.isLethal(relativeVelocity));

		((Body) bm.getBody()).clearVelocity();
	}

	@Test
	public void testPickUp() {
		// No error should occur
		bm.pickUp();

		// No error should occur
		bm.pickUp();
	}

	@Test
	public void testReleaseBall() {
		// No error should occur
		bm.releaseBall();

		// No error should occur
		bm.releaseBall();
	}

	@Test
	public void testIsPickUpAble() {
		final Vector2f playerVelocity = new Vector2f(0.0f, 0.0f);

		// Test if the ball is possible to pick up when both player and ball are
		// still
		assertTrue(bm.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when already picked up
		bm.pickUp();
		assertTrue(!bm.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when moving faster than at lethal
		// speed
		bm.releaseBall();
		((Body) bm.getBody()).applyVelocityChange(new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1, Constants.BALL_LETHAL_SPEED));
		assertTrue(!bm.isPickUpAble(playerVelocity));
		bm.releaseBall();

		// Test if the ball can get picked up again once it has been released
		// and speed has been reduced to zero
		((Body) bm.getBody()).clearVelocity();
		assertTrue(bm.isPickUpAble(playerVelocity));
	}

	@Test
	public void testSetPosition() {
		// No error should occur

		bm.setPosition(new Vector2f(20.0f, 10.0f));

		bm.setPosition(new Vector2f(10.0f, 10.0f));
	}

	@Test
	public void testGetPosition() {
		Vector2f initPosition = new Vector2f(1.0f, 1.0f);
		bm.setPosition(new Vector2f(7.0f, 4.0f));
		// Test that the object has moved
		assertTrue(!Tools.isVectorsEqual(initPosition, bm.getPosition()));

		initPosition = new Vector2f(4.0f, 7.0f);
		bm.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.isVectorsEqual(initPosition, bm.getPosition()));
	}

	@Test
	public void testThrowBall() {

		final Vector2f zeroVelocity = new Vector2f(0.0f, 0.0f);
		final Vector2f throwVelocity = new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1);

		bm.pickUp();

		// Test if the ball being thrown at a speed higher than lethal speed
		// results in it having a higher speed than lethal speed
		bm.throwBall(throwVelocity);
		assertTrue(Tools.distanceBetween(((Body) bm.getBody()).getVelocity(),
				zeroVelocity) > Constants.BALL_LETHAL_SPEED);
		// The speed is too high and should not be able to be picked up
		assertTrue(!bm.isPickUpAble(new Vector2f(0.0f, 0.0f)));
	}

	@Test
	public void testUpdate() {
		// Update is to fat for testing at the moment
	}

}

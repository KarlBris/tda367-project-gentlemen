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
	private BallModel model;

	@Before
	public void setUp() throws Exception {
		model = MainControllerFactory.get().instantiate(new BallFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testGetGeometry() {
		assertTrue(model.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(model.getBody() instanceof Body);
	}

	@Test
	public void testIsLethal() {

		// Test if standing still is above lethal speed
		final Vector2f relativeVelocity = new Vector2f(0.0f, 0.0f);
		assertTrue(!model.isLethal(relativeVelocity));

		// Test if moving faster than lethal speed is above lethal speed
		((Body) model.getBody()).applyVelocityChange(new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1));
		assertTrue(model.isLethal(relativeVelocity));

		// ((Body) bm.getBody()).clearVelocity();
	}

	@Test
	public void testPickUp() {
		// No error should occur
		model.pickUp();

		// No error should occur
		model.pickUp();
	}

	@Test
	public void testReleaseBall() {
		// No error should occur
		model.releaseBall();

		// No error should occur
		model.releaseBall();
	}

	@Test
	public void testIsPickUpAble() {
		final Vector2f playerVelocity = new Vector2f(0.0f, 0.0f);

		// Test if the ball is possible to pick up when both player and ball are
		// still
		assertTrue(model.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when already picked up
		model.pickUp();
		assertTrue(!model.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when moving faster than at lethal
		// speed
		model.releaseBall();
		((Body) model.getBody()).applyVelocityChange(new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1, Constants.BALL_LETHAL_SPEED));
		assertTrue(!model.isPickUpAble(playerVelocity));
		model.releaseBall();

		// Test if the ball can get picked up again once it has been released
		// and speed has been reduced to zero
		((Body) model.getBody()).clearVelocity();
		assertTrue(model.isPickUpAble(playerVelocity));
	}

	@Test
	public void testSetPosition() {
		// No error should occur

		model.setPosition(new Vector2f(20.0f, 10.0f));

		model.setPosition(new Vector2f(10.0f, 10.0f));
	}

	@Test
	public void testGetPosition() {
		Vector2f initPosition = new Vector2f(1.0f, 1.0f);
		model.setPosition(new Vector2f(7.0f, 4.0f));
		// Test that the object has moved
		assertTrue(!Tools.vectorsEqual(initPosition, model.getPosition()));

		initPosition = new Vector2f(4.0f, 7.0f);
		model.setPosition(initPosition);
		// Test that correct position was set
		assertTrue(Tools.vectorsEqual(initPosition, model.getPosition()));
	}

	@Test
	public void testThrowBall() {

		final Vector2f zeroVelocity = new Vector2f(0.0f, 0.0f);
		final Vector2f throwVelocity = new Vector2f(
				Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1);

		model.pickUp();

		// Test if the ball being thrown at a speed higher than lethal speed
		// results in it having a higher speed than lethal speed
		model.throwBall(throwVelocity);
		assertTrue(Tools.distanceBetween(
				((Body) model.getBody()).getVelocity(), zeroVelocity) > Constants.BALL_LETHAL_SPEED);
		// The speed is too high and should not be able to be picked up
		assertTrue(!model.isPickUpAble(new Vector2f(0.0f, 0.0f)));
	}

	@Test
	public void testUpdate() {
		// Test update not generating errors
		model.update();
	}

	@Test
	public void testGetVelocity() {

		assertTrue(model.getBody() instanceof Body);
		// The velocity should be zero at start
		assertTrue(Tools.vectorsEqual(model.getVelocity(), new Vector2f(0.0f,
				0.0f)));

		((Body) model.getBody()).applyVelocityChange(new Vector2f(20.0f, 0.0f));
		// The velocity should have changed
		assertTrue(Tools.vectorsEqual(model.getVelocity(), new Vector2f(20.0f,
				0.0f)));
		((Body) model.getBody()).applyVelocityChange(new Vector2f(0.0f, 20.0f));
		// The velocity should have changed
		assertTrue(Tools.vectorsEqual(model.getVelocity(), new Vector2f(20.0f,
				20.0f)));

	}
}

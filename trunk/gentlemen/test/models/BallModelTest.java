package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controllers.BallController;
import core.Manager;
import factories.BallFactory;

public class BallModelTest {

	private final float precision = 0.01f;
	BallController controller;
	BallModel model;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = (BallController) Manager.instantiate(new BallFactory());
		model = (BallModel) controller.getModel();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link models.BallModel#isLethal(Vector2f)}.
	 */
	@Test
	public void testIsLethal() {

		// Test if standing still is above lethal speed
		Vector2f relativeVelocity = new Vector2f(0.0f, 0.0f);
		assertTrue(!model.isLethal(relativeVelocity));

		// Test if moving faster than lethal speed is above lethal speed
		model.getBody().applyVelocityChange(
				new Vector2f(Constants.BALL_LETHAL_SPEED + 1,
						Constants.BALL_LETHAL_SPEED + 1));
		assertTrue(model.isLethal(relativeVelocity));

		model.getBody().clearVelocity();
	}

	/**
	 * Test method for {@link models.BallModel#isPickUpAble(Vector2f)}.
	 */
	@Test
	public void testIsPickUpAble() {
		Vector2f playerVelocity = new Vector2f(0.0f, 0.0f);

		// Test if the ball is possible to pick up when both player and ball are
		// still
		assertTrue(model.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when already picked up
		model.pickUp();
		assertTrue(!model.isPickUpAble(playerVelocity));

		// Test if the ball can get picked up when moving faster than at lethal
		// speed
		model.releaseBall();
		model.getBody().applyVelocityChange(
				new Vector2f(Constants.BALL_LETHAL_SPEED + 1,
						Constants.BALL_LETHAL_SPEED));
		assertTrue(!model.isPickUpAble(playerVelocity));
		model.releaseBall();

		// Test if the ball can get picked up again once it has been released
		// and speed has been reduced to zero
		model.getBody().clearVelocity();
		assertTrue(model.isPickUpAble(playerVelocity));
	}

	/**
	 * Test method for {@link models.BallModel#setPosition(Vector2f)} and
	 * {@link models.BallModel#getPosition()}.
	 */
	@Test
	public void testPosition() {
		Vector2f refPosition = new Vector2f(0.0f, 0.0f);

		// Test if setting the ball's position from a vector and subsequently
		// reading it returns the same value as the vector itself
		model.setPosition(refPosition);
		assertTrue(Tools.distanceBetween(model.getPosition(), refPosition) <= precision);

		// Test the same case after moving the ball
		refPosition.set(1.0f, 1.0f);
		model.setPosition(refPosition);
		assertTrue(Tools.distanceBetween(model.getPosition(), refPosition) <= precision);

	}

	/**
	 * Test method for {@link models.BallModel#ThrowBall(Vector2f)}.
	 */
	@Test
	public void testThrowBall() {

		Vector2f zeroVelocity = new Vector2f(0.0f, 0.0f);
		Vector2f throwVelocity = new Vector2f(Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1);

		model.pickUp();

		// Test if the ball being thrown at a speed higher than lethal speed
		// results in it having a higher speed than lethal speed
		model.throwBall(throwVelocity);
		assertTrue(Tools.distanceBetween(model.getBody().getVelocity(),
				zeroVelocity) > Constants.BALL_LETHAL_SPEED);

	}

	/**
	 * Test method for {@link models.BallModel#getGeometry()}.
	 */
	@Test
	public void testGetGeometry() {

		// Test if the method returns a Geometry object
		assertTrue(model.getGeometry() != null);
	}

}

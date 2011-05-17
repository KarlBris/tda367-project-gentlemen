package controller.entities;

import static org.junit.Assert.assertTrue;
import model.entities.BallModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import common.body.Body;

import controller.MainControllerFactory;
import factories.entities.BallFactory;

public class BallControllerTest {
	private BallController bc;

	@Before
	public void setUp() throws Exception {
		bc = MainControllerFactory.get().instantiate(new BallFactory());
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testBallControllerAndGetModel() {
		BallModel bm = new BallModel();
		BallController testbc = new BallController(bm);
		assertTrue(testbc.getModel() == bm);
	}

	@Test
	public void testPickUpBallAndReleaseBallAndIsPickUpAble() {
		Vector2f v = new Vector2f(0.0f, 0.0f);

		// At start the ball is suppose to be pick up able
		assertTrue(bc.isPickUpAble(v));

		// pick up ball and the ball is suppose to not be able to be picked up
		bc.pickUpBall();
		assertTrue(!bc.isPickUpAble(v));

		// No change
		bc.pickUpBall();
		assertTrue(!bc.isPickUpAble(v));

		// If it's released it's suppose to be pick up able again
		bc.releaseBall();
		assertTrue(bc.isPickUpAble(v));

		// No change
		bc.releaseBall();
		assertTrue(bc.isPickUpAble(v));
	}

	@Test
	public void testThrowBall() {
		Vector2f zeroVelocity = new Vector2f(0.0f, 0.0f);
		Vector2f throwVelocity = new Vector2f(Constants.BALL_LETHAL_SPEED + 1,
				Constants.BALL_LETHAL_SPEED + 1);

		bc.pickUpBall();

		// Test if the ball being thrown at a speed higher than lethal speed
		// results in it having a higher speed than lethal speed
		bc.throwBall(throwVelocity);
		assertTrue(Tools.distanceBetween(
				((Body) bc.getModel().getBody()).getVelocity(), zeroVelocity) > Constants.BALL_LETHAL_SPEED);
		// The speed is too high and should not be able to be picked up
		assertTrue(!bc.isPickUpAble(new Vector2f(0.0f, 0.0f)));
	}

	@Test
	public void testGetPositionAndSetPosition() {
		Vector2f refPosition = new Vector2f(0.0f, 0.0f);

		// Test if setting the ball's position from a vector and subsequently
		// reading it returns the same value as the vector itself
		bc.setPosition(refPosition);
		assertTrue(Tools.distanceBetween(bc.getPosition(), refPosition) <= Constants.EPSILON);

		// Test the same case after moving the ball
		refPosition.set(1.0f, 1.0f);
		bc.setPosition(refPosition);
		assertTrue(Tools.distanceBetween(bc.getPosition(), refPosition) <= Constants.EPSILON);
	}

}

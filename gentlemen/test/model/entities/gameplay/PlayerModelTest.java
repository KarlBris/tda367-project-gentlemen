package model.entities.gameplay;

import static org.junit.Assert.assertTrue;

import model.entities.gameplay.PlayerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;

import common.body.Body;

import controller.MainControllerFactory;
import controller.entities.gameplay.PlayerOneFactory;

public final class PlayerModelTest {
	private PlayerModel model;

	@Before
	public void setUp() throws Exception {
		model = MainControllerFactory.get().instantiate(new PlayerOneFactory())
				.getModel();
	}

	@After
	public void tearDown() throws Exception {
		MainControllerFactory.get().removeAll();
	}

	@Test
	public void testPlayerModel() {
		model = new PlayerModel();
	}

	@Test
	public void testIsKnockedOut() {
		assertTrue(!model.isKnockedOut());
	}

	@Test
	public void testIsCarryingBall() {
		assertTrue(!model.isCarryingBall());
	}

	@Test
	public void testIsCarryingFlag() {
		assertTrue(!model.isCarryingFlag());
	}

	@Test
	public void testPlayerKnockOut() {
		assertTrue(!model.isKnockedOut());
		model.playerKnockOut();

		assertTrue(model.isKnockedOut());
	}

	@Test
	public void testGetGeometry() {
		// Test if the method returns a Geometry objects
		assertTrue(model.getGeometry() != null);

	}

	@Test
	public void testGetBody() {
		// Test if the method returns a Body object
		assertTrue(model.getBody() instanceof Body);
	}

	@Test
	public void testPickUpFlag() {
		assertTrue(!model.isCarryingFlag());
		model.pickUpFlag();

		assertTrue(model.isCarryingFlag());
	}

	@Test
	public void testReleaseFlag() {
		model.pickUpFlag();
		assertTrue(model.isCarryingFlag());

		model.releaseFlag();
		assertTrue(!model.isCarryingFlag());
	}

	@Test
	public void testPickUpBall() {
		assertTrue(!model.isCarryingBall());
		model.pickUpBall();

		assertTrue(model.isCarryingBall());
	}

	@Test
	public void testReleaseBall() {
		model.pickUpBall();
		assertTrue(model.isCarryingBall());

		model.releaseBall();
		assertTrue(!model.isCarryingBall());
	}

	@Test
	public void testSetPosition() {
		// Test if setting the player's position to different values renders any
		// errors.
		Vector2f newPosition = new Vector2f(1.0f, 1.0f);
		model.setPosition(newPosition);

		newPosition = new Vector2f(2.0f, 2.0f);
		model.setPosition(newPosition);

		newPosition = new Vector2f(-1.0f, -1.0f);
		model.setPosition(newPosition);

	}

	@Test
	public void testGetPosition() {
		Vector2f position = new Vector2f(1.0f, 1.0f);
		model.setPosition(position);

		// Test if getting the player's position returns the correct value
		assertTrue(Tools.vectorsEqual(position, model.getPosition()));

		position = new Vector2f(2.0f, 2.0f);
		model.setPosition(position);

		// Same test with new values
		assertTrue(Tools.vectorsEqual(position, model.getPosition()));

	}

	@Test
	public void testMove() {
		// At start the player should not move
		assertTrue(((Body) model.getBody()).getAcceleration().x <= 0.001f
				&& ((Body) model.getBody()).getAcceleration().y <= 0.0001f);

		model.move(new Vector2f(10.0f, 10.0f));

		assertTrue(((Body) model.getBody()).getAcceleration().x >= 0.001f
				&& ((Body) model.getBody()).getAcceleration().y >= 0.0001f);
	}

	@Test
	public void testFaceTowards() {

		model.faceTowards(0);

		model.faceTowards(Constants.PI);

		model.faceTowards(Constants.PI / 2);

		model.faceTowards(Constants.PI / -2);

	}

	@Test
	public void testGetFacingAngle() {
		model.faceTowards(Constants.PI);
		assertTrue(model.getFacingAngle() == Constants.PI);
		model.faceTowards(Constants.PI / 2);
		assertTrue(model.getFacingAngle() == Constants.PI / 2);

	}

	@Test
	public void testSetAngle() {
		// Test if setting the player's angle to different values renders any
		// errors.
		model.setAngle(1.0f);
		model.setAngle(Constants.PI);
		model.setAngle(Constants.TWO_PI - 1);

		model.setAngle(-Constants.PI);
		model.setAngle(-Constants.TWO_PI - 1);

		model.setAngle(0.0f);
	}

	@Test
	public void testGetAngle() {
		float newAngle = 1.0f;
		model.setAngle(newAngle);

		// Test if getting the player's angle returns the correct value
		assertTrue(model.getAngle() == newAngle);

		newAngle = Constants.TWO_PI - 1;
		model.setAngle(newAngle);

		// Same test with new values
		assertTrue(Math.abs(model.getAngle() - newAngle) <= Constants.EPSILON);
	}

	@Test
	public void testUpdate() {
		// should not generate any error
		model.update();
		model.playerKnockOut();
		// Should still not generate any error
		model.update();
		for (float i = 0.0f; Float
				.compare(i, Constants.PLAYER_KNOCKED_OUT_TIME) < 0; i += Constants.DELTA_TIME) {
			model.update();
		}

	}

	@Test
	public void testGetKnockedOutTimer() {
		// Update is to fat to be tested for specific alterations. The test,
		// tests that all code is covered and not generate errors
		assertTrue(Tools.floatsEqual(model.getKnockedOutTimer(), 0.0f));
		model.update();
		assertTrue(Tools.floatsEqual(model.getKnockedOutTimer(), 0.0f));
		model.playerKnockOut();
		assertTrue(Tools.floatsEqual(model.getKnockedOutTimer(), 0.0f));
		model.update();
		assertTrue(!Tools.floatsEqual(model.getKnockedOutTimer(), 0.0f));
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

	@Test
	public void testGetVelocityAtPoint() {
		final Vector2f point = new Vector2f(1.0f, 0.0f);

		assertTrue(Tools.vectorsEqual(model.getVelocityAtPoint(point),
				new Vector2f(0.0f, 0.0f)));
	}
}

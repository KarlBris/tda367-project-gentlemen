package model.entities;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import utilities.Tools;

import common.body.Body;
import common.geometry.IGeometry;

import core.Manager;
import factories.entities.PlayerOneFactory;

public class PlayerModelTest {
	private PlayerModel model;

	@Before
	public void setUp() throws Exception {
		model = Manager.instantiate(new PlayerOneFactory()).getModel();
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
	}

	@Test
	public void testPlayerModel() {
		Color c = new Color(1.0f, 1.0f, 1.0f);
		model = new PlayerModel(c);

		c = new Color(2.0f, 2.0f, 2.0f);
		model = new PlayerModel(c);

		c = new Color(-1.0f, -1.0f, -1.0f);
		model = new PlayerModel(c);
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
		assertTrue(model.getGeometry() instanceof IGeometry);

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
		assertTrue(Tools.isVectorsEqual(position, model.getPosition()));

		position = new Vector2f(2.0f, 2.0f);
		model.setPosition(position);

		// Same test with new values
		assertTrue(Tools.isVectorsEqual(position, model.getPosition()));

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
		assertTrue(model.getAngle() == newAngle);
	}

	@Test
	public void testGetKnockedOutTimer() {

	}

}

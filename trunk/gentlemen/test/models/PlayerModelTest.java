package models;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controllers.BallController;
import controllers.FlagController;
import controllers.RuleController;
import controllers.TeamController;
import core.Manager;
import factories.BallFactory;
import factories.FlagFactory;
import factories.PlayerOneFactory;
import factories.RuleFactory;
import factories.TeamFactory;

public class PlayerModelTest {
	private PlayerModel pm;
	// Instantiate the teams
	private TeamController teamOne;
	private TeamController teamTwo;
	// Instantiate flags
	private FlagController teamOneFlag;
	private FlagController teamTwoFlag;
	// Instantiate ball
	private BallController bc;

	private final float precision = 0.001f;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		RuleController ruleController = (RuleController) Manager
				.instantiate(new RuleFactory());

		teamOne = (TeamController) Manager.instantiate(new TeamFactory());
		teamTwo = (TeamController) Manager.instantiate(new TeamFactory());
		teamOne.setHomePosition(new Vector2f(0.0f, 0.0f));
		teamTwo.setHomePosition(new Vector2f(0.0f, 10.0f));
		teamOne.setRules(ruleController);
		teamTwo.setRules(ruleController);

		teamOneFlag = (FlagController) Manager.instantiate(new FlagFactory(),
				new Vector2f(0.0f, 0.0f));
		teamTwoFlag = (FlagController) Manager.instantiate(new FlagFactory(),
				new Vector2f(0.0f, 10.0f));

		pm = (PlayerModel) Manager.instantiate(new PlayerOneFactory())
				.getModel();
		// pm = new PlayerModel(Constants.TEAM_ONE_COLOR);
		teamOne.setTeamName("Red Team");
		teamTwo.setTeamName("Blue Team");
		pm.setTeam(teamOne);

		teamOneFlag.setTeam(teamOne);
		teamTwoFlag.setTeam(teamTwo);

		teamOneFlag.setColor(Constants.TEAM_ONE_COLOR);
		teamTwoFlag.setColor(Constants.TEAM_TWO_COLOR);

		bc = (BallController) Manager.instantiate(new BallFactory());
	}

	@After
	public void tearDown() throws Exception {
		Manager.removeAll();
		bc = null;
		pm = null;
		teamOne = null;
		teamTwo = null;
		teamOneFlag = null;
		teamTwoFlag = null;
	}

	@Test
	public void testPlayerModel() {
		// Test if the player got it's teamColor
		assertTrue(pm.getGeometry().getColor() == Constants.TEAM_ONE_COLOR);
	}

	@Test
	public void testSetPositionAndGetPosition() {
		pm.setPosition(new Vector2f(1.0f, 3.0f));
		assertTrue(Tools.distanceBetween(pm.getPosition(), new Vector2f(1.0f,
				3.0f)) <= precision);

		pm.setPosition(new Vector2f(3.0f, 3.0f));
		assertTrue(Tools.distanceBetween(pm.getPosition(), new Vector2f(3.0f,
				3.0f)) <= precision);

	}

	@Test
	public void testPickUpBallAndGetBallControllerAndIsCarryingBallAndThrowBall() {

		// Test player have no ball
		assertTrue(pm.getBallController() == null);
		assertTrue(!pm.isCarryingBall());

		bc.setPosition(new Vector2f(Constants.BALL_PICK_UP_DISTANCE, 0.0f));
		pm.setPosition(new Vector2f(0.0f, 0.0f));

		// Test should be success and return true
		assertTrue(pm.pickUpBall());

		// Test player should now be carrying ball
		assertTrue(pm.getBallController() != null);
		assertTrue(pm.isCarryingBall());

		// The ball has no speed
		assertTrue(bc.getModel().getBody().getVelocity().x == 0
				&& bc.getModel().getBody().getVelocity().y == 0);

		// Throw ball should return true
		assertTrue(pm.throwBall());
		// Shouldent be carrying a ball anymore
		assertTrue(!pm.isCarryingBall());
		// The ball should have a velocity
		assertTrue(bc.getModel().getBody().getVelocity().x != 0
				|| bc.getModel().getBody().getVelocity().y != 0);

		// No longer has a ball and should now return false
		assertTrue(!pm.throwBall());

		// Test player should now no long carry ball
		assertTrue(pm.getBallController() == null);

		// Place the ball too far away from the players to be picked up
		bc.setPosition(new Vector2f((Constants.BALL_PICK_UP_DISTANCE + 0.01f),
				0.0f));
		// Test should generate false, because the ball is too far way to be
		// picked up
		assertTrue(!pm.pickUpBall());

		// The player should not have a ball in memory
		assertTrue(pm.getBallController() == null);
		assertTrue(!pm.isCarryingBall());
	}

	@Test
	public void testPlayerKnockOut() {
		// Test at start the player ain't knocked out
		assertTrue(!pm.isKnockedOut());

		pm.playerKnockOut();
		// Timer should be reseted
		assertTrue(pm.getKnockedOutTimer() == 0.0f);

		assertTrue(pm.isKnockedOut());
	}

	@Test
	public void testGetGeometry() {
		assertTrue(pm.getGeometry() != null);
	}

	@Test
	public void testGetBody() {
		assertTrue(pm.getBody() != null);
	}

	@Test
	public void testPickUpFlagAndGetFlagControllerAndIsCarryingFlagAndDropFlag() {
		// Test player have no flag
		assertTrue(pm.getFlagController() == null);
		assertTrue(!pm.isCarryingFlag());

		teamTwoFlag.setPosition(new Vector2f(Constants.FLAG_PICK_UP_DISTANCE,
				0.0f));
		pm.setPosition(new Vector2f(0.0f, 0.0f));

		// Test should be success and return true
		assertTrue(pm.pickUpFlag());

		// Test player should now be carrying flag
		assertTrue(pm.getFlagController() != null);
		assertTrue(pm.isCarryingFlag());
		assertTrue(pm.getFlagController().getTeam() != pm.getTeam());

		// The flag should be taken by the player
		assertTrue(!teamTwoFlag.isPickUpAble());
		// Drop should be success
		assertTrue(pm.dropFlag());
		// The flag should be droped by the player
		assertTrue(teamTwoFlag.isPickUpAble());
		// An other drop should fail
		assertTrue(!pm.dropFlag());

		// Test player should now no long carry flag
		assertTrue(pm.getBallController() == null);

		// Place the flag too far away from the players to be picked up
		teamTwoFlag.setPosition(new Vector2f(
				(Constants.FLAG_PICK_UP_DISTANCE + 0.01f), 0.0f));
		// Test should generate false, because the flag is too far way to be
		// picked up
		assertTrue(!pm.pickUpFlag());

		// The player should not have a flag in memory
		assertTrue(pm.getFlagController() == null);
		assertTrue(!pm.isCarryingFlag());
	}

	@Test
	public void testCaptureEnemyFlag() {
		// Test player should not be carrying flag
		assertTrue(!pm.isCarryingFlag());

		teamTwoFlag.setPosition(new Vector2f(20.0f, 10.0f));
		pm.setPosition(new Vector2f(20.0f, 10.0f));
		// The other teams flag should be picked up
		assertTrue(pm.pickUpFlag());

		pm.setPosition(new Vector2f(Constants.FLAG_PICK_UP_DISTANCE + 0.01f,
				0.0f));
		teamOneFlag.setPosition(new Vector2f(
				Constants.FLAG_PICK_UP_DISTANCE + 0.01f, 0.0f));
		// This should generate false, the flag ain't at its home position which
		// is (0.0f, 0.0f)
		assertTrue(!pm.captureEnemyFlag());
		assertTrue(pm.isCarryingFlag());

		teamOneFlag.returnFlagHome();
		assertTrue(teamOneFlag.isAtHome());

		// This should generate false, the team flag is out of reach
		assertTrue(!pm.captureEnemyFlag());
		assertTrue(pm.isCarryingFlag());

		pm.setPosition(new Vector2f(Constants.FLAG_PICK_UP_DISTANCE, 0.0f));

		// This should generate true, the flag is in reach
		assertTrue(pm.captureEnemyFlag());
		assertTrue(!pm.isCarryingFlag());
		// The enemy flag should be returned home
		assertTrue(teamTwoFlag.isAtHome());

	}

	@Test
	public void testReturnTeamFlag() {
		teamOneFlag.returnFlagHome();
		pm.setPosition(new Vector2f(teamOneFlag.getHomePosition()));
		// The flag is already home
		assertTrue(!pm.returnTeamFlag());

		teamOneFlag.setPosition(new Vector2f(Constants.FLAG_PICK_UP_DISTANCE,
				0.0f));
		// The flag is in range of the team player and the flag ain't at it's
		// home position
		assertTrue(pm.returnTeamFlag());
		assertTrue(teamOneFlag.isAtHome());

		teamOneFlag.setPosition(new Vector2f(
				Constants.FLAG_PICK_UP_DISTANCE + 0.1f, 0.0f));
		// The flag is out of range of the team player
		assertTrue(!pm.returnTeamFlag());
		assertTrue(!teamOneFlag.isAtHome());

		teamTwoFlag.setPosition(new Vector2f(0.0f, 0.0f));

		// Pick up other teams flag
		assertTrue(pm.pickUpFlag());

		teamOneFlag.setPosition(new Vector2f(Constants.FLAG_PICK_UP_DISTANCE,
				0.0f));
		// The player should be able to return the flag even if he is carrying a
		// flag of his own
		assertTrue(pm.returnTeamFlag());

	}

	@Test
	public void testMove() {
		// At start the player should not move
		assertTrue(pm.getBody().getAcceleration().x <= 0.001f
				&& pm.getBody().getAcceleration().y <= 0.0001f);

		pm.move(new Vector2f(10.0f, 10.0f));

		assertTrue(pm.getBody().getAcceleration().x >= 0.001f
				&& pm.getBody().getAcceleration().y >= 0.0001f);
	}

	@Test
	public void testFaceTowards() {
		pm.faceTowards(Constants.PI);
		assertTrue(pm.getFacingAngle() == Constants.PI);
		pm.faceTowards(Constants.PI / 2);
		assertTrue(pm.getFacingAngle() == Constants.PI / 2);
	}

	@Test
	public void testUpdate() {
		// Update is one of the method that is allowed to be different
	}

	@Test
	public void testSetAngleAndGetAngle() {
		pm.setAngle(2.0f);
		assertTrue(pm.getAngle() == 2.0f);

		pm.setAngle(Constants.PI);
		assertTrue(pm.getAngle() == Constants.PI);
	}

	@Test
	public void testUpdateFlagPosition() {
		pm.setPosition(new Vector2f(0.0f, 10.0f));
		assertTrue(pm.pickUpFlag());
		Vector2f pos = teamTwoFlag.getPosition();

		// Give player a new position
		pm.setPosition(new Vector2f(10.0f, 10.0f));
		pm.updateFlagPosition();

		// The flag should have changed position
		assertTrue(!(Tools.distanceBetween(teamTwoFlag.getPosition(), pos) <= 0.01f));
	}

	@Test
	public void testSetTeamAndGetTeam() {
		assertTrue(pm.getTeam() == teamOne);
		pm.setTeam(teamTwo);
		assertTrue(pm.getTeam() == teamTwo);
	}

}

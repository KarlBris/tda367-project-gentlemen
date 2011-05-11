package components;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controllers.FlagController;
import controllers.IController;
import controllers.PlayerController;
import controllers.RuleController;
import controllers.ScoreboardController;
import controllers.TeamController;
import core.Manager;
import factories.BallFactory;
import factories.CratePropFactory;
import factories.FlagFactory;
import factories.HorizontalWallPropFactory;
import factories.PlayerOneFactory;
import factories.PlayerTwoFactory;
import factories.RuleFactory;
import factories.ScoreboardFactory;
import factories.TeamFactory;
import factories.VerticalWallPropFactory;

public class StateComponent implements IComponent {

	/**
	 * @see components.IComponent#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#cleanup()
	 */
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#controllerAdded(controllers.IController)
	 */
	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see components.IComponent#controllerRemoved(controllers.IController)
	 */
	@Override
	public void controllerRemoved(final IController controller) {
		// TODO Auto-generated method stub

	}

	/**
	 * Initalizes the entities nessecary to play the game
	 */
	public void initializeEntities() {

		// Instantiate the rules

		final RuleController rules = (RuleController) Manager
				.instantiate(new RuleFactory());

		// Instantiate the teams
		final TeamController teamOne = (TeamController) Manager
				.instantiate(new TeamFactory());
		final TeamController teamTwo = (TeamController) Manager
				.instantiate(new TeamFactory());

		final PlayerController playerOne = (PlayerController) Manager
				.instantiate(new PlayerOneFactory());
		final PlayerController playerTwo = (PlayerController) Manager
				.instantiate(new PlayerTwoFactory());

		teamOne.setRules(rules);
		teamTwo.setRules(rules);

		teamOne.setTeamName("Red Team");
		teamOne.setHomePosition(Constants.TEAM_ONE_HOME_POSITION);
		teamTwo.setTeamName("Blue Team");
		teamTwo.setHomePosition(Constants.TEAM_TWO_HOME_POSITION);

		for (int i = 0; i < 140; i++) {
			// Added a random number so the physics engine move the ball apart
			Manager.instantiate(new BallFactory(), new Vector2f(
					Constants.VIEWPORT_WIDTH / 2.0f + (float) Math.random()
							/ 1000, Constants.VIEWPORT_HEIGHT / 2
							+ (float) Math.random() / 100));

		}

		playerOne.setTeam(teamOne);
		playerTwo.setTeam(teamTwo);

		// Instantiate flags
		final FlagController teamOneFlag = (FlagController) Manager
				.instantiate(new FlagFactory());
		final FlagController teamTwoFlag = (FlagController) Manager
				.instantiate(new FlagFactory());

		teamOneFlag.setTeam(teamOne);
		teamTwoFlag.setTeam(teamTwo);

		teamOneFlag.setColor(Constants.TEAM_ONE_COLOR);
		teamTwoFlag.setColor(Constants.TEAM_TWO_COLOR);

		// Instantiate scoreboard

		final ScoreboardController scoreboard = (ScoreboardController) Manager
				.instantiate(new ScoreboardFactory());

		scoreboard.addTeam(teamOne);
		scoreboard.addTeam(teamTwo);

		// Instantiate props

		// Top wall
		Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT));

		// Bottom wall
		Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, 0.0f));

		// Left wall
		Manager.instantiate(new VerticalWallPropFactory(), new Vector2f(0.0f,
				Constants.VIEWPORT_HEIGHT / 2));

		// Right wall
		Manager.instantiate(new VerticalWallPropFactory(), new Vector2f(
				Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT / 2));

		// Crates
		for (int i = 0; i < 3; i++) {
			Manager.instantiate(new CratePropFactory(), new Vector2f(2.0f,
					2.0f + i));
		}
	}
}

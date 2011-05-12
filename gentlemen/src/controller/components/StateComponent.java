package controller.components;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controller.entities.FlagController;
import controller.entities.IController;
import controller.entities.PlayerController;
import controller.entities.RuleController;
import controller.entities.ScoreboardController;
import controller.entities.TeamController;
import core.Manager;
import factories.entities.BallFactory;
import factories.entities.BuildingPropFactory;
import factories.entities.CratePropFactory;
import factories.entities.FlagFactory;
import factories.entities.HorizontalWallPropFactory;
import factories.entities.PlayerOneFactory;
import factories.entities.PlayerTwoFactory;
import factories.entities.RuleFactory;
import factories.entities.ScoreboardFactory;
import factories.entities.TeamFactory;
import factories.entities.VerticalWallPropFactory;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void controllerAdded(final IController controller) {
		// TODO Auto-generated method stub

	}

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
		for (int i = 0; i < 30; i++) {

			Vector2f[] restrictions = { Constants.TEAM_ONE_HOME_POSITION,
					Constants.TEAM_TWO_HOME_POSITION };

			Manager.instantiate(new CratePropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}

		// Buildings
		for (int i = 0; i < 10; i++) {

			Vector2f[] restrictions = { Constants.TEAM_ONE_HOME_POSITION,
					Constants.TEAM_TWO_HOME_POSITION };

			Manager.instantiate(new BuildingPropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}
	}
}

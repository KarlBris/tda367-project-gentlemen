package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import controller.components.KeyboardComponent;
import controller.entities.FlagController;
import controller.entities.PlayerController;
import controller.entities.RuleController;
import controller.entities.ScoreboardController;
import controller.entities.TeamController;
import core.levels.AbstractLevel;
import core.levels.RandomLevel;
import factories.entities.BallFactory;
import factories.entities.FlagFactory;
import factories.entities.PlayerOneFactory;
import factories.entities.PlayerTwoFactory;
import factories.entities.RuleFactory;
import factories.entities.ScoreboardFactory;
import factories.entities.TeamFactory;

/**
 * This class is responsible for managing and setting up levels
 */
public class LevelManager {

	private IMainController main = MainControllerFactory.get();

	public void update() {
		KeyboardComponent keyboard = main.getKeyboardComponent();

		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_R)) {
			main.removeAll();
			initializeEntities();
		}

	}

	/**
	 * Initializes the entities necessary to play the game
	 */
	public void initializeEntities() {

		final AbstractLevel level = new RandomLevel();

		buildTeams(level);

	}

	private void buildTeams(final AbstractLevel level) {

		// Instantiate the rules

		final RuleController rules = main.instantiate(new RuleFactory());

		// Instantiate the teams
		final TeamController teamOne = main.instantiate(new TeamFactory());
		final TeamController teamTwo = main.instantiate(new TeamFactory());

		final PlayerController playerOne = main
				.instantiate(new PlayerOneFactory());
		final PlayerController playerTwo = main
				.instantiate(new PlayerTwoFactory());

		teamOne.setRules(rules);
		teamTwo.setRules(rules);

		teamOne.setTeamName("Red Team");
		teamOne.setHomePosition(level.getTeamOneHomePosition());
		teamTwo.setTeamName("Blue Team");
		teamTwo.setHomePosition(level.getTeamTwoHomePosition());

		for (int i = 0; i < 140; i++) {
			// Added a random number so the physics engine move the ball apart
			main.instantiate(
					new BallFactory(),
					new Vector2f(level.getBallSpawnPosition().x
							+ (float) Math.random() / 1000, level
							.getBallSpawnPosition().y
							+ (float) Math.random()
							/ 100));

		}

		playerOne.setTeam(teamOne);
		playerTwo.setTeam(teamTwo);

		// Instantiate flags
		final FlagController teamOneFlag = main
				.instantiate(new FlagFactory());
		final FlagController teamTwoFlag = main
				.instantiate(new FlagFactory());

		teamOneFlag.setTeam(teamOne);
		teamTwoFlag.setTeam(teamTwo);

		teamOneFlag.setColor(Constants.TEAM_ONE_COLOR);
		teamTwoFlag.setColor(Constants.TEAM_TWO_COLOR);

		// Instantiate scoreboard

		final ScoreboardController scoreboard = main
				.instantiate(new ScoreboardFactory());

		scoreboard.addTeam(teamOne);
		scoreboard.addTeam(teamTwo);

	}

}

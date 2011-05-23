package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
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
import core.levels.LevelOne;
import core.levels.LevelTwo;
import core.levels.RandomLevel;
import core.levels.WinLevel;
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
public final class LevelManager {

	private final IMainController main = MainControllerFactory.get();

	public void update() {
		final KeyboardComponent keyboard = main.getKeyboardComponent();

		// Ctrl+R, Create new RandomLevel
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_R)) {
			main.removeAll();
			initializeEntities(new RandomLevel());
		}

		// Ctrl+1, Create new LevelOne
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_1)) {
			main.removeAll();
			initializeEntities(new LevelOne());
		}

		// Ctrl+2, Create new LevelTwo
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_2)) {
			main.removeAll();
			initializeEntities(new LevelTwo());
		}

		for (final TeamController tc : main.find(TeamController.class)) {
			if (tc.hasWon) {
				final Color winColor = tc.getColor();
				main.removeAll();
				new WinLevel(winColor);

				break;
			}
		}
	}

	/**
	 * Initializes the entities necessary to play the game
	 */
	public void initializeEntities(final AbstractLevel level) {

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

		teamOne.setColor(Constants.TEAM_ONE_COLOR);
		teamTwo.setColor(Constants.TEAM_TWO_COLOR);

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
		final FlagController teamOneFlag = main.instantiate(new FlagFactory());
		final FlagController teamTwoFlag = main.instantiate(new FlagFactory());

		teamOneFlag.setTeam(teamOne);
		teamTwoFlag.setTeam(teamTwo);

		// teamOneFlag.setColor(Constants.TEAM_ONE_COLOR);
		// teamTwoFlag.setColor(Constants.TEAM_TWO_COLOR);

		// Instantiate scoreboard

		final ScoreboardController scoreboard = main
				.instantiate(new ScoreboardFactory());

		scoreboard.addTeam(teamOne);
		scoreboard.addTeam(teamTwo);

	}

}

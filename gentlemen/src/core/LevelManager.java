package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import controller.components.KeyboardComponent;
import controller.entities.gamemode.RuleController;
import controller.entities.gamemode.RuleFactory;
import controller.entities.gamemode.ScoreboardController;
import controller.entities.gamemode.ScoreboardFactory;
import controller.entities.gamemode.TeamController;
import controller.entities.gamemode.TeamFactory;
import controller.entities.gameplay.BallFactory;
import controller.entities.gameplay.FlagController;
import controller.entities.gameplay.FlagFactory;
import controller.entities.gameplay.PlayerController;
import controller.entities.gameplay.PlayerOneFactory;
import controller.entities.gameplay.PlayerTwoFactory;
import controller.entities.props.BlockPropFactory;
import core.levels.ILevel;
import core.levels.LevelOne;
import core.levels.LevelThree;
import core.levels.LevelTwo;
import core.levels.RandomLevel;
import core.levels.WinLevel;

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
			generateLevel(new RandomLevel(), true);
		}

		// Ctrl+1, Create new LevelOne
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_1)) {
			main.removeAll();
			generateLevel(new LevelOne(), true);
		}

		// Ctrl+2, Create new LevelTwo
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_2)) {
			main.removeAll();
			generateLevel(new LevelTwo(), true);
		}

		// Ctrl+3, Create new LevelThree
		if (keyboard.getKey(Keyboard.KEY_LCONTROL)
				&& keyboard.getKeyDown(Keyboard.KEY_3)) {
			main.removeAll();

			generateLevel(new LevelThree(), true);
			// initializeEntities(new LevelTwo(), true);
		}

		for (final TeamController tc : main.find(TeamController.class)) {
			if (tc.hasWon) {
				final Color winColor = tc.getColor();
				main.removeAll();
				generateLevel(new WinLevel(winColor), false);

				break;
			}
		}
	}

	/**
	 * Initializes the entities necessary to play the game
	 */
	public void initializeEntities(final ILevel level,
			final boolean spawnBallsAndFlags) {

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

		if (spawnBallsAndFlags) {
			for (int i = 0; i < 140; i++) {
				// Added a random number so the physics engine move the ball
				// apart
				main.instantiate(
						new BallFactory(),
						new Vector2f(level.getBallSpawnPosition().x
								+ (float) Math.random() / 1000, level
								.getBallSpawnPosition().y
								+ (float) Math.random() / 100));

			}
		}

		playerOne.setTeam(teamOne);
		playerTwo.setTeam(teamTwo);

		// Instantiate flags

		if (spawnBallsAndFlags) {
			final FlagController teamOneFlag = main
					.instantiate(new FlagFactory());
			final FlagController teamTwoFlag = main
					.instantiate(new FlagFactory());

			teamOneFlag.setTeam(teamOne);
			teamTwoFlag.setTeam(teamTwo);
		}

		// Instantiate scoreboard

		final ScoreboardController scoreboard = main
				.instantiate(new ScoreboardFactory());

		scoreboard.addTeam(teamOne);
		scoreboard.addTeam(teamTwo);
		scoreboard.setRules(rules);

	}

	public void generateLevel(final ILevel level,
			final boolean spawnBallsAndFlags) {

		final String levelString = level.getLevelString();

		// Set the positions so that if there are no spawn or home positions
		// specified in the levelString, one does not get a NullPointerException
		level.setBallSpawnPosition(new Vector2f(1.0f, 1.0f));
		level.setTeamOneHomePosition(new Vector2f(1.0f, 1.0f));
		level.setTeamTwoHomePosition(new Vector2f(1.0f, 1.0f));

		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 29; j++) {
				if (levelString.substring(j + 29 * i, (j + 29 * i) + 1).equals(
						"X")) {
					main.instantiate(new BlockPropFactory(), new Vector2f(
							j + 1, i + 1));
				}

				if (levelString.substring(j + 29 * i, (j + 29 * i) + 1).equals(
						"1")) {
					level.setTeamOneHomePosition(new Vector2f(j + 1, i + 1));
				}

				if (levelString.substring(j + 29 * i, (j + 29 * i) + 1).equals(
						"2")) {
					level.setTeamTwoHomePosition(new Vector2f(j + 1, i + 1));
				}

				if (levelString.substring(j + 29 * i, (j + 29 * i) + 1).equals(
						"B")) {
					level.setBallSpawnPosition(new Vector2f(j + 1, i + 1));
				}

			}
		}

		level.instantiateProps();
		initializeEntities(level, spawnBallsAndFlags);
	}
}

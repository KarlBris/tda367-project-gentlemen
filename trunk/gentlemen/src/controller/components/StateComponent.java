package controller.components;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.common.IController;
import controller.components.levels.AbstractLevel;
import controller.components.levels.RandomLevel;
import controller.entities.FlagController;
import controller.entities.PlayerController;
import controller.entities.RuleController;
import controller.entities.ScoreboardController;
import controller.entities.TeamController;
import core.Manager;
import factories.entities.BallFactory;
import factories.entities.FlagFactory;
import factories.entities.PlayerOneFactory;
import factories.entities.PlayerTwoFactory;
import factories.entities.RuleFactory;
import factories.entities.ScoreboardFactory;
import factories.entities.TeamFactory;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		if (Manager.getKeyboard().getKey(Keyboard.KEY_LCONTROL)
				&& Manager.getKeyboard().getKeyDown(Keyboard.KEY_R)) {
			Manager.removeAll();
			initializeEntities();
		}

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

		final AbstractLevel level = new RandomLevel();

		buildTeams(level);

	}

	private void buildTeams(final AbstractLevel level) {

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
		teamOne.setHomePosition(level.getTeamOneHomePosition());
		teamTwo.setTeamName("Blue Team");
		teamTwo.setHomePosition(level.getTeamTwoHomePosition());

		for (int i = 0; i < 140; i++) {
			// Added a random number so the physics engine move the ball apart
			Manager.instantiate(
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

	}
}

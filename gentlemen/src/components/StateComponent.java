package components;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controllers.FlagController;
import controllers.IController;
import controllers.PlayerController;
import controllers.PropController;
import controllers.RuleController;
import controllers.ScoreboardController;
import controllers.TeamController;
import core.Manager;
import factories.CratePropFactory;
import factories.HorizontalWallPropFactory;
import factories.PlayerOneFactory;
import factories.PlayerTwoFactory;
import factories.RuleFactory;
import factories.ScoreboardFactory;
import factories.TeamFactory;
import factories.TeamFlagOneFactory;
import factories.TeamFlagTwoFactory;
import factories.VerticalWallPropFactory;

public class StateComponent implements IComponent {

	@Override
	public void initialize() {
		// TODO Auto-generated method stub

	}

	@Override
	public void instantiatePermanentEntities() {

		// Instantiate the rules
		if (Manager.find(RuleController.class).size() == 0) {
			Manager.instantiate(new RuleFactory());
		}

		// Instantiate the player
		if (Manager.find(PlayerController.class).size() == 0) {

			// Instantiate the teams
			final TeamController teamOne = (TeamController) Manager
					.instantiate(new TeamFactory());
			final TeamController teamTwo = (TeamController) Manager
					.instantiate(new TeamFactory());

			final PlayerController playerOne = (PlayerController) Manager
					.instantiate(new PlayerOneFactory(), new Vector2f(
							Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
			final PlayerController playerTwo = (PlayerController) Manager
					.instantiate(new PlayerTwoFactory(), new Vector2f(
							Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));

			playerOne.setTeam(teamOne);
			playerTwo.setTeam(teamTwo);

			// Instantiate flags
			final FlagController teamOneFlag = (FlagController) Manager
					.instantiate(new TeamFlagOneFactory(), new Vector2f(10.0f,
							10.0f));
			final FlagController teamTwoFlag = (FlagController) Manager
					.instantiate(new TeamFlagTwoFactory(), new Vector2f(2.0f,
							10.0f));

			teamOneFlag.setTeam(teamOne);
			teamTwoFlag.setTeam(teamTwo);

			// Instantiate scoreboard

			final ScoreboardController scoreboard = (ScoreboardController) Manager
					.instantiate(new ScoreboardFactory());

			scoreboard.addTeam(teamOne);
			scoreboard.addTeam(teamTwo);
		}

		// Instantiate props
		if (Manager.find(PropController.class).size() == 0) {
			// Top wall
			Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
					Constants.VIEWPORT_WIDTH / 2, Constants.VIEWPORT_HEIGHT));

			// Bottom wall
			Manager.instantiate(new HorizontalWallPropFactory(), new Vector2f(
					Constants.VIEWPORT_WIDTH / 2, 0.0f));

			// Left wall
			Manager.instantiate(new VerticalWallPropFactory(), new Vector2f(
					0.0f, Constants.VIEWPORT_HEIGHT / 2));

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

	@Override
	public void cleanup() {
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

}

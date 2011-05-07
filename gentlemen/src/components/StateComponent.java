package components;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controllers.FlagController;
import controllers.IController;
import controllers.PlayerController;
import controllers.PropController;
import core.Manager;
import factories.CratePropFactory;
import factories.HorizontalWallPropFactory;
import factories.PlayerOneFactory;
import factories.PlayerTwoFactory;
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

		// Instantiate the player
		if (Manager.find(PlayerController.class).size() == 0) {
			Manager.instantiate(new PlayerOneFactory(),
					new Vector2f(Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
			Manager.instantiate(new PlayerTwoFactory(),
					new Vector2f(Constants.VIEWPORT_WIDTH / 2,
							Constants.VIEWPORT_HEIGHT / 2));
		}

		if (Manager.find(FlagController.class).size() == 0) {
			Manager.instantiate(new TeamFlagOneFactory(), new Vector2f(10.0f,
					10.0f));
			Manager.instantiate(new TeamFlagTwoFactory(), new Vector2f(2.0f,
					10.0f));
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

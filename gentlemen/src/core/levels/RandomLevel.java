package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.BuildingPropFactory;
import factories.entities.CratePropFactory;

public final class RandomLevel extends SuperLevel {

	private IMainController main = MainControllerFactory.get();

	public RandomLevel() {
		
		super(	"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"              B              " +
				" 1                         2 " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             " +
				"                             ");

	}

	@Override
	public void instantiateProps() {
		super.instatiateWalls();

		// Crates
		for (int i = 0; i < 30; i++) {

			final Vector2f[] restrictions = { getTeamOneHomePosition(),
					getTeamTwoHomePosition() };

			main.instantiate(new CratePropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}

		// Buildings
		for (int i = 0; i < 10; i++) {

			final Vector2f[] restrictions = { getTeamOneHomePosition(),
					getTeamTwoHomePosition() };

			main.instantiate(new BuildingPropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}

	}

}

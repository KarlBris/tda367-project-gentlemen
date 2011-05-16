package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import core.Manager;
import factories.entities.BuildingPropFactory;
import factories.entities.CratePropFactory;

public class RandomLevel extends AbstractLevel {

	public RandomLevel() {
		super(Constants.TEAM_ONE_HOME_POSITION,
				Constants.TEAM_TWO_HOME_POSITION, new Vector2f(
						Constants.VIEWPORT_WIDTH / 2,
						Constants.VIEWPORT_HEIGHT / 2));

		instantiateProps();

	}

	private void instantiateProps() {
		super.instatiateWalls();

		// Crates
		for (int i = 0; i < 30; i++) {

			final Vector2f[] restrictions = { getTeamOneHomePosition(),
					getTeamTwoHomePosition() };

			Manager.instantiate(new CratePropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}

		// Buildings
		for (int i = 0; i < 10; i++) {

			final Vector2f[] restrictions = { getTeamOneHomePosition(),
					getTeamTwoHomePosition() };

			Manager.instantiate(new BuildingPropFactory(), Tools
					.randomVectorInArea(new Vector2f(0, 0),
							new Vector2f(Constants.VIEWPORT_WIDTH,
									Constants.VIEWPORT_HEIGHT), restrictions));

		}

	}

}

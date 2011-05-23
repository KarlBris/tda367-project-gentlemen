package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import utilities.Tools;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.BlockPropFactory;
import factories.entities.CratePropFactory;
import factories.entities.HorizontalWallPropFactory;

public final class LevelTwo extends AbstractLevel {

	private final IMainController main = MainControllerFactory.get();

	public LevelTwo() {

		super(new Vector2f(5.0f, 8.0f), new Vector2f(
				Constants.VIEWPORT_WIDTH - 5.0f, 8.0f), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, 8.0f));

		instantiateProps();
	}

	private void instantiateProps() {
		super.instatiateWalls();

		// Bottom part

		main.instantiate(new HorizontalWallPropFactory(), new Vector2f(15.0f,
				16.0f));

		// Ball cage

		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(13.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(13.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 11.0f));

		// Left cage

		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 5.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 10.0f));

		// Right cage

		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(24.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 5.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(24.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 10.0f));

		// Top field of crates
		for (int i = 0; i < 30; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 1.0f), new Vector2f(29.0f, 4.0f)));
		}

		// Bottom field of crates
		for (int i = 0; i < 30; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 12.0f), new Vector2f(29.0f, 4.0f)));
		}
	}

}

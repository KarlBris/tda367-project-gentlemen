package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.BlockPropFactory;

public class LevelOne extends AbstractLevel {

	private final IMainController main = MainControllerFactory.get();

	public LevelOne() {

		super(new Vector2f(2.0f, 2.0f), new Vector2f(
				Constants.VIEWPORT_WIDTH - 2.0f, 2.0f), new Vector2f(
				Constants.VIEWPORT_WIDTH / 2, 4.0f));

		instantiateProps();
	}

	private void instantiateProps() {
		super.instatiateWalls();

		// Left base
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 1.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 2.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 4.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 7.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 13.0f));

		// Right base
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 1.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 2.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 4.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 7.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 13.0f));

		// Ball divider

		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 1.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 2.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 3.0f));

		// H-shape

		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 13.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 13.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 13.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 3.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 12.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 13.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(13.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 7.0f));

		// Path blocker

		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 16.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 15.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 14.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 14.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 14.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 11.0f));

	}

}

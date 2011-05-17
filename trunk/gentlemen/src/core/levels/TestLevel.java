package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Constants;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.PropFactory;

public class TestLevel extends AbstractLevel {

	private IMainController main = MainControllerFactory.get();

	public TestLevel() {
		super(new Vector2f(3.0f, 3.0f), new Vector2f(3.0f,
				Constants.VIEWPORT_HEIGHT - 3.0f), new Vector2f(
				Constants.VIEWPORT_WIDTH - 4.0f, Constants.VIEWPORT_HEIGHT / 2));

		initializeProps();
	}

	private void initializeProps() {
		super.instatiateWalls();

		// Middle wall

		main.instantiate(new PropFactory(), new Vector2f(1.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(3.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(5.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(7.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(9.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(11.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(13.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(15.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(17.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(19.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(21.0f,
				Constants.VIEWPORT_HEIGHT / 2));
		main.instantiate(new PropFactory(), new Vector2f(23.0f,
				Constants.VIEWPORT_HEIGHT / 2));

	}

}

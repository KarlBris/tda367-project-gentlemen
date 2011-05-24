package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.IMainController;
import controller.MainControllerFactory;
import controller.entities.props.CratePropFactory;

public final class LevelTwo extends AbstractLevel {

	private final IMainController main = MainControllerFactory.get();

	public LevelTwo() {

		super("                             " + "                             "
				+ "                             "
				+ "                             "
				+ "  XXXXX    XX   XX    XXXXX  "
				+ "      X    X     X    X      "
				+ "      X    X     X    X      "
				+ "    1 X    X  B  X    X 2    "
				+ "      X    X     X    X      "
				+ "      X    X     X    X      "
				+ "  XXXXX    XX   XX    XXXXX  "
				+ "                             "
				+ "                             "
				+ "                             "
				+ "                             "
				+ "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	@Override
	public void instantiateProps() {
		super.instatiateWalls();

		// Top field of crates
		for (int i = 0; i < 10; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 1.0f), new Vector2f(29.0f, 4.0f)));
		}

		// Bottom field of crates
		for (int i = 0; i < 10; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 12.0f), new Vector2f(29.0f, 4.0f)));
		}
	}

}

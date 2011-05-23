package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;
import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.CratePropFactory;

public final class LevelOne extends SuperLevel {

	private final IMainController main = MainControllerFactory.get();

	public LevelOne() {

		super(	"    X         X         X    " +
				" 1  X         X         X  2 " +
				"    X    XX   X   XX    X    " +
				"   XX    XX       XX    XX   " +
				"         XX   B   XX         " +
				"         XX       XX         " +
				"   XX    XXXXXXXXXXX    XX   " +
				"         XX       XX         " +
				"         XX       XX         " +
				"   XX    XX   X   XX    XX   " +
				"    X    XX  XXX  XX    X    " +
				"    X    XX       XX    X    " +
				"   XX    XX       XX    XX   " +
				"             XXX             " +
				"              X              " +
				"              X              ");
	}

	public void instantiateProps() {
		super.instatiateWalls();

		// Bottom field of crates
		for (int i = 0; i < 30; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 13.0f), new Vector2f(29.0f, 4.0f)));
		}

	}

}

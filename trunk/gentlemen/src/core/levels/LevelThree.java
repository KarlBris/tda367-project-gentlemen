package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Tools;

import controller.IMainController;
import controller.MainControllerFactory;
import factories.entities.CratePropFactory;

public final class LevelThree extends SuperLevel {

	private final IMainController main = MainControllerFactory.get();

	public LevelThree() {
		
		super(	"X          XXXXXXX          X" +
				"            XXXXX            " +
				"  X   XXX    XXX    XXX   X  " +
				"  XX  XXXX   XXX   XXXX  XX  " +
				"  XX         XXX         XX  " +
				"  XXX         X         XXX  " +
				"       XXXX       XXXX       " +
				" 1     XXXXX  B  XXXXX     2 " +
				"       XXXX       XXXX       " +
				"  XXX         X         XXX  " +
				"  XX         XXX         XX  " +
				"  XX  XXXX   XXX   XXXX  XX  " +
				"  X   XXX    XXX    XXX   X  " +
				"            XXXXX            " +
				"           XXXXXXX           " +
				"XXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	}

	@Override
	public void instantiateProps() {
		super.instatiateWalls();
		
		// 'field of crates
		for (int i = 0; i < 30; i++) {
			main.instantiate(new CratePropFactory(), Tools.randomVectorInArea(
					new Vector2f(1.0f, 1.0f), new Vector2f(29.0f, 16.0f)));
		}
	}
}

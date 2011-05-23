package core.levels;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import controller.IMainController;
import controller.MainControllerFactory;
import controller.entities.PulsatingPropController;
import factories.entities.BlockPropFactory;

public class WinLevel extends AbstractLevel {

	private final IMainController main = MainControllerFactory.get();
	private final Color color;

	public WinLevel(final Color winColor) {
		super(new Vector2f(4.0f, 5.0f), new Vector2f(-100.0f, -100.0f),
				new Vector2f(-100.0f, -100.0f));

		color = winColor;

		instantiateProps();
	}

	private void instantiateProps() {
		super.instatiateWalls();

		// W
		main.instantiate(new BlockPropFactory(), new Vector2f(2.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(8.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(8.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(8.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(8.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(9.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(9.0f, 11.0f));

		// I

		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 11.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 11.0f));

		// N

		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(22.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(24.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 9.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(28.0f, 4.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 10.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 11.0f));

		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 4.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 5.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 6.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 7.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 8.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 9.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 10.0f));

		// Underscore

		main.instantiate(new BlockPropFactory(), new Vector2f(3.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(4.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(5.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(6.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(7.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(8.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(9.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(10.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(11.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(12.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(13.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(14.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(15.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(16.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(17.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(18.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(19.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(20.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(21.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(22.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(23.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(24.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(25.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(26.0f, 13.0f));
		main.instantiate(new BlockPropFactory(), new Vector2f(27.0f, 13.0f));

		// Color them all according to the winning team color
		for (final PulsatingPropController bcp : main
				.find(PulsatingPropController.class)) {
			bcp.setColors(color, Color.WHITE, 100);
		}

	}

}

package core.levels;

import utilities.Color;
import controller.IMainController;
import controller.MainControllerFactory;
import controller.entities.PulsatingPropController;

public class WinLevel extends SuperLevel {

	private final IMainController main = MainControllerFactory.get();
	private final Color color;

	public WinLevel(final Color winningTeamColor) {
//		super(new Vector2f(2.0f, 2.0f), new Vector2f(
//				Constants.VIEWPORT_WIDTH - 2.0f, 2.0f),
//				new Vector2f(0.0f, 0.0f));
		
		super(	"                             " +
				" 1            B            2 " +
				"                             " +
				" XXX     XXX XXXX  X     XXX " +
				"  XX  X  XX   XX   XX    XX  " +
				"  XX  X  XX   XX   XXX   XX  " +
				"   X XXX X    XX   XX X  XX  " +
				"   X X X X    XX   XX  X XX  " +
				"   X X X X    XX   XX   XXX  " +
				"   XXX XXX    XX   XX    XX  " +
				"   XX   XX   XXXX XXX     X  " +
				"                             " +
				"  XXXXXXXXXXXXXXXXXXXXXXXXX  " +
				"                             " +
				"                             " +
				"                             ");
		
		color = winningTeamColor;
	}

	public void instantiateProps() {
		super.instatiateWalls();

		// Color all props according to the winning team color
		for (final PulsatingPropController bcp : main
				.find(PulsatingPropController.class)) {
			bcp.setColors(color, Color.WHITE, 100);
		}

	}

}

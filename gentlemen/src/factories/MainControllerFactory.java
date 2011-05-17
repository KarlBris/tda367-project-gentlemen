package factories;

import controller.IMainController;
import controller.MainController;

public class MainControllerFactory {

	public static IMainController get() {
		return MainController.get();
	}

}

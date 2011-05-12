package factories;

import model.IMainModel;
import controller.IMainController;
import controller.MainController;

public class MainControllerFactory {

	public static IMainController get(final IMainModel mainModel) {
		return new MainController(mainModel);
	}

}

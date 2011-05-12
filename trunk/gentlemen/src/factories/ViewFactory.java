package factories;

import model.IMainModel;
import view.IView;
import view.View2D;

public class ViewFactory {

	public static IView get(final IMainModel mainModel) {
		return new View2D(mainModel);
	}

}

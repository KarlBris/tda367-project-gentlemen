package factories;

import model.IMainModel;
import model.MainModel;

public class MainModelFactory {

	public static IMainModel get() {
		return new MainModel();
	}

}

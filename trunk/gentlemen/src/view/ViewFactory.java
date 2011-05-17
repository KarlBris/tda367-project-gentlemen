package view;

import view.twodimensions.View2D;

public class ViewFactory {

	public static IView get() {
		return View2D.get();
	}

}

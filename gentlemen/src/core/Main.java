package core;

import utilities.Tools;

public final class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		Tools.identifyOS();

		Manager.start();
	}
}

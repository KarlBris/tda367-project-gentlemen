package core;

import utilities.Tools;

public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		Tools.identifyOS();

		Manager.start();
	}
}

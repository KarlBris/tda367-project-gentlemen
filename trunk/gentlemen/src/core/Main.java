package core;

import java.io.File;

public class Main {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {

		if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_LINUX) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "linux");
		} else if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_MACOSX) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "macosx");
		} else if (org.lwjgl.LWJGLUtil.getPlatform() == org.lwjgl.LWJGLUtil.PLATFORM_WINDOWS) {
			System.setProperty("org.lwjgl.librarypath",
					System.getProperty("user.dir") + File.separator
							+ "lwjgl-2.7.1" + File.separator + "native"
							+ File.separator + "windows");
		}

		Manager.start();
	}
}

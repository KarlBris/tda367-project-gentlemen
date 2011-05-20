package utilities;

import java.util.Random;

/**
 * This class represents a color with float values
 */
public final class Color {

	public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
	public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);
	public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
	public static final Color GREEN = new Color(0.0f, 1.0f, 0.0f);
	public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
	public static final Color YELLOW = new Color(1.0f, 1.0f, 0.0f);
	public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
	public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
	public static final Color BROWN = new Color(0.6f, 0.4f, 0.2f);
	public static final Color IT = new Color(0.04f, 0.8f, 0.85f);

	private final float red;
	private final float green;
	private final float blue;
	private final float alpha;

	/**
	 * Sets the colors as intended
	 * 
	 * @param red
	 *            the float value between 0.0f and 1.0f representing the red
	 *            color
	 * @param green
	 *            the float value between 0.0f and 1.0f representing the green
	 *            color
	 * @param blue
	 *            the float value between 0.0f and 1.0f representing the blue
	 *            color
	 * @param alpha
	 *            the float value between 0.0f and 1.0f representing the alpha
	 *            channel
	 */
	public Color(final float red, final float green, final float blue,
			final float alpha) {
		if (red > 1.0f) {
			this.red = 1.0f;
		} else if (red < 0.0f) {
			this.red = 0.0f;
		} else {
			this.red = red;
		}

		if (green > 1.0f) {
			this.green = 1.0f;
		} else if (green < 0.0f) {
			this.green = 0.0f;
		} else {
			this.green = green;
		}

		if (blue > 1.0f) {
			this.blue = 1.0f;
		} else if (blue < 0.0f) {
			this.blue = 0.0f;
		} else {
			this.blue = blue;
		}

		if (alpha > 1.0f) {
			this.alpha = 1.0f;
		} else if (alpha < 0.0f) {
			this.alpha = 0.0f;
		} else {
			this.alpha = alpha;
		}
	}

	/**
	 * Sets the colors as intended. Since no alpha value is set, default it to
	 * 1.0f
	 * 
	 * @param red
	 *            the float value between 0.0f and 1.0f representing the red
	 *            color
	 * @param green
	 *            the float value between 0.0f and 1.0f representing the green
	 *            color
	 * @param blue
	 *            the float value between 0.0f and 1.0f representing the blue
	 *            color
	 */
	public Color(final float red, final float green, final float blue) {
		this(red, green, blue, 1.0f);
	}

	/**
	 * @return the red value of the color
	 */
	public float getRed() {
		return red;
	}

	/**
	 * @return the green value of the color
	 */
	public float getGreen() {
		return green;
	}

	/**
	 * @return the blue value of the color
	 */
	public float getBlue() {
		return blue;
	}

	/**
	 * @return the alpha value of the color
	 */
	public float getAlpha() {
		return alpha;
	}

	/**
	 * Randomizes a color and returns it, alpha is fixed at 1.0f
	 * 
	 * @return the randomized color
	 */
	public static Color randomColor() {
		final Random rand = new Random();
		return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	}

	/**
	 * Randomizes a color, both RGB and alpha channels, and returns it
	 * 
	 * @return the randomized color
	 */
	public static Color randomColorAndAlpha() {
		final Random rand = new Random();
		return new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(),
				rand.nextFloat());
	}
}

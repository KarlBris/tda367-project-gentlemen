package utilities;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ColorTest {

	@Test
	public void testColorFloatFloatFloatFloat() {

		// Try with normal parameters
		try {
			new Color(1.0f, 1.0f, 1.0f, 1.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}

		// Try with way too large parameters
		try {
			new Color(100.0f, 100.0f, 100.0f, 100.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}

		// Try with negative parameters
		try {
			new Color(-1.0f, -1.0f, -1.0f, -1.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}

	}

	@Test
	public void testColorFloatFloatFloat() {

		// Try with normal parameters
		try {
			new Color(1.0f, 1.0f, 1.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}

		// Try with way too large parameters
		try {
			new Color(100.0f, 100.0f, 100.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}

		// Try with negative parameters
		try {
			new Color(-1.0f, -1.0f, -1.0f);
		} catch (final Exception e) {
			fail("Exception is not expected!");
		}
	}

	@Test
	public void testGetRed() {

		// Test normal case
		float testRed = 0.7f;

		Color c = new Color(testRed, 1.0f, 1.0f);
		assertTrue(Tools.floatsEqual(c.getRed(), testRed));

		// Test too large value
		testRed = 100.0f;

		c = new Color(testRed, 1.0f, 1.0f);
		assertTrue(Tools.floatsEqual(c.getRed(), 1.0f));

		// Test negative value
		testRed = -1.0f;

		c = new Color(testRed, 1.0f, 1.0f);
		assertTrue(Tools.floatsEqual(c.getRed(), 0.0f));
	}

	@Test
	public void testGetGreen() {

		// Test normal case
		float testGreen = 0.7f;

		Color c = new Color(1.0f, testGreen, 1.0f);
		assertTrue(Tools.floatsEqual(c.getGreen(), testGreen));

		// Test too large value
		testGreen = 100.0f;

		c = new Color(1.0f, testGreen, 1.0f);
		assertTrue(Tools.floatsEqual(c.getGreen(), 1.0f));

		// Test negative value
		testGreen = -1.0f;

		c = new Color(1.0f, testGreen, 1.0f);
		assertTrue(Tools.floatsEqual(c.getGreen(), 0.0f));
	}

	@Test
	public void testGetBlue() {

		// Test normal case
		float testBlue = 0.7f;

		Color c = new Color(1.0f, 1.0f, testBlue);
		assertTrue(Tools.floatsEqual(c.getBlue(), testBlue));

		// Test too large value
		testBlue = 100.0f;

		c = new Color(1.0f, 1.0f, testBlue);
		assertTrue(Tools.floatsEqual(c.getBlue(), 1.0f));

		// Test negative value
		testBlue = -1.0f;

		c = new Color(1.0f, 1.0f, testBlue);
		assertTrue(Tools.floatsEqual(c.getBlue(), 0.0f));
	}

	@Test
	public void testGetAlpha() {

		// Test normal case
		float testAlpha = 0.7f;

		Color c = new Color(1.0f, 1.0f, 1.0f, testAlpha);
		assertTrue(Tools.floatsEqual(c.getAlpha(), testAlpha));

		// Test too large value
		testAlpha = 100.0f;

		c = new Color(1.0f, 1.0f, 1.0f, testAlpha);
		assertTrue(Tools.floatsEqual(c.getAlpha(), 1.0f));

		// Test negative value
		testAlpha = -1.0f;

		c = new Color(1.0f, 1.0f, 1.0f, testAlpha);
		assertTrue(Tools.floatsEqual(c.getAlpha(), 0.0f));
	}

	@Test
	public void testRandomColor() {

		try {
			final Color c = utilities.Color.randomColor();

			assertTrue(c.getRed() <= 1.0f && c.getRed() >= 0.0f);
			assertTrue(c.getGreen() <= 1.0f && c.getGreen() >= 0.0f);
			assertTrue(c.getBlue() <= 1.0f && c.getBlue() >= 0.0f);
			assertTrue(Tools.floatsEqual(c.getAlpha(), 1.0f));

		} catch (final Exception e) {
			fail("Exception not expected!");
		}

	}

	@Test
	public void testRandomColorAndAlpha() {
		try {
			final Color c = utilities.Color.randomColorAndAlpha();

			assertTrue(c.getRed() <= 1.0f && c.getRed() >= 0.0f);
			assertTrue(c.getGreen() <= 1.0f && c.getGreen() >= 0.0f);
			assertTrue(c.getBlue() <= 1.0f && c.getBlue() >= 0.0f);
			assertTrue(c.getAlpha() <= 1.0f && c.getBlue() >= 0.0f);

		} catch (final Exception e) {
			fail("Exception not expected!");
		}
	}

}

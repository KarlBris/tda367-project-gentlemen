package utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.jbox2d.common.Vec2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.lwjgl.util.Point;
import org.lwjgl.util.vector.Vector2f;

public class ToolsTest {

	@Before
	public void setUp() throws Exception {

		Tools.identifyOS();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFloatsEqual() {

		float floatTest = 0.0f;
		assertTrue(Tools.floatsEqual(floatTest, floatTest));

		floatTest = -1.0f;
		assertTrue(Tools.floatsEqual(floatTest, floatTest));

		floatTest = 100.0f;
		assertTrue(Tools.floatsEqual(floatTest, floatTest));

		floatTest = 1.0f;
		assertTrue(Tools.floatsEqual(floatTest, floatTest));

		assertFalse(Tools.floatsEqual(floatTest, floatTest + 0.1f));
		assertFalse(Tools.floatsEqual(floatTest, floatTest + 0.01f));
		assertFalse(Tools.floatsEqual(floatTest, floatTest + 0.001f));
	}

	@Test
	public void testClampValue() {
		// Test a value inside the clamp range
		assertTrue(Tools.floatsEqual(Tools.clampValue(7.0f, 0.0f, 10.0f), 7.0f));

		// Test a value greater than the clamp range
		assertTrue(Tools.floatsEqual(Tools.clampValue(15.0f, 0.0f, 10.0f),
				10.0f));

		// Test a value less than the clamp range
		assertTrue(Tools.floatsEqual(Tools.clampValue(-23.0f, 0.0f, 10.0f),
				0.0f));

		// Test a value less than the negative clamp value
		assertTrue(Tools.floatsEqual(Tools.clampValue(-23.0f, -10.0f, 10.0f),
				-10.0f));
	}

	@Test
	public void testWrapAngle() {

		// Test a value of 3*PI
		float testAngle = Constants.PI + Constants.TWO_PI;
		assertTrue(Tools.floatsEqual(Tools.wrapAngle(testAngle), Constants.PI));

		// Test negative angles
		testAngle = -Constants.PI;
		assertTrue(Tools.floatsEqual(Tools.wrapAngle(testAngle), Constants.PI));

		// Test that there is no change when the value is already in the correct
		// range
		testAngle = Constants.PI;
		assertTrue(Tools.floatsEqual(Tools.wrapAngle(testAngle), Constants.PI));

	}

	@Test
	public void testClosestAngleDelta() {

		// Test the angle delta magnitude and sign; positive movement
		float testAngleOne = Constants.PI / 2;
		float testAngleTwo = Constants.PI;
		float closestAngle = Tools
				.closestAngleDelta(testAngleOne, testAngleTwo);

		assertTrue(Tools.floatsEqual(Math.abs(closestAngle), Constants.PI / 2));
		assertTrue(closestAngle > 0.0f);

		// Test the angle delta magnitude and sign; negative movement
		testAngleOne = Constants.PI;
		testAngleTwo = Constants.PI / 2;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);

		assertTrue(Tools.floatsEqual(Math.abs(closestAngle), Constants.PI / 2));
		assertTrue(closestAngle < 0.0f);

		// Test the angle delta magnitude and sign where it should wrap across
		// angle 0; negative movement
		testAngleOne = Constants.TWO_PI * 0.2f;
		testAngleTwo = Constants.TWO_PI * 0.8f;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Tools.floatsEqual(Math.abs(closestAngle),
				Constants.TWO_PI * 0.4f));
		assertTrue(closestAngle < 0.0f);

		// Test angles that are not in the standard range 0 to TWO_PI
		testAngleOne = 0.0f;
		testAngleTwo = Constants.TWO_PI + Constants.PI;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Tools.floatsEqual(Math.abs(closestAngle), Constants.PI));
		assertTrue(closestAngle > 0.0f);
	}

	@Test
	public void testAngleToVector() {

		// Test an angle of 0.0f should produce a vector {1.0, 0.0}
		float testAngle = 0.0f;
		Vector2f testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, 1.0f));
		assertTrue(Tools.floatsEqual(testVector.y, 0.0f));

		// Test an angle of Pi should produce a vector {-1.0, 0.0}
		testAngle = Constants.PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, -1.0f));
		assertTrue(Tools.floatsEqual(testVector.y, 0.0f));

		// Test an angle of 1.5 Pi should produce a vector {0.0, 1.0}
		testAngle = Constants.TWO_PI * 3 / 4;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, 0.0f));
		assertTrue(Tools.floatsEqual(testVector.y, 1.0f));

		// Test an angle of Pi/2 should produce a vector {0.0, -1.0}
		testAngle = Constants.PI / 2;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, 0.0f));
		assertTrue(Tools.floatsEqual(testVector.y, -1.0f));

		// Test an angle of three Pi should produce a vector {-1.0, 0.0}
		testAngle = Constants.PI + Constants.TWO_PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, -1.0f));
		assertTrue(Tools.floatsEqual(testVector.y, 0.0f));

		// Test an angle of -pi should produce a vector {-1.0, 0.0}
		testAngle = -Constants.PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, -1.0f));
		assertTrue(Tools.floatsEqual(testVector.y, 0.0f));

		// Test an angle of Pi / 4 should produce a vector {1/sqrt(2),
		// -1/sqrt(2)}
		testAngle = Constants.PI / 4;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Tools.floatsEqual(testVector.x, (float) (1 / Math.sqrt(2))));
		assertTrue(Tools.floatsEqual(testVector.y, (float) (-1 / Math.sqrt(2))));
	}

	@Test
	public void testVectorToAngle() {

		// Test a vector of {1/sqrt(2), -1/sqrt(2)} should produce a angle of
		// PI/4
		Vector2f testVector = new Vector2f(1.0f / (float) Math.sqrt(2), -1.0f
				/ (float) Math.sqrt(2));
		float testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Tools.floatsEqual(Math.abs(testAngle), Constants.PI / 4.0f));
		assertTrue(testAngle > 0.0f);

		// Test a vector of {2.0f, 0.0f} should produce a angle of 0.0
		testVector = new Vector2f(2.0f, 0.0f);
		testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Tools.floatsEqual(Math.abs(testAngle), 0.0f));

		// Test a vector of {0.0f, 1.0f} should produce a angle of 1.5 * PI
		testVector = new Vector2f(0.0f, 1.0f);
		testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Tools.floatsEqual(Math.abs(testAngle),
				Constants.TWO_PI * 3.0f / 4.0f));
		assertTrue(testAngle > 0.0f);
	}

	@Test
	public void testVectorBetween() {

		// Test one
		Vector2f testVectorOne = new Vector2f(0.0f, 0.0f);
		Vector2f testVectorTwo = new Vector2f(5.0f, 3.0f);
		Vector2f vectorBetween = Tools.vectorBetween(testVectorOne,
				testVectorTwo);
		assertTrue(Tools.floatsEqual(vectorBetween.x, 5.0f));
		assertTrue(Tools.floatsEqual(vectorBetween.y, 3.0f));
		assertTrue(vectorBetween.x > 0.0f);
		assertTrue(vectorBetween.y > 0.0f);

		// Test two
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(-5.0f, 3.0f);
		vectorBetween = Tools.vectorBetween(testVectorOne, testVectorTwo);
		assertTrue(Tools.floatsEqual(vectorBetween.x, -5.0f));
		assertTrue(Tools.floatsEqual(vectorBetween.y, 4.0f));
		assertTrue(vectorBetween.x < 0.0f);
		assertTrue(vectorBetween.y > 0.0f);

		// Test three
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(-5.0f, 3.0f);
		vectorBetween = Tools.vectorBetween(testVectorOne, testVectorTwo);
		assertTrue(Tools.floatsEqual(vectorBetween.x, -5.0f));
		assertTrue(Tools.floatsEqual(vectorBetween.y, 4.0f));
		assertTrue(vectorBetween.x < 0.0f);
		assertTrue(vectorBetween.y > 0.0f);
	}

	@Test
	public void testDistanceBetween() {
		// Test one
		Vector2f testVectorOne = new Vector2f(3.0f, 0.0f);
		Vector2f testVectorTwo = new Vector2f(0.0f, 4.0f);
		float distanceBetween = Tools.distanceBetween(testVectorOne,
				testVectorTwo);
		assertTrue(Tools.floatsEqual(distanceBetween, 5.0f));

		// Test two
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(0.0f, 3.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(Tools.floatsEqual(distanceBetween, 4.0f));

		// Test three
		testVectorOne = new Vector2f(-1.0f, -1.0f);
		testVectorTwo = new Vector2f(1.0f, 1.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(Tools.floatsEqual(distanceBetween, (float) Math.sqrt(8)));

		// Test four
		testVectorOne = new Vector2f(10.1f, 10.0f);
		testVectorTwo = new Vector2f(10.0f, 10.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(Tools.floatsEqual(distanceBetween, (float) 0.1));
	}

	@Test
	public void testToPhysicsVector() {

		final Vector2f testVector = new Vector2f(-2.0f, 4.0f);
		final Vec2 convertedVector = Tools.toPhysicsVector(testVector);
		assertTrue(testVector.x == convertedVector.x);
		assertTrue(testVector.y == convertedVector.y);

	}

	@Test
	public void testToNormalVector() {

		final Vec2 testVector = new Vec2(-2.0f, 4.0f);
		final Vector2f convertedVector = Tools.toNormalVector(testVector);
		assertTrue(testVector.x == convertedVector.x);
		assertTrue(testVector.y == convertedVector.y);

	}

	@Test
	public void testCloneVector() {

		final Vector2f testVector = new Vector2f(-2.0f, 4.0f);
		final Vector2f clonedVector = Tools.cloneVector(testVector);
		assertTrue(testVector != clonedVector);
		assertTrue(testVector.x == clonedVector.x);
		assertTrue(testVector.y == clonedVector.y);
	}

	@Test
	public void testScreenToViewport() {

		// Test if the viewport aspect ratio is the same as the screen aspect
		// ratio
		final Vector2f testViewport = Tools.screenToViewport(
				Tools.getScreenWidth(), Tools.getScreenHeight());

		assertTrue(Tools.floatsEqual(testViewport.x / testViewport.y,
				(float) Constants.DEFAULT_SCREEN_WIDTH
						/ Constants.DEFAULT_SCREEN_HEIGHT));
	}

	@Test
	public void testViewportToScreen() {
		// Test if the viewport aspect ratio is the same as the aspect ratio of
		// the input
		final Point testScreen = Tools
				.viewportToScreen(new Vector2f(8.0f, 4.0f));

		assertTrue(Tools.floatsEqual((float) testScreen.getX()
				/ (float) testScreen.getY(), 8.0f / 4.0f));
	}

	@Test
	public void testIsVectorsEqual() {
		// Test different vectors;
		assertTrue(Tools.vectorsEqual(new Vector2f(10.0f, 10.0f), new Vector2f(
				10.0f, 10.0f)));

		assertTrue(!Tools.vectorsEqual(new Vector2f(10.1f, 10.0f),
				new Vector2f(10.0f, 10.0f)));

		assertTrue(!Tools.vectorsEqual(new Vector2f(-10.0f, -10.0f),
				new Vector2f(10.0f, 10.0f)));
	}

}

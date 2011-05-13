package utilities;

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
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClampValue() {
		// Test a value inside the clamp range
		assertTrue(Tools.clampValue(7.0f, 0.0f, 10.0f) - 7.0f <= Constants.EPSILON);

		// Test a value greater than the clamp range
		assertTrue(Tools.clampValue(15.0f, 0.0f, 10.0f) - 10.0f <= Constants.EPSILON);

		// Test a value less than the clamp range
		assertTrue(Tools.clampValue(-23.0f, 0.0f, 10.0f) - 0.0f <= Constants.EPSILON);

		// Test a value less than the negative clamp value
		assertTrue(Tools.clampValue(-23.0f, -10.0f, 10.0f) + 10.0f <= Constants.EPSILON);
	}

	@Test
	public void testWrapAngle() {

		// Test a value of 3*PI
		float testAngle = Constants.PI + Constants.TWO_PI;
		assertTrue(Math.abs(Tools.wrapAngle(testAngle) - Constants.PI) <= Constants.EPSILON);

		// Test negative angles
		testAngle = -Constants.PI;
		assertTrue(Math.abs(Tools.wrapAngle(testAngle) - Constants.PI) <= Constants.EPSILON);

		// Test that there is no change when the value is already in the correct
		// range
		testAngle = Constants.PI;
		assertTrue(Math.abs(Tools.wrapAngle(testAngle) - Constants.PI) <= Constants.EPSILON);

	}

	@Test
	public void testClosestAngleDelta() {

		// Test the angle delta magnitude and sign; positive movement
		float testAngleOne = Constants.PI / 2;
		float testAngleTwo = Constants.PI;
		float closestAngle = Tools
				.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Math.abs(Math.abs(closestAngle) - Constants.PI / 2) <= Constants.EPSILON);
		assertTrue(closestAngle > 0.0f);

		// Test the angle delta magnitude and sign; negative movement
		testAngleOne = Constants.PI;
		testAngleTwo = Constants.PI / 2;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Math.abs(Math.abs(closestAngle) - Constants.PI / 2) <= Constants.EPSILON);
		assertTrue(closestAngle < 0.0f);

		// Test the angle delta magnitude and sign where it should wrap across
		// angle 0; negative movement
		testAngleOne = Constants.TWO_PI * 0.2f;
		testAngleTwo = Constants.TWO_PI * 0.8f;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Math.abs(Math.abs(closestAngle) - Constants.TWO_PI * 0.4f) <= Constants.EPSILON);
		assertTrue(closestAngle < 0.0f);

		// Test angles that are not in the standard range 0 to TWO_PI
		testAngleOne = 0.0f;
		testAngleTwo = Constants.TWO_PI + Constants.PI;
		closestAngle = Tools.closestAngleDelta(testAngleOne, testAngleTwo);
		assertTrue(Math.abs(Math.abs(closestAngle) - Constants.PI) <= Constants.EPSILON);
		assertTrue(closestAngle > 0.0f);
	}

	@Test
	public void testAngleToVector() {

		// Test an angle of 0.0f should produce a vector {1.0, 0.0}
		float testAngle = 0.0f;
		Vector2f testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x - 1.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y - 0.0f) <= Constants.EPSILON);

		// Test an angle of Pi should produce a vector {-1.0, 0.0}
		testAngle = Constants.PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x + 1.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y - 0.0f) <= Constants.EPSILON);

		// Test an angle of 1.5 Pi should produce a vector {0.0, 1.0}
		testAngle = Constants.TWO_PI * 3 / 4;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x - 0.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y - 1.0f) <= Constants.EPSILON);

		// Test an angle of Pi/2 should produce a vector {0.0, -1.0}
		testAngle = Constants.PI / 2;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x - 0.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y + 1.0f) <= Constants.EPSILON);

		// Test an angle of three Pi should produce a vector {-1.0, 0.0}
		testAngle = Constants.PI + Constants.TWO_PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x + 1.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y - 0.0f) <= Constants.EPSILON);

		// Test an angle of -pi should produce a vector {-1.0, 0.0}
		testAngle = -Constants.PI;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x + 1.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y - 0.0f) <= Constants.EPSILON);

		// Test an angle of Pi / 4 should produce a vector {1/sqrt(2),
		// -1/sqrt(2)}
		testAngle = Constants.PI / 4;
		testVector = Tools.angleToVector(testAngle);
		assertTrue(Math.abs(testVector.x - 1 / Math.sqrt(2)) <= Constants.EPSILON);
		assertTrue(Math.abs(testVector.y + 1 / Math.sqrt(2)) <= Constants.EPSILON);
	}

	@Test
	public void testVectorToAngle() {

		// Test a vector of {1/sqrt(2), -1/sqrt(2)} should produce a angle of
		// PI/4
		Vector2f testVector = new Vector2f(1.0f / (float) Math.sqrt(2), -1.0f
				/ (float) Math.sqrt(2));
		float testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Math.abs(Math.abs(testAngle) - Constants.PI / 4.0f) <= Constants.EPSILON);
		assertTrue(testAngle > 0.0f);

		// Test a vector of {2.0f, 0.0f} should produce a angle of 0.0
		testVector = new Vector2f(2.0f, 0.0f);
		testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Math.abs(Math.abs(testAngle) - 0.0f) <= Constants.EPSILON);

		// Test a vector of {0.0f, 1.0f} should produce a angle of 1.5 * PI
		testVector = new Vector2f(0.0f, 1.0f);
		testAngle = Tools.vectorToAngle(testVector);
		assertTrue(Math.abs(Math.abs(testAngle) - Constants.TWO_PI * 3.0f
				/ 4.0f) <= Constants.EPSILON);
		assertTrue(testAngle > 0.0f);
	}

	@Test
	public void testVectorBetween() {

		// Test one
		Vector2f testVectorOne = new Vector2f(0.0f, 0.0f);
		Vector2f testVectorTwo = new Vector2f(5.0f, 3.0f);
		Vector2f vectorBetween = Tools.vectorBetween(testVectorOne,
				testVectorTwo);
		assertTrue(Math.abs(vectorBetween.x - 5.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(vectorBetween.y - 3.0f) <= Constants.EPSILON);
		assertTrue(vectorBetween.x > 0.0f);
		assertTrue(vectorBetween.y > 0.0f);

		// Test two
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(-5.0f, 3.0f);
		vectorBetween = Tools.vectorBetween(testVectorOne, testVectorTwo);
		assertTrue(Math.abs(vectorBetween.x + 5.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(vectorBetween.y - 4.0f) <= Constants.EPSILON);
		assertTrue(vectorBetween.x < 0.0f);
		assertTrue(vectorBetween.y > 0.0f);

		// Test three
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(-5.0f, 3.0f);
		vectorBetween = Tools.vectorBetween(testVectorOne, testVectorTwo);
		assertTrue(Math.abs(vectorBetween.x + 5.0f) <= Constants.EPSILON);
		assertTrue(Math.abs(vectorBetween.y - 4.0f) <= Constants.EPSILON);
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
		assertTrue(distanceBetween - 5.0f <= Constants.EPSILON);

		// Test two
		testVectorOne = new Vector2f(0.0f, -1.0f);
		testVectorTwo = new Vector2f(0.0f, 3.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(distanceBetween - 4.0f <= Constants.EPSILON);

		// Test three
		testVectorOne = new Vector2f(-1.0f, -1.0f);
		testVectorTwo = new Vector2f(1.0f, 1.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(distanceBetween - (float) Math.sqrt(8) <= Constants.EPSILON);

		// Test four
		testVectorOne = new Vector2f(10.1f, 10.0f);
		testVectorTwo = new Vector2f(10.0f, 10.0f);
		distanceBetween = Tools.distanceBetween(testVectorOne, testVectorTwo);
		assertTrue(distanceBetween - (float) 0.1 <= Constants.EPSILON);
	}

	@Test
	public void testToPhysicsVector() {

		Vector2f testVector = new Vector2f(-2.0f, 4.0f);
		Vec2 convertedVector = Tools.toPhysicsVector(testVector);
		assertTrue(testVector.x == convertedVector.x);
		assertTrue(testVector.y == convertedVector.y);

	}

	@Test
	public void testToNormalVector() {

		Vec2 testVector = new Vec2(-2.0f, 4.0f);
		Vector2f convertedVector = Tools.toNormalVector(testVector);
		assertTrue(testVector.x == convertedVector.x);
		assertTrue(testVector.y == convertedVector.y);

	}

	@Test
	public void testCloneVector() {

		Vector2f testVector = new Vector2f(-2.0f, 4.0f);
		Vector2f clonedVector = Tools.cloneVector(testVector);
		assertTrue(testVector != clonedVector);
		assertTrue(testVector.x == clonedVector.x);
		assertTrue(testVector.y == clonedVector.y);
	}

	@Test
	public void testScreenToViewport() {

		// Test if the viewport aspect ratio is the same as the screen aspect
		// ratio
		Vector2f testViewport = Tools.screenToViewport(Tools.getScreenWidth(),
				Tools.getScreenHeight());

		assertTrue(Math.abs(testViewport.x / testViewport.y
				- (float) Constants.DEFAULT_SCREEN_WIDTH
				/ Constants.DEFAULT_SCREEN_HEIGHT) <= Constants.EPSILON);
	}

	@Test
	public void testViewportToScreen() {
		// Test if the viewport aspect ratio is the same as the aspect ratio of
		// the input
		Point testScreen = Tools.viewportToScreen(new Vector2f(8.0f, 4.0f));

		assertTrue((float) testScreen.getX() / (float) testScreen.getY() - 8.0f
				/ 4.0f <= Constants.EPSILON);
	}

	@Test
	public void testIsVectorsEqual() {
		// Test different vectors;
		assertTrue(Tools.isVectorsEqual(new Vector2f(10.0f, 10.0f),
				new Vector2f(10.0f, 10.0f)));

		assertTrue(!Tools.isVectorsEqual(new Vector2f(10.1f, 10.0f),
				new Vector2f(10.0f, 10.0f)));

		assertTrue(!Tools.isVectorsEqual(new Vector2f(-10.0f, -10.0f),
				new Vector2f(10.0f, 10.0f)));
	}

}

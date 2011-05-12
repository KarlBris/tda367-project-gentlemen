package utilities;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class contains static constants used by many parts of the game
 */
public class Constants {

	public static final float PI = (float) Math.PI;
	public static final float TWO_PI = (float) (Math.PI * 2);
	public static final float TO_DEGREES = (float) (180.0 / Math.PI);

	public static final int FRAMES_PER_SECOND = 60;
	public static final float DELTA_TIME = 1.0f / FRAMES_PER_SECOND;

	public static final int DEFAULT_SCREEN_WIDTH = 960;
	public static final int DEFAULT_SCREEN_HEIGHT = 540;
	public static final float VIEWPORT_RATIO = 16.0f / 9.0f;
	public static final float VIEWPORT_WIDTH = 30.0f;
	public static final float VIEWPORT_HEIGHT = VIEWPORT_WIDTH / VIEWPORT_RATIO;

	public static final float GEOMETRY_TO_PHYSICS_INTERPOLATION = 0.8f;

	public static final float BODY_DEFAULT_DAMPING = 1.0f;
	public static final float BODY_DEFAULT_ANGULAR_DAMPING = 1.0f;

	public static final float PLAYER_MOVEMENT_ACCELERATION = 20.0f;
	public static final float PLAYER_RETICLE_DISTANCE = 1.0f;
	public static final float PLAYER_BALL_CARRYING_DISTANCE = 1.0f;
	public static final float PLAYER_KNOCKED_OUT_TIME = 3.0f;

	public static final float FLAG_PICK_UP_DISTANCE = 0.8f;

	public static final float BALL_LETHAL_SPEED = 7.0f;
	public static final float BALL_PICK_UP_DISTANCE = 1.0f;
	public static final float BALL_THROW_SPEED = 20.0f;
	public static final float BALL_SHOCKWAVE_SPEED = 5.0f;

	public static final float SHOCKWAVE_ANIMATION_TIME = 0.3f;

	public static final int NETWORK_PORT = 13781;

	public static final Color TEAM_ONE_COLOR = Color.RED;
	public static final Color TEAM_TWO_COLOR = Color.BLUE;

	public static final Vector2f TEAM_ONE_HOME_POSITION = new Vector2f(2.0f,
			10.0f);
	public static final Vector2f TEAM_TWO_HOME_POSITION = new Vector2f(28.0f,
			10.0f);

	public static final int FLAG_CAPTURE_SCORE = 15;
	public static final int FLAG_RETURN_SCORE = 4;
	public static final int KNOCK_OUT_SCORE = 1;
	public static final int SCORE_LIMIT = 100;
}

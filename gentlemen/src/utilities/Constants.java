package utilities;

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

	public static final float FLAG_PICK_UP_DISTANCE = 0.8f;

	public static final float LETHAL_BALL_SPEED = 2.0f;
	public static final float BALL_PICK_UP_DISTANCE = 1.0f;
	public static final float BALL_THROW_SPEED = 20.0f;

	public static final int NETWORK_PORT = 13781;

	public static final Color TEAM_ONE_COLOR = Color.RED;
	public static final Color TEAM_TWO_COLOR = Color.BLUE;
}

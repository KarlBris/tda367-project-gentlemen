package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import components.KeyboardComponent;

/**
 * Player represents a player in the gameworld 
 */
public class Player extends Entity {

	//private Camera camera;
	
	private Reticle reticle;
	
	public Player() {
		super(new RectangleGeometry(null, 1.0f, 1.0f));	
	}
	
	@Override
	public void start() {
		reticle = Manager.instantiate(Reticle.class);
	}
	
	@Override
	public void update() {
		
		// Update player position
		Vector2f posVect = this.getGeometry().getPosition();
		
		Vector2f newVect = new Vector2f();
		
		Vector2f.add(posVect, getKeyDirection(), newVect);
		
		// Update player facing angle
		Vector2f playerToReticle = new Vector2f();
		
		Vector2f.sub(reticle.getGeometry().getPosition(), getGeometry().getPosition(), playerToReticle);
		
		float angle = Vector2f.angle(playerToReticle, new Vector2f(1.0f, 0.0f));
		
		if (Float.isNaN(angle) || Float.isInfinite(angle)) {
			angle = 0.0f;
		}
		
		if(playerToReticle.y > 0) {
			angle = Constants.TWO_PI - angle;
		}
		
		// Push the calculated values to the geometry
		getGeometry().moveTowards(newVect, angle);
		
		// Shoot test entities just for fun!
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			TestEntity obj = Manager.instantiate(TestEntity.class);
			
			obj.setPosition(getGeometry().getPosition());
			obj.setAngle(getGeometry().getAngle());
		}
	}
	
	/**
	 * Calculate the direction of pushed down keys
	 * @return the pushed down keys direction
	 */
	private Vector2f getKeyDirection(){
		
		KeyboardComponent keyboard = Manager.getKeyboard();
		Vector2f dirVect = new Vector2f();
		
		if(keyboard.getKey(Keyboard.KEY_W)){
			dirVect.y -=1.0f;
		}
		if(keyboard.getKey(Keyboard.KEY_S)){
			dirVect.y +=1.0f;
		}
		
		if(keyboard.getKey(Keyboard.KEY_A)){
			dirVect.x -=1.0f;
		}
		if(keyboard.getKey(Keyboard.KEY_D)){
			dirVect.x +=1.0f;
		}
		
		// Normalize the direction vector to always keep the same distance 
		if (dirVect.length() > 0.0f) {
			dirVect.normalise();
		}
		
		return dirVect;
	}
	
}

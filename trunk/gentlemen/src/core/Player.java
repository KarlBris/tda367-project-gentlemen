package core;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import components.utilities.KeyboardComponent;

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
		
		Vector2f posVect = this.getGeometry().getPosition();
		
		Vector2f newVect = new Vector2f();
		
		Vector2f.add(posVect, getKeyDirection(), newVect);
		
		Vector2f playerToReticle = new Vector2f();
		
		Vector2f.sub(reticle.getGeometry().getPosition(), getGeometry().getPosition(), playerToReticle);
		
		float angle = Vector2f.angle(playerToReticle, new Vector2f(1.0f, 0.0f));
		
		if (Float.isNaN(angle) || Float.isInfinite(angle)) {
			angle = 0.0f;
		}
		
		if(playerToReticle.y > 0) {
			angle = Constants.TWO_PI - angle;
		}
		
		getGeometry().moveTowards(newVect, angle);
	}
	
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
		
		if(dirVect.length() > 0.0f) {
			dirVect.normalise();
		}
		return dirVect;
	}
	
}

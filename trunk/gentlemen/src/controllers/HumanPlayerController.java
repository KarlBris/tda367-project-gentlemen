package controllers;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import components.KeyboardComponent;

import core.Constants;
import core.IModel;
import core.Manager;
import core.PlayerModel;
import factories.HumanPlayerFactory;

/**
 * HumanPlayerController controls a PlayerModel with inputs from user interaction 
 */
public class HumanPlayerController implements IController {
	
	private PlayerModel model;
	
	//private ReticleController reticleController;
	
	public HumanPlayerController(PlayerModel model){
		this.model = model;
	}

	@Override
	public void update() {
		// Update player position
		Vector2f velocity = new Vector2f(getKeyDirection());
		
		velocity.scale(10.0f);
		
		model.move(velocity);
		
		// Update player aim direction
		model.faceTowards(getFacingDirection());
		
		// Throw ball if space is pressed
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			model.throwBall();
		}
		
		model.update();
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void start() {
		//reticleController = Manager.instantiate(new HumanPlayerFactory());
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}
	
	
	/**
	 * @return the angel player facing calculated throught the position of the player and 
	 * the mouse position
	 */
	private float getFacingDirection(){
		
		Vector2f playerToReticle = new Vector2f();
		
		// Calculate the position of the reticle relative to the player
		Vector2f.sub(	Manager.getMouse().getViewportPosition(), 
						model.getPosition(), 
						playerToReticle);
		
		// Calculate the angle from the x-axis to the playerToReticle vector
		float angle = Vector2f.angle(playerToReticle, new Vector2f(1.0f, 0.0f));
		
		// Avoid error that can be generated by the Vector2f.angle() method
		if (Float.isNaN(angle) || Float.isInfinite(angle)) {
			angle = 0.0f;
		}
		
		// To take in to account if the angle is between PI and two PI
		if(playerToReticle.y > 0) {
			angle = Constants.TWO_PI - angle;
		}
		
		return angle;
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

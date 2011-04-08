package controllers;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector2f;

import components.KeyboardComponent;

import core.Constants;
import core.IModel;
import core.Manager;
import core.PlayerModel;

/**
 * HumanPlayerController controls a PlayerModel with inputs from user interaction 
 */
public class HumanPlayerController implements IController {
	private PlayerModel playerModel;
	//private ReticleModel reticleModel;
	
	public HumanPlayerController(PlayerModel playerModel){
		this.playerModel = playerModel;
	}

	@Override
	public void update() {
		// Update player position
		playerModel.move(this.getKeyDirection());
		
		// Update player aim direction
		playerModel.faceTowards(this.getFacingDirection());
		
		// Throw ball if space is pressed
		if (Manager.getKeyboard().getKey(Keyboard.KEY_SPACE)) {
			playerModel.throwBall();
		}
		
	}

	@Override
	public IModel getModel() {
		return playerModel;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		// Instanceiate Aim

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
		Vector2f.sub(	reticleModel.getPosition(), 
						playerModel.getPosition(), 
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

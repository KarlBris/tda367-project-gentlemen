package controller.entities;

import model.common.IModel;
import model.entities.FlagModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;

import utilities.Color;

/**
 * This class controls a flag model
 */
public class FlagController implements IController {
	private final FlagModel model;

	public FlagController(final FlagModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void update() {

	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * @return the position of the flag
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * Checks with the model if it is "on the ground"
	 * 
	 * @return true if players can pick it up, false if it already is picked up
	 */
	public boolean isPickUpAble() {
		return model.isPickUpAble();
	}

	/**
	 * Sets the flag's status to picked up, making other players unable to pick
	 * it up
	 */
	public void pickUpFlag() {
		model.pickUpFlag();
	}

	/**
	 * Checks if the flag is at its home position
	 * 
	 * @return true if the flag is at its home position, false if it is anywhere
	 *         else
	 */
	public boolean isAtHome() {
		return model.isAtHome();
	}

	/**
	 * Returns the flag to its home position
	 */
	public void returnFlagHome() {
		model.returnFlagHome();
	}

	/**
	 * Releases the flag from the player and places it on the ground
	 */
	public void releaseFlag() {
		model.releaseFlag();
	}

	/**
	 * Sets the team to which the flag belongs
	 * 
	 * @param team
	 *            the team the flag will belong to
	 */
	public void setTeam(final TeamController team) {
		model.setTeam(team);

	}

	/**
	 * Returns the team owning the flag
	 * 
	 * @return the team owning the flag
	 */
	public TeamController getTeam() {
		return model.getTeam();
	}

	/**
	 * Sets the color of the flag
	 * 
	 * @param color
	 *            the desired color
	 */
	public void setColor(final Color color) {
		model.setColor(color);
	}

	/**
	 * @return the color of the flag
	 */
	public Color getColor() {
		return model.getColor();
	}

	/**
	 * Returns the home position of the team
	 * 
	 * @return the home position of the team
	 */
	public Vector2f getHomePosition() {
		return model.getHomePosition();
	}
}

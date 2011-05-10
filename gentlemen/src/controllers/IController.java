package controllers;

import models.IModel;

import org.lwjgl.util.vector.Vector2f;

/**
 * An interface which all controllers will implement
 */
public interface IController {

	/**
	 * @return the model of this controller
	 */
	public IModel getModel();

	/**
	 * Sets the position of the controller
	 * 
	 * @param position
	 *            the new position
	 */
	public void setPosition(Vector2f position);

	/**
	 * Allows the controller to perform tasks when it is instantiated
	 */
	public void start();

	/**
	 * Allows the controller to perform tasks before it is about to be deleted
	 */
	public void end();

	/**
	 * Allows the controller to perform update tasks; called once a frame
	 */
	public void update();

	/**
	 * Allows the controller to send data over the network for synchronization
	 * purposes
	 */
	public Object[] networkDataSend();

	/**
	 * Allows the controller to handle data sent over the network for
	 * synchronization purposes
	 */
	public void networkDataReceive(Object[] data);
}
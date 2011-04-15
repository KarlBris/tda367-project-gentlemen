package controllers;

import models.IModel;

/**
 * An interface which all controller will implement
 */
public interface IController {

	/**
	 * Allows the controller to perform update tasks; called once a frame
	 */
	public void update();

	/**
	 * @return the model of this controller
	 */
	public IModel getModel();

	/**
	 * Allows the controller to perform tasks when it is instantiated
	 */
	public void start();

	/**
	 * Allows the controller to perform tasks before it is about to be deleted
	 */
	public void end();

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

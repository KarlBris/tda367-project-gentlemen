package controllers;

import models.IModel;

/**
 * An interface which all controller will implement
 */
public interface IController {
	
	/**
	 * Allows the controller to update itself and and the model in which the controller
	 * is in control over
	 */
	public void update();
	
	/**
	 * @return the model in which the controller is in control over
	 */
	public IModel getModel();
	
	/**
	 * Allows the controller to preform task when it's created
	 */
	public void start();
	
	/**
	 * Allows the controller to preform task when it's about to expire
	 */
	public void end();
}

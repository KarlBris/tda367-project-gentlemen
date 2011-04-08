package controllers;

import core.IModel;

public interface IController {
	public void update();
	public IModel getModel();
	public void start();
	public void end();
}

package controller.common;

import model.common.IModel;

/**
 * An interface which all game factories will implement
 */
public interface IEntityFactory<M extends IModel, C extends IController<M>> {

	/**
	 * @return the model which was created by the factory
	 */
	public M getModel();

	/**
	 * @return the controller linked to the model created by the factory
	 */
	public C getController();

}

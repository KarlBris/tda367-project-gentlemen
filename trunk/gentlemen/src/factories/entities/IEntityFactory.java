package factories.entities;

import model.common.IModel;
import controller.common.IController;

/**
 * An interface which all factorys will implement
 */
public interface IEntityFactory {
	
	/**
	 * @return the model which was created by the factory
	 */
	public IModel getModel();
	
	/**
	 * @return the controller linked to the model created by the factory
	 */
	public IController getController();

}

package models;

import core.Geometry;


/**
 * An interface which all models will implement
 * 
 */
public interface IModel {

	/**
	 * Returns the Geometry object of the current Model.
	 * 
	 * @return the Geometry object of the current Model
	 */
	public Geometry getGeometry();
}

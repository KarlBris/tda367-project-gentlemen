package model.entities;

import core.Body;
import core.Geometry;

/**
 * An interface which all models will implement
 */
public interface IModel {

	/**
	 * @return the Geometry object of the current Model
	 */
	public Geometry getGeometry();

	/**
	 * @return the Body object of the current Model
	 */
	public Body getBody();
}
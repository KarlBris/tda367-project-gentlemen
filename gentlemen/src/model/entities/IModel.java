package model.entities;

import core.body.Body;
import core.geometry.Geometry;

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
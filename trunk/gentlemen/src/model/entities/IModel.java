package model.entities;

import model.body.Body;
import common.geometry.IGeometry;


/**
 * An interface which all models will implement
 */
public interface IModel {

	/**
	 * @return the Geometry object of the current Model
	 */
	public IGeometry getGeometry();

	/**
	 * @return the Body object of the current Model
	 */
	public Body getBody();
}
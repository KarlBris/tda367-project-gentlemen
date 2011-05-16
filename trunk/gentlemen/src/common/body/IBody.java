package common.body;

import org.jbox2d.dynamics.World;

public interface IBody {

	/**
	 * Adds the body to a physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void addToWorld(World world);

	/**
	 * Removes the body from the physics world
	 * 
	 * @param world
	 *            the world
	 */
	public void removeFromWorld(World world);

}

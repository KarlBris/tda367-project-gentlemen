package core;

/**
 * 
 * @author Gustav, Karl
 *
 */
public abstract class Entity {
	
	private Geometry geometry;
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public abstract void start();
	public abstract void end();
	public abstract void update();
	public abstract void customRender();
}

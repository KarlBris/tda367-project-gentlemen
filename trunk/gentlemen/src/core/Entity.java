package core;

/**
 * 
 * @author Gustav, Karl
 *
 */
public abstract class Entity {
	
	private Geometry geometry;
	
	public Entity(Geometry geometry) {
		this.geometry = geometry;
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public void start() {}
	public void end() {}
	public void update() {}
	public void customRender() {}
}

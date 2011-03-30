package core;

public interface Component {
	
	public void initialize();

	public void instantiatePermanentEntities();
	
	public void cleanup();
	
	public void update();
	
	public void entityAdded(Entity entity);
	
	public void entityRemoved(Entity entity);

}

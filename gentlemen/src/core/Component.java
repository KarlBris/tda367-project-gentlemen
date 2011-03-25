package core;

public interface Component {
	
	public void initialize();

	public void instantiatePermanentEntities();
	
	public void cleanup();
	
	public void update();
	
	public void entityAdded();
	
	public void entityRemoved();

}

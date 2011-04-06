package core;

import components.utilities.MouseComponent;

public class Reticle extends Entity {

	public Reticle() {
		super(new ReticleGeometry());
				
	}
	
	@Override
	public void update() {
		MouseComponent mouse = Manager.getMouse();
		
		getGeometry().setPosition(mouse.getViewportPosition());
		
		
	}
	
}

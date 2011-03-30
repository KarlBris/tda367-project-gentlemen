package components.views;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import core.Component;
import core.Entity;
import core.Manager;

public class RenderComponent implements Component {

	@Override
	public void initialize() {
		
		// Initialize the projection and matrix modes
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);	// TODO Replace 800 and 600 with suitable units
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}

	@Override
	public void instantiatePermanentEntities() {
		// TODO Auto-generated method stub
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		
		List<Entity> entityList = Manager.getEntities();
		
		// Clear the color buffer and depth buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		// Begin rendering triangles
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// Let each entity render itself
		for(Entity e : entityList) {
			
			if(e.getGeometry()!=null) {
				e.getGeometry().render();
			}
			
		}
		
		// Stop rendering triangles
		GL11.glEnd();
	}

	@Override
	public void entityAdded(Entity entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public void entityRemoved(Entity entity) {
		// TODO Auto-generated method stub
	}

}
package components.views;

import java.util.List;
import org.lwjgl.opengl.GL11;
import core.Component;
import core.Entity;
import core.Manager;

public class RenderComponent implements Component {

	@Override
	public void initialize() {
		
		// The width and height of the viewport in game units
		float width;
		float height;

		
		// Initializes the display window size
		int displayHeight = core.Constants.getScreenWidth();
		int displayWidth = core.Constants.getScreenHeight();
		
		// Calculate the aspect ratio of the display window
		float displayRatio = (float) displayWidth / (float) displayHeight;
		
		
		// If the screen is slimmer than the standard ratio (16:9), make a new, smaller, width
		// but keep the height
		if(displayRatio < core.Constants.VIEWPORT_RATIO) {
			width = (core.Constants.VIEWPORT_HEIGHT / displayHeight) * displayWidth;
			height = core.Constants.VIEWPORT_HEIGHT;
		}
		// If the screen is wider than or equally wide to the standard ratio, keep the height
		// and make a new, smaller, width
		else {
			width = core.Constants.VIEWPORT_WIDTH;
			height = (core.Constants.VIEWPORT_WIDTH / displayWidth) * displayHeight;
		}
		
		// Initialize the projection and matrix modes
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);	// TODO Replace 800 and 600 with suitable units
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

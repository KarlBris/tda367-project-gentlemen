package common.body;

import org.jbox2d.dynamics.World;
import org.lwjgl.util.vector.Vector;

public class NullBody implements IBody {

	@Override
	public void addToWorld(final World world) {
		// Do nothing
	}

	@Override
	public void removeFromWorld(final World world) {
		// Do nothing
	}

	@Override
	public Vector getVelocity() {
		// TODO Auto-generated method stub
		return null;
	}

}

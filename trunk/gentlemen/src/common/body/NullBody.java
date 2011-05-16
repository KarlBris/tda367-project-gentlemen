package common.body;

import org.jbox2d.dynamics.World;

public class NullBody implements IBody {

	@Override
	public void addToWorld(final World world) {
		// Do nothing
	}

	@Override
	public void removeFromWorld(final World world) {
		// Do nothing
	}

}

package model.entities.gamemode;

import model.common.IModel;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

/**
 * Represents a visual scoreboard in the game. Teams are added to the list and
 * their score is read every update() and printed on the window
 * 
 */
public final class ScoreboardModel implements IModel {

	private final IGeometry geometry = new NullGeometry();
	private final IBody body = new NullBody();

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public IBody getBody() {
		return body;
	}

	/**
	 * Sets the scoreboard text
	 * 
	 * @param scoreString
	 *            the text to be set on the scoreboard
	 */
	public void setText(final String scoreString) {
		org.lwjgl.opengl.Display.setTitle(scoreString);

	}

}

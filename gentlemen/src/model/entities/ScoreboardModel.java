package model.entities;

import model.common.IModel;

import common.body.Body;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

/**
 * Represents a visual scoreboard in the game. Teams are added to the list and
 * their score is read every update() and printed on the window
 * 
 */
public class ScoreboardModel implements IModel {

	private final IGeometry geometry = new NullGeometry();

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
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

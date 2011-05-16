package model.entities;

import java.util.LinkedList;
import java.util.List;

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

	private final List<TeamModel> teamList = new LinkedList<TeamModel>();

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
	 * Updates the scoreboard text according to the teams' scores
	 */
	public void update() {

		String scoreString = "";

		for (final TeamModel t : teamList) {
			scoreString = scoreString + t.getTeamName() + ": " + t.getScore()
					+ " ";
		}

		setText(scoreString);
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

	public void addTeam(final TeamModel model) {
		teamList.add(model);

	}

}

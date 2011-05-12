package model.entities;

import java.util.LinkedList;
import java.util.List;

import model.body.Body;

import common.geometry.IGeometry;
import common.geometry.NullGeometry;

import controller.entities.TeamController;

/**
 * Represents a visual scoreboard in the game. Teams are added to the list and
 * their score is read every update() and printed on the window
 * 
 */
public class ScoreboardModel implements IModel {

	private final IGeometry geometry = new NullGeometry();

	private final List<TeamController> teamList = new LinkedList<TeamController>();

	/**
	 * update() is called every frame and updates the visual scoreboard in the
	 * window frame
	 */
	public void update() {
		String scoreString = "";
		for (final TeamController team : teamList) {
			scoreString = scoreString + team.getTeamName() + ": "
					+ team.getScore() + " ";
		}

		org.lwjgl.opengl.Display.setTitle(scoreString);
	}

	/**
	 * Adds a team to the scoreboard. This allows the team have its score
	 * printed on the screen
	 * 
	 * @param team
	 *            the TeamController of a given team
	 */
	public void addTeam(final TeamController team) {
		teamList.add(team);
	}

	public List<TeamController> getTeamList() {
		return teamList;
	}

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}

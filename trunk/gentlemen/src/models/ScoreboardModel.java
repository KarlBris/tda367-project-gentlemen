package models;

import java.util.LinkedList;
import java.util.List;

import controllers.TeamController;
import core.Body;
import core.Geometry;
import core.NullGeometry;

public class ScoreboardModel implements IModel {

	private final Geometry geometry = new NullGeometry();

	private final List<TeamController> teamList = new LinkedList<TeamController>();

	public void update() {
		String scoreString = "";
		for (final TeamController team : teamList) {
			scoreString = scoreString + team + ": " + team.getScore() + " ";
		}

		org.lwjgl.opengl.Display.setTitle(scoreString);
	}

	public void addTeam(final TeamController team) {
		teamList.add(team);
	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

}

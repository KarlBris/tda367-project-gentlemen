package model.entities.gamemode;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;
import utilities.Tools;

import common.body.IBody;
import common.body.NullBody;
import common.geometry.IGeometry;
import common.geometry.NullGeometry;

/**
 * Represents a team in the game
 */
public final class TeamModel implements IModel {

	private Vector2f homePosition;

	private String teamName;

	private final IGeometry geometry = new NullGeometry();
	private final IBody body = new NullBody();

	private int totalScore = 0;

	private Color color = Color.BLACK;

	/**
	 * Add an amount to the team score
	 * 
	 * @param amount
	 *            is the amount to be added to the team score
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void addScore(final int amount) throws NumberFormatException {
		if (amount < 0) {
			throw new NumberFormatException("Number must be positive");
		} else {
			totalScore += amount;
		}

	}

	/**
	 * Subtracts an amount from the team score
	 * 
	 * @param amount
	 *            the amount to be subtracted from the team score
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void subtractScore(final int amount) throws NumberFormatException {
		if (amount < 0) {
			throw new NumberFormatException("Number must be positive");
		} else {

			totalScore -= amount;

		}
	}

	@Override
	public IGeometry getGeometry() {
		return geometry;
	}

	@Override
	public IBody getBody() {
		return body;
	}

	/**
	 * @return the team score
	 */
	public int getScore() {
		return totalScore;
	}

	/**
	 * Sets the team name
	 * 
	 * @param name
	 *            the name the team will be given
	 */
	public void setTeamName(final String name) {
		teamName = name;
	}

	/**
	 * Gets the team name
	 * 
	 * @return the name of the team
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Sets the home position of the team. This will be used by players and
	 * flags alike
	 * 
	 * @param position
	 *            the home position of the team
	 */
	public void setHomePosition(final Vector2f position) {
		homePosition = position;
	}

	/**
	 * @return the home position of the team
	 */
	public Vector2f getHomePosition() {
		return Tools.cloneVector(homePosition);
	}

	/**
	 * Add an amount to the team score, without looking if someone won. Main
	 * purpuse of this method is to use if for testing.
	 * 
	 * @param amount
	 *            is the amount to be added to the team score
	 * 
	 * @throws NumberFormatException
	 *             is thrown if amount is negative
	 */
	public void addPoints(final int amount) throws NumberFormatException {
		if (amount < 0) {
			throw new NumberFormatException("Number must be positive");
		} else {

			totalScore += amount;

		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

}

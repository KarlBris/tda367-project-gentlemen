package controllers;

import models.FlagModel;
import models.IModel;

import org.lwjgl.util.vector.Vector2f;

import utilities.Color;

public class FlagController implements IController {
	private final FlagModel model;

	public FlagController(final FlagModel model) {
		this.model = model;
	}

	@Override
	public IModel getModel() {
		return model;
	}

	@Override
	public void update() {

	}

	@Override
	public void setPosition(final Vector2f position) {
		model.setPosition(position);
	}

	/**
	 * @return the position of the flag
	 */
	public Vector2f getPosition() {
		return model.getPosition();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

	public boolean isPickUpAble() {
		return model.isPickUpAble();
	}

	public void pickUpFlag() {
		model.pickUpFlag();
	}

	public boolean isAtHome() {
		return model.isAtHome();
	}

	public void returnFlagHome() {
		model.returnFlagHome();

	}

	public void releaseFlag() {
		model.releaseFlag();
	}

	public void setTeam(final TeamController team) {
		model.setTeam(team);

	}

	public TeamController getTeam() {
		return model.getTeam();
	}

	public void setColor(final Color color) {
		model.setColor(color);
	}

	public Vector2f getHomePosition() {
		return model.getHomePosition();
	}
}

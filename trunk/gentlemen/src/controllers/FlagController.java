package controllers;

import models.FlagModel;
import models.IModel;

import org.lwjgl.util.vector.Vector2f;

public class FlagController implements IController {
	private final FlagModel model;

	public FlagController(FlagModel model) {
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
	public void setPosition(Vector2f position) {
		model.setPosition(position);
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
	public void networkDataReceive(Object[] data) {
		// TODO Auto-generated method stub

	}

	public boolean isPickUpAble() {
		return model.isPickUpAble();
	}

	public void pickUpFlag() {
		model.pickUpFlag();
	}

	public int getFlagTeamIndex() {
		return model.getTeamIndex();
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

}

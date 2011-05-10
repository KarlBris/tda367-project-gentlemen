package controllers;

import models.IModel;
import models.RuleModel;
import models.TeamModel;

import org.lwjgl.util.vector.Vector2f;

/**
 * This class controls a rule model
 */
public class RuleController implements IController {

	private final RuleModel model;

	public RuleController(final RuleModel model) {
		this.model = model;
	}

	/**
	 * Checks if a given team has won based on its total score
	 * 
	 * @param team
	 *            the team to check for victory
	 */
	public void checkVictory(final TeamModel team) {
		model.checkVictory(team);
	}

	/**
	 * @see controllers.IController#getModel()
	 */
	@Override
	public IModel getModel() {
		return model;
	}

	/**
	 * @see controllers.IController#setPosition(org.lwjgl.util.vector.Vector2f)
	 */
	@Override
	public void setPosition(final Vector2f position) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#start()
	 */
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#end()
	 */
	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#update()
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	/**
	 * @see controllers.IController#networkDataSend()
	 */
	@Override
	public Object[] networkDataSend() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see controllers.IController#networkDataReceive(java.lang.Object[])
	 */
	@Override
	public void networkDataReceive(final Object[] data) {
		// TODO Auto-generated method stub

	}

}

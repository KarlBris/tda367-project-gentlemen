package controller;

import java.util.List;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import factories.entities.IEntityFactory;

public interface IMainController {

	public <M extends IModel, C extends IController<M>, T extends IEntityFactory<M, C>> C instantiate(
			IEntityFactory<M, C> factory, Vector2f position);

	public <M extends IModel> void remove(IController<M> controller);

	public <M extends IModel, C extends IController<M>> List<C> find(
			Class<C> type);

	public List<IController<? extends IModel>> getControllers();

	public KeyboardComponent getKeyboardComponent();

	public MouseComponent getMouseComponent();

	public void initialize();

	public void update();
}

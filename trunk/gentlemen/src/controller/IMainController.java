package controller;

import java.util.List;

import model.common.IModel;

import org.lwjgl.util.vector.Vector2f;

import controller.common.IController;
import controller.components.KeyboardComponent;
import controller.components.MouseComponent;
import factories.entities.IEntityFactory;

public interface IMainController {

	public <M extends IModel, C extends IController, T extends IEntityFactory<M, C>> C instantiate(
			IEntityFactory<M, C> factory, Vector2f position);

	public void remove(IController controller);

	public <T extends IController> List<T> find(Class<T> type);

	public List<IController> getControllers();

	public KeyboardComponent getKeyboardComponent();

	public MouseComponent getMouseComponent();

	public void initialize();

	public void update();
}

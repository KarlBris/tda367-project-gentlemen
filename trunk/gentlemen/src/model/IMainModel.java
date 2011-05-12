package model;

import java.util.List;

import model.entities.IModel;

public interface IMainModel {

	public void add(IModel model);

	public void remove(IModel model);

	public List<IModel> getModels();

}

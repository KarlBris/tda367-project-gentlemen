package model;

import java.util.List;

import models.IModel;

public interface IMainModel {

	public void add(IModel model);

	public void remove(IModel model);

	public List<IModel> getModels();

}

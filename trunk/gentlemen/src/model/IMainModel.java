package model;

import java.util.Collection;

import model.common.IModel;

public interface IMainModel {

	public void add(IModel model);

	public void remove(IModel model);

	public Collection<IModel> getModels();

}

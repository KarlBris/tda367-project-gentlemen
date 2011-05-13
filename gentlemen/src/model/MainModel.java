package model;

import java.util.List;

import common.TypeMap;

import model.common.IModel;

public class MainModel implements IMainModel {

	private TypeMap<IModel> models = new TypeMap<IModel>();

	@Override
	public void add(IModel model) {
		models.add(model);
	}

	@Override
	public void remove(IModel model) {
		models.remove(model);
	}

	@Override
	public List<IModel> getModels() {
		return models.getItems();
	}

}

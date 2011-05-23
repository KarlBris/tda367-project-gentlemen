package model;

import java.util.Collection;

import model.common.IModel;

import common.TypeMap;

public class MainModel implements IMainModel {

	private static MainModel instance;

	private TypeMap<IModel> modelMap = new TypeMap<IModel>();

	private MainModel() {
		// Do nothing; exists for setting the constructor to private
	}

	public static MainModel get() {
		if (instance == null) {
			instance = new MainModel();
		}

		return instance;
	}

	@Override
	public void add(final IModel model) {
		modelMap.add(model);
	}

	@Override
	public void remove(final IModel model) {
		modelMap.remove(model);
	}

	@Override
	public void removeAll() {
		Collection<IModel> models = modelMap.getItems();

		for (IModel model : models) {
			remove(model);
		}
	}

	@Override
	public Collection<IModel> getModels() {
		return modelMap.getItems();
	}

}

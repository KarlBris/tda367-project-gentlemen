package model;

import java.util.Collection;

import model.common.IModel;

import common.TypeMap;

public class MainModel implements IMainModel {

	private static MainModel instance;

	private TypeMap<IModel> models = new TypeMap<IModel>();

	private MainModel() {
		// Do nothing
	}

	public static MainModel get() {
		if (instance == null) {
			instance = new MainModel();
		}

		return instance;
	}

	@Override
	public void add(final IModel model) {
		models.add(model);
	}

	@Override
	public void remove(final IModel model) {
		models.remove(model);
	}

	@Override
	public Collection<IModel> getModels() {
		return models.getItems();
	}

}

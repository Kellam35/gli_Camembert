package controller;

import model.IModel;
import model.Item;
import view.IView;

import java.util.List;

public class Controller implements IModel {

	private IModel model;
	private IView view;

	public Controller(IModel model, IView view) {
		this.model = model;
		this.view = view;
		view.setController(this);

	}

	@Override
	public void setName(String name) {
		model.setName(name);
		view.notifyChange();
	}

	@Override
	public void setItems(List<Item> items) {
		model.setItems(items);
		view.notifyChange();
	}

	@Override
	public String getName() {
		return model.getName();
	}

	@Override
	public List<Item> getItems() {
		return model.getItems();
	}

	@Override
	public void addItem(Item item) {
		model.addItem(item);
		view.notifyChange();
	}

	@Override
	public void removeItem(Item item) {
		model.removeItem(item);
		view.notifyChange();
	}

	@Override
	public int getTotal() {
		return model.getTotal();
	}

	public void onClickEvent( ){
		view.notifyChange();
	}
}

package controller;

import model.IModel;
import model.Item;
import view.IView;

import java.util.ArrayList;
import java.util.List;

public class Controller implements IModel {

	private IModel model;
	private List<IView> views;

	public Controller(IModel model) {
		this.model = model;
		this.views = new ArrayList<>();
	}

	@Override
	public String getName() {
		return model.getName();
	}

	@Override
	public void setName(String name) {
		model.setName(name);
		for (IView view : views) {
			view.notifyChange();
		}
	}

	@Override
	public List<Item> getItems() {
		return model.getItems();
	}

	@Override
	public void setItems(List<Item> items) {
		model.setItems(items);
		for (IView view : views) {
			view.notifyChange();
		}
	}

	@Override
	public void addItem(Item item) {
		model.addItem(item);
		for (IView view : views) {
			view.notifyChange();
		}
	}

	@Override
	public void removeItem(int i) {
		model.removeItem(i);
		for (IView view : views) {
			view.notifyChange();
		}
	}

	@Override
	public int getTotal() {
		return model.getTotal();
	}

	public void onClickEvent( int id){
		for (IView view : views) {
			view.notifyItemSelected(id);
		}
	}
	public void addView(IView view ){
		views.add(view);
		view.setController(this);
	}

	public void update(){
		for (IView view : views) {
			view.notifyChange();
		}
	}

	public void setSelected(int id) {
		for (IView view: views){
			view.notifyItemSelected(id);
		}
	}
}

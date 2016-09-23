package model;

import java.util.ArrayList;
import java.util.List;


public class Model implements IModel{


	private String name;
	private List<Item> items;

	public Model(String name) {
		this.name = name;
		this.items = new ArrayList<>();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Item> getItems() {
		return items;
	}

	@Override
	public void addItem(Item item) {
		items.add(item);
	}

	@Override
	public void removeItem(int i) {
		items.remove(i);
	}

	@Override
	public int getTotal() {
		int total=0;
		for (Item item: getItems()){
			total+=item.getValue();
		}
		return total;
	}
}

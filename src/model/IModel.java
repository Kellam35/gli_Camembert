package model;

import java.util.List;

/**
 * Created by florian on 16/09/16.
 */
public interface IModel {

	void setName(String name);

	void setItems(List<Item> items);

	String getName();

	List<Item> getItems();

	void addItem(Item item);

	void removeItem(int i);

	int getTotal();

}

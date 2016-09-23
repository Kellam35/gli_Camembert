package model;

import controller.Controller;

import javax.swing.table.AbstractTableModel;

public class TableAdapter extends AbstractTableModel{


	private IModel model;
	private Controller controller;
	private String titre[] = {"Name","Amount","Description"};

	public TableAdapter(IModel model) {
		this.model = model;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}


	@Override
	public int getRowCount() {
		return model.getItems().size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		switch (column){
			case 0 : return model.getItems().get(row).getName();
			case 1 : return model.getItems().get(row).getValue();
			case 2 : return model.getItems().get(row).getDescription();
			default : return null;}
	}

	@Override
	public void setValueAt(Object o, int row, int column) {
		switch (column) {
			case 0:
				model.getItems().get(row).setName(o.toString());break;
			case 1:
				model.getItems().get(row).setValue(Integer.parseInt(o.toString()));break;
			case 2:
				model.getItems().get(row).setDescription(o.toString());break;
		}
		controller.update();
	}

	@Override
	public String getColumnName(int i) {
		return titre[i];
	}

	@Override
	public boolean isCellEditable(int i, int i1) {
		return true;
	}
}

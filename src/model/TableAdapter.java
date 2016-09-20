package model;

import javax.swing.table.AbstractTableModel;

public class TableAdapter extends AbstractTableModel{

	private IModel model;

	public TableAdapter(IModel model) {
		this.model = model;
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
		if(column==1){
			return model.getItems().get(row).getName();
		}
		else if (column==2)
		{
			return model.getItems().get(row).getValue();
		}else if (column==3)
		{
			return model.getItems().get(row).getDescription();
		}else {return null;}
	}


}

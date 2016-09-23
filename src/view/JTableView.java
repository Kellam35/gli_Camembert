package view;

import controller.Controller;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class JTableView extends JTable implements IView {

	private int selected=-1;
	private AbstractTableModel tableModel;


	public JTableView(TableModel tableModel) {
		super(tableModel);
		this.tableModel = (AbstractTableModel) tableModel;
	}

	@Override
	public void notifyChange() {
		tableModel.fireTableDataChanged();
	}

	@Override
	public void setController(Controller controller) {
	}

	@Override
	public void notifyItemSelected(int id) {
		if(selected!= id) {
			selected = id;
			if (selected != -1) {
				setRowSelectionInterval(id, id);
			} else {
				clearSelection();
			}
		}
	}
}

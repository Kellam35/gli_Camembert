package main;

import controller.Controller;
import model.IModel;
import model.Item;
import model.Model;
import model.TableAdapter;
import view.Camembert;
import view.JTableView;

import javax.swing.*;
import java.awt.*;

public class Main {

	public static void main (String[] args){

		IModel model = new Model("Budget");
		Camembert view= new Camembert(model,"Budget");
		TableAdapter tableAdapter = new TableAdapter(model);
		JTableView table = new JTableView(tableAdapter);
		Controller controller = new Controller(model);
		tableAdapter.setController(controller);
		controller.addView(view);
		controller.addView(table);
		controller.addItem(new Item("test1","c'est l'item1",42));
		controller.addItem(new Item("test2","The Game",52));
		controller.addItem(new Item("test3","c'est l'item3",62));
		controller.addItem(new Item("test4","42",30));
		controller.addItem(new Item("test5","Moi c'est Bobby",34));
		controller.addItem(new Item("test6","LA RUSSIE",90));
		controller.addItem(new Item("test7","c'est l'item7",3));
		controller.addItem(new Item("test8","description8",17));
		controller.addItem(new Item("test9","description9",39));
		controller.addItem(new Item("test10","description10",20));

		//1. Create the frame.
		JFrame frame = new JFrame("Camembert");

		JButton add = new JButton("add");
		JButton remove = new JButton("remove");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JScrollPane scrollPane = new JScrollPane(table);
		JPanel panelTable = new JPanel();
		panelTable.add(scrollPane);
		panelTable.add(add);
		panelTable.add(remove);

		add.addActionListener(e -> controller.addItem(new Item("name","description",1)));

		remove.addActionListener(e -> {
			int[] selected = (table.getSelectedRows());
			for (int i = 0; i<selected.length;i++) {
				controller.removeItem(selected[0]);
			}
			controller.setSelected(-1);
		});

		table.getSelectionModel().addListSelectionListener(event -> {
			int select = table.getSelectedRow();
			if (select > -1) {
				controller.setSelected(select);
			}
		});

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,view,panelTable);
		splitpane.setDividerLocation(750);
		view.setMinimumSize(new Dimension(750,600));
		panelTable.setMinimumSize(new Dimension(470,600));
		view.addMouseListener(view);

		frame.getContentPane().add(splitpane, BorderLayout.CENTER);

		frame.setMinimumSize(new Dimension(1220,600));
		frame.pack();

		frame.setVisible(true);
	}
}

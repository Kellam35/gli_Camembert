package main;

import controller.Controller;
import model.IModel;
import model.Item;
import model.Model;
import model.TableAdapter;
import view.Camembert;
import view.IView;
import view.JTableView;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class Main {

	public static void main (String[] args){

		IModel model = new Model("Budget");
		Camembert view= new Camembert(model,"Budget");
		IView table = new TableAdapter(model);
		Controller controller = new Controller(model);
		controller.addView(view);
		controller.addView(table);
		controller.addItem(new Item("test1","description1",42));
		controller.addItem(new Item("test2","description2",52));
		controller.addItem(new Item("test3","description3",62));
		controller.addItem(new Item("test4","description4",30));
		controller.addItem(new Item("test5","description5",34));
		controller.addItem(new Item("test6","description6",90));
		controller.addItem(new Item("test7","description7",3));
		controller.addItem(new Item("test8","description8",17));
		controller.addItem(new Item("test9","description9",39));
		controller.addItem(new Item("test10","description10",20));

		//1. Create the frame.
		JFrame frame = new JFrame("Camembert");

	//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//3. Create components and put them in the frame.
	//...create emptyLabel...

	//	JScrollPane scrollPane = new JScrollPane((JTable)table);
	//	JPanel panelTable = new JPanel();
	//	panelTable.add(scrollPane);
		JTable jTable= new JTable((TableModel) table);
		JScrollPane scrollPane = new JScrollPane(jTable);
		JPanel panelTable = new JPanel();
		panelTable.add(scrollPane);

		JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,view,panelTable);
		splitpane.setDividerLocation(750);
		view.setMinimumSize(new Dimension(750,600));
		panelTable.setMinimumSize(new Dimension(470,600));
		view.addMouseListener(view);

		frame.getContentPane().add(splitpane, BorderLayout.CENTER);

	//4. Size the frame.
		frame.setMinimumSize(new Dimension(1220,600));
		frame.pack();

	//5. Show it.
		frame.setVisible(true);
	}
}

package view;

import controller.Controller;
import model.IModel;

import javax.swing.*;

public class JTableView extends JTable implements  IView{

	private IModel model;
	private Controller controller;

	public JTableView(IModel model) {
		this.model = model;
	}

	@Override
	public void notifyChange() {

	}

	@Override
	public void setController(Controller controller) {
		this.controller=controller;
	}
}

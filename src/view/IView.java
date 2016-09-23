package view;

import controller.Controller;

public interface IView {

	void notifyChange();

	void setController(Controller controller);

	void notifyItemSelected(int id);
}

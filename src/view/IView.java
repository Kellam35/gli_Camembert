package view;

import controller.Controller;

/**
 * Created by florian on 16/09/16.
 */
public interface IView {

	void notifyChange();

	void setController(Controller controller);
}

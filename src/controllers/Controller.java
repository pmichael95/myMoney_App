package controllers;

import observer.Observer;

/** 
 * The controller interface in the MVC pattern.
 * 
 * All Controllers should implement this interface.
 * The Controller/view uses an observer pattern.
 * The Controller is the observer, it expects its dependent subject/view 
 * to notify the controller to update its model
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @updated 1/29/2018
 */
public interface Controller extends Observer {
	
	/**
	 * Tells the controller's attached view to update its ui.
	 * 
	 * The programmer must manually call this method. This method updates the attached view.
	 * For example lets says we just handled an event in the controller, the model  might have changed.
	 * In order for the view to reflect these changes, you must call this method.
	 * This method is called after any model changes have happened, and the view needs to reflect these changes
	 * 
	 * @see controllers.ExampleController#updateView()
	 */
	void updateView();
	
	/**
	 * Initialize view code and logic 
	 * example, load specific view with certain parameters
	 * 
	 * @see controllers.ExampleController#initView()
	 */
	void initView();
	
	/**
	 * Initialize any model or models that the controller needs to update or create
	 * This method can load a specific model from the database
	 * example, load model with id = 4, or any other specific query
	 * 
	 * @see controllers.ExampleController#initModel()
	 */
	void initModel();
	
	/**
	 * Update the controller/observer data/state
	 * This is automatically called by the view/subject when any view event is triggered
	 * update is called when the view calls notifyObservers(Object data).
	 * In this method you might update the model with the new changes from the view
	 * At the end of this method, it is best to call updateView() in order to reflect the model changes
	 * performed in this method
	 * 
	 * @see controllers.ExampleController#update(Object data)
	 * 
	 * @param Object data, this is the corresponding data container passed from the view's notifyObserver(Object data) call
	 * the object is usually going to be type casted to the specified data type depending on the what the view's data is.
	 * the data reflects the state of the view 
	 * @see views.ExampleView.ExampleViewData as an example
	 */
	@Override
	void update(Object data);
	
	void updateModel(Object data);
}

package views;

import observer.Subject;

/**
 * The view is the basic ui. 
 * The ui can be console ui, or GUI similar to java swing
 * 
 * All views should extend this class
 * 
 * 
 * 
 * @author Steven Tucci
 * @created 1/29/2018
 * @updated 1/29/2018
 */
public abstract class View extends Subject {
	
	/**
	 * Interface method that all sub classes should implement
	 * 
	 * @see views.ExampleView#updateUI(Object)
	 * @param data, the data object that the view wants to send to the controller
	 */
	public abstract void updateUI(Object data);
}

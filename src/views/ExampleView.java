package views;

import java.util.Scanner;

import javafx.event.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.application.*;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * ExampleView shows a very simple way of how a view is made
 * The view normally maps to one corresponding controller. In this case it maps to the ExampleController.
 * 
 * A new view should extend the View class, and implement any of the methods like updateUI(Object data)
 * The example view provides a simple keyboard interface, but eventually we should start using swing GUI components or which ever GUI library we choose
 * 
 * In the view Constructor, you can initialize any ui components, event listeners, or small ui logic
 * 
 * The view should not know about the model in any way, any data changes should notify the controller of the changes. 
 * To do this call the notifyObservers(data) to pass the data around
 * Since we are doing message passing between the view and controller and dont want to pass around the model,
 * each view should declare a data pojo/struct/container
 * @see ExampleViewData
 * 
 * @author Steven Tucci, Philip Michael
 * @created 1/29/2018
 * @updated 3/5/2018
 * 
 */
public class ExampleView extends View {
	private Scanner keyboard;

	
	/**
	 * In order to pass messages back to the controller, we need a some sort of data container
	 * Since we don't want to pass around models, we'll have a container that holds any data that the view needs
	 * For example lets say a sign up form has name, email, date of birth, then the object would hold 3 fields,
	 * when the use hits submit, we'll create the container with the new data, call notifyObserver(data) 
	 * and the corresponding controller would handle it
	 * 
	 * Though it doens't need to be an inner class, is is better to lump together the view and the view state into one file,
	 * instead of having multiple files
	 * 
	 * To make it accessible to outer classes, make the inner class static
	 */
	public static class ExampleViewData {
		// While the data fields don't have to be public, they are easier to read/write if they are
		// You can have private fields with getter/setters, but that makes the message passing very ugly, cumbersome, and clunky
		// Think of these classes as structs in c/c++
		// Since we are creating new objects every time we pass a message, we don't really need to encapsulate anything
		// The passed object is never used again
		
		// A simple data field
		public int aField;
		// ... Any data fields you want to pass to the controller
		// These could be primitives,strings, or other objects
	}
	
	/**
	 * Initialize any view logic or necessary components for this view to work. such as buttons, event listeners
	 */
	public ExampleView() {
		keyboard = new Scanner(System.in);
	}
	
	/*
	 * Your code may not have this method, but this is an example of a simple ui element event happening.
	 * For example, lets say your ui has some textboxes and button, when the user presses the button you might want to do something
	 * Then, your button should implement some sort of event listener. When the event is fired you may want to take all the inputs and send it to the controller.
	 * In order to do that then perform something similar to what this example code is doing below
	 */
	private void OnEvent(int i) {
		// Create a new data container holding state about our view currently
		ExampleViewData data = new ExampleViewData();
		// get the input data from the textfields or anything other ui data
		// and add it to the data container
		data.aField = i;
		// once the data container has all the necessary data for the controller to handle
		// notify the observer of the data change
		notifyObservers(data);
	}
	

	public void causeEvent() {
		// You wouldn't have this in your implementation
		// this was just here to cause an event to trigger. the cause of an event might be a button press, or ui event
		System.out.println("enter an integer");
		int myint = keyboard.nextInt();
		// Fire an event
		OnEvent(myint);
	}

	/**
	 * Update this view with the new data 
	 * 
	 * @param data, the new updated data passed from the controller
	 * @see views.View#updateUI(java.lang.Object)
	 */
	@Override
	public void updateUI(Object data) {
		// Cast the data to our example view data container
		ExampleViewData viewData = (ExampleViewData) data;
		// Extract all the data out of the container and update the ui with new data
		// In the case of this example, since we are using the console, we are just printing out the new data
		System.out.println("Test " + viewData.aField);
		
	}
}

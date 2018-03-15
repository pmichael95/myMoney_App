package views;

import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import views.ExampleView.ExampleViewData;
import GUI.LandingGUI;

/**
 * 
 * The DepositMoney view for when we deposit into the deposit_money table.
 * 
 * @author Sabrina D'Mello
 * @modifiedBy Johnny Mak, Philip Michael
 * @created 2/1/2018
 * @updated 3/12/2018
 *
 */
public class DepositMoneyView extends View {

	private Scanner keyboard; //for the scanner
	
	//Nested Data Class	
	public static class DepositMoneyViewData 
	{
		public float amount;
		public String type;
		public String account;
	}
	
	//Constructor creating a scanner for the users input
	public DepositMoneyView()
	{
		// keyboard = new Scanner(System.in);
	}
	
	//Method will allow the user to enter the amount will to deposit
	public void DepositEvent()
	{
		/* System.out.println("\nHow much money would you like to deposit in this account?");
		float user_amount = keyboard.nextFloat();
		
		System.out.println("\nWhat is the type of deposit?");
		String user_type = keyboard.next(); */
		
		//ToDo how does DepositEvent get data
		
		// onDepositEvent(user_amount, user_type); 
		
	}
	
	//Will notify the observer on amount change
	public void onDepositEvent(float amt, String tp)
	{
		DepositMoneyViewData dataView = new DepositMoneyViewData();
		dataView.amount = amt;
		dataView.type   = tp;
		notifyObservers(dataView);
	}
	
	
	/**
	 * Update this view with the new data 
	 */
	@Override
	public void updateUI(Object data) {
		// Cast the data to our example view data container
		// DepositMoneyViewData viewData = (DepositMoneyViewData) data;
		// Extract all the data out of the container and update the ui with new data
		// In the case of this example, since we are using the console, we are just printing out the new data
		// System.out.println("Test Deposit: " + viewData.amount + ", Test Type: " + viewData.type);
		
	}

}
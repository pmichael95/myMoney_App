package views;

import java.util.Scanner;

import views.ExampleView.ExampleViewData;

/**
 * 
 * Following the skeleton code created by Steven, I have created the DepositMoneyView.
 * This class will take the input of the user by prompting him through the console to write the 
 * amount of money that will be deposited.
 * 
 * Added an extra field essential to our Database 
 * 
 * @author Sabrina, Johnny
 * @created 2/1/2018
 * @updated 2/7/2018
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
		keyboard = new Scanner(System.in);
	}
	
	//Method will allow the user to enter the amount will to deposit
	public void DepositEvent()
	{
		System.out.println("\nHow much money would you like to deposit in this account?");
		float user_amount = keyboard.nextFloat();
		
		System.out.println("\nWhat is the type of deposit?");
		String user_type = keyboard.next();
		
		onDepositEvent(user_amount, user_type);
		
	}
	
	//Will notify the observer on amount change
	public void onDepositEvent(float amt, String tp)
	{
		DepositMoneyViewData dataView = new DepositMoneyViewData();
		dataView.amount = amt;
		dataView.type   = tp;
		notifyObservers(dataView);
	}
	
	
	//For now I have commented this out, because I think we will have to make another use case eventually, were the user will have to 
	//add their own accounts, and then later we can edit this use cause to allow the user to choose an account
/*	
	public void addAccount()
	{
		System.out.println("\nWhich account are you depositing into?");
		String user_account = keyboard.nextLine();
		onAddAccountEvent(user_account);
	}
	public void onAddAccountEvent(String act)
	{
		DepositMoneyViewData dataView = new DepositMoneyViewData();
		dataView.account = act;
		notifyObservers(dataView);
	}
*/	

	
	@Override
	public void updateUI(Object data) {
			// Cast the data to our example view data container
		DepositMoneyViewData viewData = (DepositMoneyViewData) data;
			// Extract all the data out of the container and update the ui with new data
			// In the case of this example, since we are using the console, we are just printing out the new data
			System.out.println("Test Deposit: " + viewData.amount + ", Test Type: " + viewData.type);	
	}

}

package views;

import java.util.Scanner;

import views.ExampleView.ExampleViewData;

/**
 * 
 * Following the skeleton code created by Steven, I have created the DepositMoneyView.
 * This class will take the input of the user by prompting him through the console to write the 
 * amount of money that will be deposited.
 * 
 * @author Sabrina
 * @created 2/1/2018
 * @updated 2/2/2018
 *
 */
public class DepositMoneyView extends View {

	private Scanner keyboard; //for the scanner
	
	//Nested Data Class	
	public static class DepositMoneyViewData 
	{
		public float amount;
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
		onDepositEvent(user_amount);
	}
	
	//Will notify the observer
	public void onDepositEvent(float amt)
	{
		DepositMoneyViewData dataView = new DepositMoneyViewData();
		dataView.amount = amt;
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
			System.out.println("Test Deposit: " + viewData.amount);	
	}

}

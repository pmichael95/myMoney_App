package views;

import java.util.Scanner;

public class DepositMoneyView extends View {

	private Scanner keyboard; //for the scanner
	
	//Nested Data Class	
	public static class DepositMoneyViewData 
	{
		public float amount;
		public String account;
	}
	
	public DepositMoneyView()
	{
		keyboard = new Scanner(System.in);
	}
	
	//requires a method that will notify the amount of money that has been deposited
	//causeEvent ---> then we will have an onEvent to notify the Observer
	public void DepositEvent()
	{
		System.out.println("\nHow much money would you like to deposit in this account?");
		float user_amount = keyboard.nextFloat();
		onDepositEvent(user_amount);
	}
	public void onDepositEvent(float amt)
	{
		DepositMoneyViewData dataView = new DepositMoneyViewData();
		dataView.amount = amt;
		notifyObservers(dataView);
	}
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
		// TODO Auto-generated method stub
		
	}

}

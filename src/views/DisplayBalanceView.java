package views;

import java.text.DecimalFormat;

import GUI.LandingGUI;

/**
 * Display Balance View 
 * 
 * @author Shunyu Wang
 * @modifiedBy Johnny Mak, Shunyu Wang
 * @created 1/29/2018
 * @updated 3/15/2018
 * 
 */

public class DisplayBalanceView extends View {

	//Nested Data Class
	public static class DisplayBalanceViewData {
		
		public String account;
		public float accountBalance;
	}
	
	/**
	 * Initialize any view logic or necessary components for this view to work. such as buttons, event listeners
	 */
	public DisplayBalanceView() {	}
	
	
	public void DisplayEvent() {
		onDisplayEvent("Chequing");
	}
	
	
	/**
	 * @param account whose balance user select to display
	 */
	private void onDisplayEvent(String account) {
		DisplayBalanceViewData data = new DisplayBalanceViewData();
		data.account = account;
		notifyObservers(data);
	}
	

	/**
	 * Show the balance of selected account
	 * 
	 * @param data, the new updated data passed from the controller
	 * @see views.View#updateUI(java.lang.Object)
	 */
	@Override
	public void updateUI(Object data) {
		DisplayBalanceViewData viewData = (DisplayBalanceViewData) data;
		
		
		//format the accountBalance to have only 2 decimal places, the database still store precise value
		DecimalFormat df = new DecimalFormat("0.00");
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		String formattedAccountBalance = df.format(viewData.accountBalance);
		
		//avoiding cases such as "22.99999-23", which result a negative formatted string
		if(formattedAccountBalance.equals("-0.00")) {
			formattedAccountBalance = "0.00";
		}
		
		LandingGUI._GUI.UpdateBalance(formattedAccountBalance);
		System.out.println("Your balance of " + viewData.account + " is " + formattedAccountBalance);
	}

}

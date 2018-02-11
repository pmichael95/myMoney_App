package views;


/**
 * Display Balance View 
 * 
 * @author Shunyu Wang
 * @modifiedBy Johnny Mak
 * @created 1/29/2018
 * @updated 2/11/2018
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
		System.out.println("Your balance of " + viewData.account + " is " + viewData.accountBalance);
	}

}

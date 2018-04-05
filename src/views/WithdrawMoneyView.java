package views;

import java.util.Date;

/**
 * 
 * This will update the system GUI with the new information following a Withdraw event 
 * 
 * @author Jason Kalec
 * @modifiedBy Johnny Mak, Ramez Nahas
 * @created 2/6/2018
 * @updated 04/05/2018
 * 
 */

public class WithdrawMoneyView extends View {
//	private Scanner keyboard;


	public static class WithdrawMoneyViewData {
		//Data to be passed to controller

		public float amount;
		public String type;
		public String transactionReason;
		public Date date;
		
		public WithdrawMoneyViewData() {}

		public WithdrawMoneyViewData(float amount, String type, String transactionReason, Date date) {
			this.amount = amount;
			this.type = type;
			this.transactionReason = transactionReason;
			this.date = date;
		}
	}
	
	/**
	 * Initialize any view logic or necessary components for this view to work. such as buttons, event listeners
	 */
	public WithdrawMoneyView() {
//		keyboard = new Scanner(System.in);
	}
	
	
/*	
	public void WithdrawEvent() {
		// You wouldn't have this in your implementation
		// this was just here to cause an event to trigger. the cause of an event might be a button press, or ui event
		System.out.println("Please Enter Withdraw Amount: ");
		float withdrawAmount = keyboard.nextFloat();
		
		System.out.println("Please Enter Withdraw Type: ");
		String withdrawType = keyboard.next();
		// Fire an event
		//OnWithdrawEvent(withdrawAmount, withdrawType);
	}
	
	private void OnWithdrawEvent(float withdrawAmount, String withdrawType, String reason) {
		// Create a new data container holding state about our view currently
		WithdrawMoneyViewData data = new WithdrawMoneyViewData();
		// get the input data from the textfields or anything other ui data
		// and add it to the data container
		data.amount = withdrawAmount;
		data.type   = withdrawType;
		data.transactionReason = reason;
		// once the data container has all the necessary data for the controller to handle
		// notify the observer of the data change
		notifyObservers(data);
	}
*/	

	/**
	 * Update this view with the new data 
	 */
	@Override
	public void updateUI(Object data) {
//		// Cast the data to our example view data container
//		WithdrawMoneyViewData viewData = (WithdrawMoneyViewData) data;
//		// Extract all the data out of the container and update the ui with new data
//		// In the case of this example, since we are using the console, we are just printing out the new data
//		System.out.println("(Test)Amount Withdrawn " + viewData.amount);
	}
}

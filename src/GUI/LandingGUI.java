package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * The default landing GUI where the buttons (which would open other windows) can be found as well as the balance.
 * 
 * @author Philip Michael
 * @created 3/5/2018
 * @lastUpdated 3/5/2018 
 */

public class LandingGUI {
	
	/**
	 * GUI ELEMENTS
	 */
	// -- These @FXML private fields are associated to the fx:id of the individual components in the GUI.
	@FXML 
	private Text balance;
	@FXML
	private Button withdraw;
	@FXML
	private Button deposit;
	@FXML
	private Button showHistory;
	@FXML
	private Button recurringPayment;
	@FXML
	private Button clearAccount;
	@FXML
	private TextField inputAmount;
	
	/**
	 * HANDLER FUNCTIONS
	 */
	// -- These handler functions are tied to the onAction="#[namehere]" of the buttons in the GUI
	
	@FXML
	protected void withdrawButtonAction(ActionEvent event) {
		System.out.println("Called Withdraw Button Event");
	}
	
	@FXML
	protected void depositButtonAction(ActionEvent event) {
		System.out.println("Called Deposit Button Event");
	}
	
	@FXML
	protected void showHistoryButtonAction(ActionEvent event) {
		System.out.println("Called Edit Transaction Button Event");
	}
	
	@FXML
	protected void clearAccountButtonAction(ActionEvent event) {
		System.out.println("Called Delete Account Button Event");
	}
	
	@FXML
	protected void recurringPaymentButtonAction(ActionEvent event) {
		System.out.println("Called Recurring Payment Button Event");
	}
}

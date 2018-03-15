package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import models.DatabaseConnectionSource;
import controllers.*;

import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

/**
 * The default landing GUI where the buttons (which would open other windows) can be found as well as the balance.
 * 
 * @author Philip Michael
 * @modifiedBy Tobi Decary-Larocque
 * @created 3/5/2018
 * @lastUpdated 3/12/2018 
 */

public class LandingGUI {
	
	// Static GUI referencing to 'this' for use elsewhere
	public static LandingGUI _GUI;
	
	/**
	 * The controllers for our main functionalities
	 */
	private DisplayBalanceController displayBalance;
	//private DepositMoneyController depositMoney;
	//private WithdrawMoneyController withdrawMoney;
	
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
	 * Initialize is called after every element/handler was fetched.
	 * The order is: Constructor > Tie the members/handlers > initialize
	 */
	@FXML
	public void initialize() {
		// Get a connection to our database before we start any ui
		JdbcConnectionSource source = DatabaseConnectionSource.getConnection();
		
		// Creates the static GUI object referencing to the current GUI shown on screen.
		// This is used in the views.
		if(_GUI == null) {
			_GUI = this;
		}
		
		if (source != null) {
			displayBalance = new DisplayBalanceController();

			// WithdrawMoneyController withdrawController = new WithdrawMoneyController();

			// DepositMoneyController deposit_money = new DepositMoneyController();
			
			// Once we are done, close the connection to the database 
			DatabaseConnectionSource.closeConncetion();
		} else {
			System.out.println("Could not make a connection to the database");
		}
	}
	
	/**
	 * HELPER FUNCTIONS
	 */
	/**
	 * Updates the displayed balance on the main GUI screen.
	 * @param balance to update on the balance
	 */
	public void UpdateBalance(float balance) {
		this.balance.setText("$" + balance);
	}
	
	/**
	 * HANDLER FUNCTIONS
	 */
	// -- These handler functions are tied to the onAction="#[namehere]" of the buttons in the GUI.
	// In them, use the controllers respectively to initiate the functionality of each.
	
	
	/**
	 * For withdraw, we'd need to use the Withdraw controller to initiate the withdrawal.
	 * For consistency's sake, we will need to also update the amount in display_balance table.
	 * For that, we will need to also call the DisplayBalance's controller's DAO to update the amount.
	 * Thereafter, the view is updated again for us.
	 * @param event triggered by clicking on the button.
	 * @throws SQLException 
	 */
	@FXML
	protected void withdrawButtonAction(ActionEvent event) throws SQLException {
		System.out.println("Called Withdraw Button Event");

		//test
		displayBalance.updateBalance("withdraw", 100);

	}
	
	@FXML
	protected void depositButtonAction(ActionEvent event) throws SQLException {
		System.out.println("Called Deposit Button Event");
		
		//test
		displayBalance.updateBalance("deposit", 100);

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
